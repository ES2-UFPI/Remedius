import React, { useState, useEffect } from 'react';
import { View, Text, StyleSheet, TouchableOpacity, ScrollView, useWindowDimensions } from 'react-native';
import { Ionicons } from '@expo/vector-icons';
import { format, addDays, addHours, differenceInDays } from 'date-fns';
import { ptBR } from 'date-fns/locale';

interface Medication {
  id: string;
  name: string;
  startDate: string; // Data inicial do uso
  frequencyHours: number; // Frequência em horas
  dosage: string;
  color: string;
}

const MedicationCard = ({ medication }: { medication: Medication }) => (
  <View style={[styles.medicationCard, { backgroundColor: medication.color }]}>
    <Text style={styles.medicationName} numberOfLines={1}>
      {medication.name}
    </Text>
    <Text style={styles.medicationDosage} numberOfLines={1}>
      {medication.dosage}
    </Text>
  </View>
);

export default function MedicationSchedule() {
  const { width: windowWidth } = useWindowDimensions();
  const [medications, setMedications] = useState<Medication[]>([]);
  const [startDate, setStartDate] = useState(new Date());
  const [visibleColumns, setVisibleColumns] = useState(3);

  useEffect(() => {
    const calculateVisibleColumns = () => {
      if (windowWidth < 300) {
        setVisibleColumns(2);
      } else if (windowWidth < 768) {
        setVisibleColumns(3);
      } else if (windowWidth < 1024) {
        setVisibleColumns(5);
      } else {
        setVisibleColumns(7);
      }
    };

    calculateVisibleColumns();
  }, [windowWidth]);

  const getColumnWidth = () => {
    const availableWidth = Math.max(windowWidth - 20, 200); // Garante uma largura mínima
    return availableWidth / visibleColumns;
  };

  useEffect(() => {
    fetchMedications();
  }, []);

  const fetchMedications = async () => {
    try {
      const data: Medication[] = [
        {
          id: '1',
          name: 'Dipirona',
          startDate: '2024-11-16T08:00:00',
          frequencyHours: 48,
          dosage: '2 comprimidos',
          color: '#74B2C3',
        },
        {
          id: '2',
          name: 'Paracetamol',
          startDate: '2024-11-16T12:00:00',
          frequencyHours: 24,
          dosage: '1 comprimido',
          color: '#D34547',
        },
        {
          id: '3',
          name: 'Ibuprofeno',
          startDate: '2024-11-16T18:00:00',
          frequencyHours: 72,
          dosage: '1 comprimido',
          color: '#45D35A',
        },
      ];
      setMedications(data);
    } catch (error) {
      console.error('Erro ao buscar medicações:', error);
    }
  };

  const getDays = () => {
    return Array.from({ length: 30 }, (_, i) => addDays(startDate, i));
  };

  const getMedicationsForDay = (date: Date) => {
    return medications.filter((med) => {
      const medicationStartDate = new Date(med.startDate);
      const diffInDays = differenceInDays(date, medicationStartDate);

      // Calcula se o medicamento deve ser exibido neste dia
      return (
        diffInDays >= 0 && // A medicação já começou
        (diffInDays * 24) % med.frequencyHours === 0 // É um horário válido dentro da frequência
      );
    });
  };

  const advanceDays = () => {
    setStartDate(addDays(startDate, 1));
  };

  const backDays = () => {
    setStartDate(addDays(startDate, -1));
  };

  return (
    <View style={styles.container}>
      <View style={styles.scheduleContainer}>
        <ScrollView
          horizontal
          showsHorizontalScrollIndicator={false}
          style={styles.scrollView}
        >
          {getDays().map((date, index) => (
            <View key={index} style={[styles.column, { width: getColumnWidth() }]}>
              <View style={styles.dateHeader}>
                <Text style={styles.dateText} numberOfLines={1}>
                  {format(date, 'dd/MM', { locale: ptBR })}
                </Text>
                <Text style={styles.dayText} numberOfLines={1}>
                  {format(date, 'EEEE', { locale: ptBR })}
                </Text>
              </View>
              <ScrollView style={styles.medicationsContainer} showsVerticalScrollIndicator={false}>
                {getMedicationsForDay(date).map((med, medIndex) => (
                  <MedicationCard key={medIndex} medication={med} />
                ))}
              </ScrollView>
            </View>
          ))}
        </ScrollView>
      </View>
      <View style={styles.navigationContainer}>
        <TouchableOpacity style={styles.navigationButton} onPress={backDays}>
          <Ionicons name="chevron-back" size={24} color="black" />
        </TouchableOpacity>
        <TouchableOpacity style={styles.navigationButton} onPress={advanceDays}>
          <Ionicons name="chevron-forward" size={24} color="black" />
        </TouchableOpacity>
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
  },
  scheduleContainer: {
    flex: 1,
  },
  navigationContainer: {
    flexDirection: 'row',
    justifyContent: 'center',
    alignItems: 'center',
    paddingVertical: 8,
    backgroundColor: '#f5f5f5',
  },
  scrollView: {
    flex: 1,
  },
  column: {
    borderRightWidth: 1,
    borderRightColor: '#eee',
    height: '100%',
    minWidth: 100,
  },
  dateHeader: {
    padding: 8,
    alignItems: 'center',
    backgroundColor: '#f5f5f5',
    borderBottomWidth: 1,
    borderBottomColor: '#eee',
  },
  dateText: {
    fontSize: 14,
    fontWeight: 'bold',
    textAlign: 'center',
  },
  dayText: {
    fontSize: 12,
    color: '#666',
    textAlign: 'center',
  },
  medicationsContainer: {
    padding: 8,
    flex: 1,
  },
  medicationCard: {
    padding: 8,
    borderRadius: 8,
    marginBottom: 8,
    elevation: 2,
    shadowColor: '#000',
    shadowOffset: { width: 0, height: 1 },
    shadowOpacity: 0.2,
    shadowRadius: 2,
  },
  medicationName: {
    fontSize: 14,
    fontWeight: 'bold',
    color: '#fff',
  },
  medicationDosage: {
    fontSize: 12,
    color: '#fff',
  },
  navigationButton: {
    padding: 12,
    backgroundColor: '#f0f0f0',
    borderRadius: 20,
    marginHorizontal: 16,
    elevation: 2,
  },
});

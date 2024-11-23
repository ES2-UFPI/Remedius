import React, { useState, useEffect, useRef } from 'react';
import { View, Text, TouchableOpacity, ScrollView, useWindowDimensions } from 'react-native';
import { Ionicons } from '@expo/vector-icons';
import { format, addDays, differenceInDays } from 'date-fns';
import { ptBR } from 'date-fns/locale';
import "../global.css"

interface Medication {
  id: string;
  name: string;
  startDate: string;
  frequencyHours: number;
  dosage: string;
  color: string;
}

const MedicationCard = ({ medication }: { medication: Medication }) => (
  <View className="p-3 rounded-lg mb-2" style={{ backgroundColor: medication.color }}>
    <Text className="text-sm font-bold text-white" numberOfLines={1}>
      {medication.name}
    </Text>
    <Text className="text-xs text-white" numberOfLines={1}>
      {medication.dosage}
    </Text>
  </View>
);

const MedicationSchedule = () => {
  const viewRef = useRef(null);
  const [medications, setMedications] = useState<Medication[]>([]);
  const [startDate, setStartDate] = useState(new Date());
  const [visibleColumns, setVisibleColumns] = useState(3);
  const [viewWidth, setViewWidth] = useState(0);

  const calculateVisibleColumns = () => {
    const baleColumnWidth = 120; // Largura mínima desejada para cada coluna
    const columns = Math.round(viewWidth / baleColumnWidth);
    setVisibleColumns(columns);
  };

  const getColumnWidth = () => {
    return Math.floor(viewWidth / visibleColumns); // Divida o espaço igualmente entre as colunas visíveis
  };

  useEffect(() => {
    calculateVisibleColumns();
  }, [viewWidth]);

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
          color: '#FF6347',
        },
        {
          id: '3',
          name: 'Ibuprofeno',
          startDate: '2024-11-16T18:00:00',
          frequencyHours: 72,
          dosage: '1 comprimido',
          color: '#45D35A',
        },
        {
          id: '4',
          name: 'Venvanse',
          startDate: '2024-11-16T18:00:00',
          frequencyHours: 48,
          dosage: '1 comprimido',
          color: '#D34547',
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

      return (
        diffInDays >= 0 &&
        (diffInDays * 24) % med.frequencyHours === 0
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
    <View
      ref={viewRef}
      onLayout={(event) => {
        const { width } = event.nativeEvent.layout;
        setViewWidth(width);
      }}
      className="flex-1 bg-[#D8F1F5] bg-opacity-67 rounded-md"
    >
      <ScrollView
        horizontal
        showsHorizontalScrollIndicator={false}
        className="flex-1 pl-1 pt-1"
      >
        {getDays().map((date, index) => (
          <View key={index} className="p-1" style={{ width: getColumnWidth() }}>
            <View className="mb-2">
              <Text className="text-sm font-bold text-center" numberOfLines={1}>
                {format(date, 'dd/MM', { locale: ptBR })}
              </Text>

              <Text className="text-xs text-gray-600 text-center" numberOfLines={1}>
                {format(date, 'EEEE', { locale: ptBR })}
              </Text>
            </View>

            <ScrollView className="flex-1" showsVerticalScrollIndicator={false}>
              {getMedicationsForDay(date).map((med, medIndex) => (
                <MedicationCard key={medIndex} medication={med} />
              ))}
            </ScrollView>
          </View>
        ))}
      </ScrollView>

      <View className="flex-row justify-center items-center">
        <TouchableOpacity className="bg-white p-3 rounded-full m-4" onPress={backDays}>
          <Ionicons name="chevron-back" size={24} color="black" />
        </TouchableOpacity>
        <TouchableOpacity className="bg-white p-3 rounded-full m-4" onPress={advanceDays}>
          <Ionicons name="chevron-forward" size={24} color="black" />
        </TouchableOpacity>
      </View>
    </View>
  );
};

export default MedicationSchedule;

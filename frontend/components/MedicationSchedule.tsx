import React, { useState, useEffect, useRef } from 'react';
import { View, Text, TouchableOpacity, ScrollView } from 'react-native';
import { Ionicons } from '@expo/vector-icons';
import { format, addDays, differenceInDays, parseISO } from 'date-fns';
import { ptBR } from 'date-fns/locale';
import axios from 'axios';
import "../global.css"

// Cores para as medicações
const MEDICATION_COLORS = [
  '#D34547',   // Vermelho
  '#74B2C3',   // Azul
  '#45D35A',   // Verde
  '#B5CC06',   // Amarelo
  '#808080'    // Cinza (para suspensas)
];

interface Medication {
  id: number;
  medicamento: {
    id: number;
    nome: string;
    laboratorio: string;
  };
  dataInicial: string;
  frequencia: string;
  dosagem: number;
  cor?: string;
}

const MedicationCard = ({ medication }: { medication: Medication }) => (
  <View 
    className="p-3 rounded-lg mb-2" 
    style={{ 
      backgroundColor: medication.cor || 
        MEDICATION_COLORS[medication.id % MEDICATION_COLORS.length] 
    }}
  >
    <Text className="text-sm font-bold text-white" numberOfLines={1}>
      {medication.medicamento.nome.toUpperCase()}
    </Text>
    <Text className="text-xs text-white" numberOfLines={1}>
      {medication.dosagem} comp.
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
    const columnWidth = 120;
    const columns = Math.round(viewWidth / columnWidth);
    setVisibleColumns(Math.max(columns, 1));
  };

  const getColumnWidth = () => {
    return Math.floor(viewWidth / visibleColumns);
  };

  useEffect(() => {
    calculateVisibleColumns();
  }, [viewWidth]);

  useEffect(() => {
    fetchMedications();
  }, []);

  const fetchMedications = async () => {
    try {
      const response = await axios.get('http://localhost:8080/usuarios-medicamentos/1');
      const medicationsData: Medication[] = response.data.map((med: Medication, index: number) => ({
        ...med,
        cor: MEDICATION_COLORS[index % MEDICATION_COLORS.length]
      }));
      setMedications(medicationsData);
    } catch (error) {
      console.error('Erro ao buscar medicações:', error);
    }
  };

  const getDays = () => {
    return Array.from({ length: 30 }, (_, i) => addDays(startDate, i));
  };

  const getMedicationsForDay = (date: Date) => {
    return medications.filter((med) => {
      const medicationStartDate = parseISO(med.dataInicial);
      const diffInDays = differenceInDays(date, medicationStartDate);
      const frequencyHours = parseInt(med.frequencia);

      return (
        diffInDays >= 0 &&
        (diffInDays * 24) % frequencyHours === 0
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
      className="flex-1 bg-[#E8F4F6] bg-opacity-67 rounded-md"
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
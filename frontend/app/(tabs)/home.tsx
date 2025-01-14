import MedicationSchedule from '@/components/MedicationSchedule';
import React, { useState, useEffect, useRef } from 'react';
import { View, Text, TouchableOpacity, useWindowDimensions, Animated } from 'react-native';
import { SafeAreaView } from 'react-native-safe-area-context';
import { useFonts, Katibeh_400Regular } from '@expo-google-fonts/katibeh';
import axios from 'axios';
import AnimatedHeader from '../../components/Header';
import { format, parseISO, isPast, isFuture } from 'date-fns';
import RecentMedications from '@/entities/RecentMedications';

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
}

const MedicationCard = ({ name, dosage, time }) => (
  <View className="w-full flex-row justify-between items-center">
    <View className="flex-1">
      <Text className="text-[#2D3648] text-2xl sm:text-3xl font-bold mb-1">{name}</Text>
      <Text className="text-[#2D3648] text-base sm:text-lg">{dosage}</Text>
    </View>
    <View className="bg-[#E8F4F6] h-24 w-24 rounded-full flex items-center justify-center">
      <Text className="text-[#2D3648] text-sm sm:text-lg font-bold text-center">
        {time}
      </Text>
    </View>
  </View>
);

const Home = () => {
  const [fontsLoaded] = useFonts({
    Katibeh_400Regular,
  });
  const { width } = useWindowDimensions();
  const cardWidth = Math.min(width - 32, 768);
  const scrollY = useRef(new Animated.Value(0)).current;
  
  const [nextMedication, setNextMedication] = useState<Medication | null>(null);

  useEffect(() => {
    fetchNextMedication();
  }, []);

  const fetchNextMedication = async () => {
    try {
      const response = await axios.get('http://localhost:8080/usuarios-medicamentos/1');
      const medications: Medication[] = response.data;
      
      // Ordena as medicações por data e filtra as medicações passadas
      const upcomingMedications = medications
        .filter(med => {
          const medDate = parseISO(med.dataInicial);
          return isPast(medDate) || isFuture(medDate);
        })
        .sort((a, b) => new Date(a.dataInicial).getTime() - new Date(b.dataInicial).getTime());

      if (upcomingMedications.length > 0) {
        const nextMed = upcomingMedications[0];
        setNextMedication(nextMed);
      }
    } catch (error) {
      console.error('Erro ao buscar próxima medicação:', error);
    }
  };

  return (
    <SafeAreaView className="flex-1 bg-[#D8F1F5]">
      <AnimatedHeader scrollY={scrollY} />

      <Animated.ScrollView
        className="flex-1"
        contentContainerStyle={{ paddingTop: 100 }}
        onScroll={Animated.event(
          [{ nativeEvent: { contentOffset: { y: scrollY } } }],
          { useNativeDriver: true }
        )}
        scrollEventThrottle={16}
      >
        <View className="items-center">
          {/* Próxima medicação */}
          <View className="mb-6 mt-6 w-full" style={{ maxWidth: cardWidth }}>
            <View className="bg-white rounded-2xl p-4 sm:p-6 relative shadow-md">
              <Text className="text-[#36555E] text-4xl sm:text-5xl font-light" style={{ fontFamily: 'Katibeh_400Regular' }}>
                Próxima medicação
              </Text>
              {nextMedication ? (
                <MedicationCard 
                  name={nextMedication.medicamento.nome.toUpperCase()} 
                  dosage={`${nextMedication.dosagem} comp.`} 
                  time={`HOJE: ${format(parseISO(nextMedication.dataInicial), 'HH:mm')}`} 
                />
              ) : (
                <Text className="text-[#2D3648] text-lg mt-4">
                  Nenhuma medicação encontrada
                </Text>
              )}
            </View>
          </View>

          {/* Cronograma de medicações */}
          <View className="mb-6 w-full" style={{ maxWidth: cardWidth }}>
            <View className="bg-white rounded-2xl p-4 sm:p-6 relative shadow-md">
              <Text className="text-[#36555E] text-4xl sm:text-5xl font-light" style={{ fontFamily: 'Katibeh_400Regular' }}>Cronograma</Text>
              <MedicationSchedule />
            </View>
          </View>

          {/* Seção para Medicações Recentes (Medicações que não foi atualizado pelo usuário
          a informação de que tomou a medicação) */}
          <RecentMedications />
        </View>
      </Animated.ScrollView>
    </SafeAreaView>
  );
}

export default Home;
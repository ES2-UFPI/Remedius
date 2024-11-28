import React, { useState, useEffect, useRef } from 'react';
import { View, Text, TouchableOpacity, useWindowDimensions, Animated } from 'react-native';
import { Edit2, Bell } from 'lucide-react-native';
import { SafeAreaView } from 'react-native-safe-area-context';
import { useFonts } from 'expo-font';
import { Katibeh_400Regular } from '@expo-google-fonts/katibeh';
import axios from 'axios';
import MedicationStock from '@/components/MedicationStock';
import AnimatedHeader from '../../components/Header';

// Define paleta de cores para as medicações enquanto aguardamos a implementação de cores reais
const MEDICATION_COLORS = [
  '#FFE4E4',   // Light Red
  '#E4F2FF',   // Light Blue
  '#E4FFE8',   // Light Green
  '#FFF4E4',   // Light Orange
  '#E5E5E5'    // Light Gray (for suspended)
];

// Definição de tipo para as respostas da API
interface Medicamento {
  id: number;
  nome: string;
  laboratorio: string;
}

interface UsuarioMedicamento {
  id: number;
  medicamento: Medicamento;
  dataInicial: string;
  frequencia: string;
  dosagem: number;
}
import { router } from 'expo-router';

interface MedicationCardProps {
  name: string;
  status?: string;
  color?: string;
}

const MedicationCard: React.FC<MedicationCardProps> = ({ name, color='#E5E5E5', status='inactive' }) => (
  <View style={{ backgroundColor: color }} className="flex-row justify-between items-center p-4 rounded-lg mb-2">
    <Text className="text-base text-[#2F4858]">{name}</Text>
    <View className="flex-row gap-3">
      <TouchableOpacity
        className="p-1"
        onPress={() => router.push({
          pathname: '../edit', // Ensure this path is correctly defined in your routing configuration
          params: { name, status }
        })}
      >
        <Edit2 size={20} color="#2F4858" />
      </TouchableOpacity>
      <TouchableOpacity className="p-1">
        <Bell size={20} color="#2F4858" />
      </TouchableOpacity>
    </View>
  </View>
);

const MedicationList = () => {
  const [fontsLoaded] = useFonts({
    Katibeh_400Regular,
  });
  const { width } = useWindowDimensions();
  const cardWidth = Math.min(width - 32, 768);
  const scrollY = useRef(new Animated.Value(0)).current;

  // estados para medicações ativas e suspensas
  const [activeMedications, setActiveMedications] = useState<UsuarioMedicamento[]>([]);
  const [suspendedMedications, setSuspendedMedications] = useState<UsuarioMedicamento[]>([]);

  // busca as medicações do usuário na API
  useEffect(() => {
    const fetchMedications = async () => {
      try {
        const response = await axios.get('http://localhost:8080/usuarios-medicamentos/1');
        
        // separa as medicações ativas e suspensas
        // nota: a API não retorna medicações suspensas ainda, então usamos um placeholder
        const medications = response.data;
        setActiveMedications(medications);
        
        // placeholder para medicações suspensas
        setSuspendedMedications([
          { 
            id: 1, 
            medicamento: { id: 1, nome: 'Glifage', laboratorio: 'N/A' }, 
            dataInicial: '', 
            frequencia: '', 
            dosagem: 0 
          },
          { 
            id: 2, 
            medicamento: { id: 2, nome: 'Buscopam', laboratorio: 'N/A' }, 
            dataInicial: '', 
            frequencia: '', 
            dosagem: 0 
          }
        ]);
      } catch (error) {
        console.error('Error fetching medications:', error);
        // opcional: exibir mensagem de erro ao usuário
      }
    };

    fetchMedications();
  }, []);

  // calcula a duração do estoque de medicações
  const calculateStockDuration = (medication: UsuarioMedicamento) => {
    // placeholder porque a lógica de cálculo de estoque não foi implementada
    const stockQuantity = 30; // Quantidade fixa para demonstração
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
          {/* Active Medications */}
          <View className="mb-6 mt-6 w-full" style={{ maxWidth: cardWidth }}>
            <View className="bg-white rounded-2xl p-4 sm:p-6 relative shadow-md">
              <Text 
                className="text-[#36555E] text-4xl sm:text-5xl font-light" 
                style={{ fontFamily: 'Katibeh_400Regular' }}
              >
                Medicações ativas
              </Text>
              {activeMedications.map((medication, index) => (
                <View 
                  key={medication.id} 
                  className="flex-row justify-between items-center p-4 rounded-lg mb-2"
                  style={{ 
                    backgroundColor: MEDICATION_COLORS[index % MEDICATION_COLORS.length] 
                  }}
                >
                  <Text className="text-base text-[#2F4858]">
                    {medication.medicamento.nome.charAt(0).toUpperCase() + medication.medicamento.nome.slice(1)}
                  </Text>
                  <View className="flex-row gap-3">
                    <TouchableOpacity className="p-1">
                      <Edit2 size={20} color="#2F4858" />
                    </TouchableOpacity>
                    <TouchableOpacity className="p-1">
                      <Bell size={20} color="#2F4858" />
                    </TouchableOpacity>
                  </View>
                </View>
              ))}
            </View>
          </View>

          {/* Suspended Medications */}
          <View className="mb-6 w-full" style={{ maxWidth: cardWidth }}>
            <View className="bg-white rounded-2xl p-4 sm:p-6 relative shadow-md">
              <Text 
                className="text-[#36555E] text-4xl sm:text-5xl font-light" 
                style={{ fontFamily: 'Katibeh_400Regular' }}
              >
                Medicações suspensas
              </Text>
              {suspendedMedications.map((medication) => (
                <View 
                  key={medication.id} 
                  className="flex-row justify-between items-center p-4 rounded-lg mb-2 bg-[#E5E5E5]"
                >
                  <Text className="text-base text-[#2F4858]">
                    {medication.medicamento.nome}
                  </Text>
                  <View className="flex-row gap-3">
                    <TouchableOpacity className="p-1">
                      <Edit2 size={20} color="#2F4858" />
                    </TouchableOpacity>
                    <TouchableOpacity className="p-1">
                      <Bell size={20} color="#2F4858" />
                    </TouchableOpacity>
                  </View>
                </View>
              ))}
            </View>
          </View>

          {/* Medication Stock */}
          <View className="mb-6 w-full" style={{ maxWidth: cardWidth }}>
            <View className="bg-white rounded-2xl p-4 sm:p-6 relative shadow-md">
              <Text 
                className="text-[#36555E] text-4xl sm:text-5xl font-light" 
                style={{ fontFamily: 'Katibeh_400Regular' }}
              >
                Estoque de medicações
              </Text>
              <MedicationStock medications={activeMedications} />
            </View>
          </View>
        </View>
      </Animated.ScrollView>
    </SafeAreaView>
  );
}

export default MedicationList;
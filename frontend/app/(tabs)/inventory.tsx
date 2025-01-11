import React, { useRef, useState, useEffect } from 'react';
import { View, Text, TouchableOpacity, ScrollView, SafeAreaView, useWindowDimensions, Animated } from 'react-native';
import { ArrowLeft, Edit, Bell, ChevronDown, ChevronUp } from 'lucide-react-native';
import { useFonts, Katibeh_400Regular } from '@expo-google-fonts/katibeh';
import axios from 'axios';

interface Medication {
  id: number;
  medicamento: {
    id: number;
    nome: string;
    laboratorio: string;
    cor: string | null;
  };
  quantidade: number;
  ultimaCompra: string;
  status: 'Ativo' | 'Suspenso';
  duracaoEstimada: number;
}

interface MedicationCardProps {
  name: string;
  quantity: number;
  estimatedDuration: number;
  status: 'Ativo' | 'Suspenso';
  onEdit: () => void;
  onNotification: () => void;
}

const MedicationCard = ({
  name,
  quantity,
  estimatedDuration,
  status,
  color,
  onEdit,
  onNotification
}: MedicationCardProps & { color: string | null }) => {
  const [expanded, setExpanded] = useState(false);
  const animation = useRef(new Animated.Value(0)).current;

  const toggleExpand = () => {
    setExpanded(!expanded);
    Animated.timing(animation, {
      toValue: expanded ? 0 : 1,
      duration: 300,
      useNativeDriver: false,
    }).start();
  };

  const contentHeight = animation.interpolate({
    inputRange: [0, 1],
    outputRange: [0, 210],
  });

  const getBgColor = () => {
    if (status === 'Suspenso') return 'bg-gray-200';
    return color ? `bg-[${color}]` : 'bg-blue-100'; // Use color from API or fallback
  };

  return (
    <View className={`${getBgColor()} rounded-lg p-4 mb-3`}>
      <TouchableOpacity onPress={toggleExpand} className="flex-row justify-between items-center">
        <Text className="text-gray-800 text-2xl font-semibold pl-3">{name}</Text>
        {expanded ? <ChevronUp className="text-gray-600" size={24} /> : <ChevronDown className="text-gray-600" size={24} />}
      </TouchableOpacity>
      
      <Animated.View style={{ height: contentHeight, overflow: 'hidden' }}>
        <View className="bg-white rounded-2xl p-4 sm:p-6 relative shadow-md mb-3">
          <Text className="text-gray-900 mb-1 text-lg">Total: {quantity} comps</Text>
          <View className="flex-row items-center mt-1">
            <Text className="text-gray-900 text-lg">Duração Estimada: {estimatedDuration} dias</Text>
          </View>
        </View>  
        
        <View className="flex-row justify-between">
          <TouchableOpacity onPress={onEdit} className="p-2">
            <View className="flex-row bg-white rounded-2xl p-2 relative shadow-md">
              <Edit className="text-gray-600" size={20} />
              <Text className="text-[#36555E] pl-2 mb-[-15px] text-2xl sm:text-3xl font-light" style={{ fontFamily: 'Katibeh_400Regular' }}>
                Editar Estoque
              </Text>
            </View>
          </TouchableOpacity>
          
          <TouchableOpacity onPress={onNotification} className="p-2">
            <View className="flex-row bg-white rounded-2xl p-2 relative shadow-md">
              <Bell size={20} className="text-gray-600 mr-2" />
              <Text className="text-[#36555E] mb-[-15px] text-2xl sm:text-3xl font-light" style={{ fontFamily: 'Katibeh_400Regular' }}>
                Notificações
              </Text>
            </View>
          </TouchableOpacity>
        </View>
      </Animated.View>
    </View>
  );
};

const Inventory = () => {
  const [fontsLoaded] = useFonts({
    Katibeh_400Regular,
  });
  const { width } = useWindowDimensions();
  const cardWidth = Math.min(width - 32, 768);
  const scrollY = useRef(new Animated.Value(0)).current;

  const [medications, setMedications] = useState<Medication[]>([]);

  useEffect(() => {
    fetchMedications();
  }, []);

  const fetchMedications = async () => {
    try {
      const response = await axios.get('http://localhost:8080/estoque/1');
      setMedications(response.data);
    } catch (error) {
      console.error('Erro ao buscar medicações:', error);
    }
  };

  const activeMedications = medications.filter(med => med.status === 'Ativo');
  const suspendedMedications = medications.filter(med => med.status === 'Suspenso');

  if (!fontsLoaded) {
    return null;
  }

  return (
    <SafeAreaView className="flex-1 bg-[#D8F1F5]">
      <View className="flex-1">
        <View className="flex-row items-center p-4 bg-white">
          <TouchableOpacity accessible={true} accessibilityLabel="Voltar" accessibilityHint="Navega para a tela anterior">
            <ArrowLeft size={24} className="text-gray-800" />
          </TouchableOpacity>
          <Text className="text-xl font-semibold ml-4 text-gray-800">Estoque</Text>
        </View>

        <ScrollView className="flex-1 px-4 pt-4">
          <View className="items-center">
            <View className="mb-6 mt-6 w-full" style={{ maxWidth: cardWidth }}>
              <View className="bg-white rounded-2xl p-4 sm:p-6 relative shadow-md">
                <Text className="text-[#36555E] text-4xl sm:text-5xl font-light" style={{ fontFamily: 'Katibeh_400Regular' }}>Estoque de medicações ativas</Text>
                {activeMedications.map((med) => (
                  <MedicationCard
                    key={med.id}
                    name={med.medicamento.nome}
                    quantity={med.quantidade}
                    estimatedDuration={med.duracaoEstimada}
                    status={med.status}
                    color={med.medicamento.cor}
                    onEdit={() => console.log('Edit', med.medicamento.nome)}
                    onNotification={() => console.log('Notification', med.medicamento.nome)}
                  />
                ))}
              </View>
            </View>

            <View className="mb-6 mt-6 w-full" style={{ maxWidth: cardWidth }}>
              <View className="bg-white rounded-2xl p-4 sm:p-6 relative shadow-md">
                <Text className="text-[#36555E] text-4xl sm:text-5xl font-light" style={{ fontFamily: 'Katibeh_400Regular' }}>Estoque de medicações suspensas</Text>
                {suspendedMedications.map((med) => (
                  <MedicationCard
                    key={med.id}
                    name={med.medicamento.nome}
                    quantity={med.quantidade}
                    estimatedDuration={med.duracaoEstimada}
                    status={med.status}
                    color={med.medicamento.cor}
                    onEdit={() => console.log('Edit', med.medicamento.nome)}
                    onNotification={() => console.log('Notification', med.medicamento.nome)}
                  />
                ))}
              </View>
            </View>
          </View>
        </ScrollView>
      </View>
    </SafeAreaView>
  );
}

export default Inventory;
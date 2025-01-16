import React from 'react';
import { View, Text } from 'react-native';

export interface MedicationEventProps {
  nome: string;
  dosagem: string;
  horario: string;
  cor: string;
  status: 'tomado' | 'rejeitado' | 'pendente';
}

const statusColors = {
  tomado: 'bg-green-500',
  rejeitado: 'bg-red-500',
  pendente: 'bg-gray-400',
};

const MedicationEventCard: React.FC<MedicationEventProps> = ({
  nome,
  dosagem,
  horario,
  cor,
  status
}) => {
  return (
    <View className={`bg-[#${cor}] rounded-xl p-4 mb-2 relative`}>
      {/* Indicador do status do evento */}
      <View 
        className={`absolute top-2 right-2 w-3 h-3 rounded-full ${statusColors[status]}`}
      />
      
      {/* Informações da medicação */}
      <Text className="text-[#2D3648] text-lg font-bold mb-1">
        {nome}
      </Text>
      <View className="flex-row justify-between items-center">
        <Text className="text-[#2D3648] text-base">
          {dosagem}
        </Text>
        <Text className="text-[#2D3648] text-sm font-medium">
          {horario}
        </Text>
      </View>
    </View>
  );
};

export default MedicationEventCard;

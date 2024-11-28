import React from 'react';
import { View, Text, ScrollView } from 'react-native';

// Definição de tipo para as medicações
interface Medicamento {
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

// Componente responsável pelo Estoque de Medicamentos
const MedicationStock = ({ medications }: { medications: Medicamento[] }) => {
  // Função para calcular a duração estimada do estoque em dias
  const calculateStockDuration = (
    medication: Medicamento
  ) => {
    // Lógica de cálculo de estoque - IMPORTANTE: este é um placeholder
    // Você precisará implementar a lógica real de contagem de comprimidos
    const stockQuantity = 30; // Quantidade fixa para demonstração
    const frequencyHours = parseFloat(medication.frequencia);
    const dosesPerDay = 24 / frequencyHours;
    const dosagePerUse = medication.dosagem;

    const totalDoses = stockQuantity / dosagePerUse;
    const totalDays = totalDoses / dosesPerDay;

    const startDate = new Date(medication.dataInicial);
    const endDate = new Date(startDate.getTime() + totalDays * 24 * 60 * 60 * 1000);

    return { 
      totalDays: Math.floor(totalDays), 
      endDate, 
      stockQuantity 
    };
  };

  return (
    <ScrollView className="flex-1 bg-[#D8F1F5] rounded-3xl px-4 py-2">
      {medications.map((medication, index) => {
        const { totalDays, endDate, stockQuantity } = calculateStockDuration(medication);

        return (
          <View key={medication.id} className="bg-white rounded-2xl p-4 my-3 shadow-md">
            <Text className="text-2xl font-bold text-[#36555E] mb-2">
              {medication.medicamento.nome.charAt(0).toUpperCase() + medication.medicamento.nome.slice(1)}
            </Text>
            <Text className="text-lg text-black mb-1">
              Estoque atual: {stockQuantity} unidades
            </Text>
            <Text className="text-lg text-black">
              Estoque durará {totalDays} dias (até{' '}
              {endDate.toLocaleDateString('pt-BR')})
            </Text>
            <Text className="text-base text-gray-600 mt-1">
              Laboratório: {medication.medicamento.laboratorio}
            </Text>
          </View>
        );
      })}
    </ScrollView>
  );
}

export default MedicationStock;
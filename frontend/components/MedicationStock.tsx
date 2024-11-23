import React, { useEffect, useState } from 'react';
import { View, Text, ScrollView } from 'react-native';

// Componente responsável pelo Estoque de Medicamentos
const MedicationStock = () => {
  const [medicationStock, setMedicationStock] = useState<
    { name: string; stock: number; frequency: number; startDate: string }[]
  >([]);

  // Função para buscar os dados de estoque de medicamentos da API
  const fetchMedicationStock = async () => {
    try {
      // Substitua esta URL pela URL real da API
      //const response = await fetch('https://api.exemplo.com/medication-stock');
      //const data = await response.json();

      // Exemplo de dados retornados da API
      setMedicationStock([
        { name: 'Dipirona', stock: 10, frequency: 48, startDate: '2024-11-16' },
        { name: 'Paracetamol', stock: 20, frequency: 24, startDate: '2024-11-15' },
      ]);
    } catch (error) {
      console.error('Erro ao buscar dados de estoque:', error);
    }
  };

  // Função para calcular a duração estimada do estoque em dias
  const calculateStockDuration = (
    stock: number,
    frequency: number,
    startDate: string
  ) => {
    const startDateTime = new Date(startDate).getTime();
    const dosesPerDay = 24 / frequency;
    const totalDoses = stock;
    const totalDays = totalDoses / dosesPerDay;

    const endDate = new Date(startDateTime + totalDays * 24 * 60 * 60 * 1000);
    return { totalDays: Math.floor(totalDays), endDate };
  };

  useEffect(() => {
    fetchMedicationStock();
  }, []);

  return (
    <ScrollView className="flex-1 bg-[#D8F1F5] rounded-t-3xl px-4 py-6">
      <Text className="text-4xl font-['Katibeh_400Regular'] text-[#36555E] mb-2">
        Estoque de Medicamentos
      </Text>
      {medicationStock.map((medication, index) => {
        const { totalDays, endDate } = calculateStockDuration(
          medication.stock,
          medication.frequency,
          medication.startDate
        );

        return (
          <View key={index} className="bg-white rounded-2xl p-4 my-3 shadow-md">
            <Text className="text-2xl font-bold text-[#36555E] mb-2">
              {medication.name}
            </Text>
            <Text className="text-lg text-black mb-1">
              Estoque atual: {medication.stock} unidades
            </Text>
            <Text className="text-lg text-black">
              Estoque durará {totalDays} dias (até{' '}
              {endDate.toLocaleDateString('pt-BR')})
            </Text>
          </View>
        );
      })}
    </ScrollView>
  );
}

export default MedicationStock;


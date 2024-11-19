import React, { useEffect, useState } from 'react';
import { View, Text, StyleSheet } from 'react-native';

// Componente responsável pelo Estoque de Medicamentos
export default function MedicationStock() {
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
    <View>
      {medicationStock.map((medication, index) => {
        const { totalDays, endDate } = calculateStockDuration(
          medication.stock,
          medication.frequency,
          medication.startDate
        );

        return (
          <View key={index} style={styles.stockItem}>
            <Text style={styles.medicationName}>{medication.name}</Text>
            <Text style={styles.stockInfo}>
              Estoque atual: {medication.stock} unidades
            </Text>
            <Text style={styles.stockInfo}>
              Estoque durará {totalDays} dias (até{' '}
              {endDate.toLocaleDateString('pt-BR')})
            </Text>
          </View>
        );
      })}
      </View>
  );
}

const styles = StyleSheet.create({
  card: {
    minHeight: 180,
    padding: 16,
    backgroundColor: '#FFFFFF',
    borderRadius: 16,
    shadowColor: '#000',
    shadowOffset: { width: 0, height: 2 },
    shadowOpacity: 0.25,
    shadowRadius: 4,
    elevation: 5,
  },
  sectionTitle: {
    fontSize: 40,
    fontFamily: 'Katibeh_400Regular',
    color: '#36555E',
    marginBottom: 10,
  },
  stockItem: {
    margin: 12,
    backgroundColor: '#FFFFFF',
    padding: 16,
    borderRadius: 16
  },
  medicationName: {
    fontSize: 24,
    fontWeight: 'bold',
    color: '#36555E',
  },
  stockInfo: {
    fontSize: 19,
    color: '#000',
  },
  blueContainer: {
    flex: 1,
    width: '100%',
    backgroundColor: '#D8F1F5',
    borderTopLeftRadius: 20,
    borderTopRightRadius: 20,
  },
});

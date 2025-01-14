import React, { useState } from 'react';
import { View, Text, TextInput, TouchableOpacity, ScrollView, useWindowDimensions, Alert } from 'react-native';
import { ArrowLeft } from 'lucide-react-native';
import { useFonts } from 'expo-font';
import { Katibeh_400Regular } from '@expo-google-fonts/katibeh';
import { Link } from 'expo-router';
import { Picker } from '@react-native-picker/picker';
import { DatePickerModal } from 'react-native-paper-dates';
import { Provider as PaperProvider } from 'react-native-paper';
import { ApiServices }  from '../../services/apiServices'; // Importar a classe ApiServices
import axios from 'axios';
import { parse } from 'date-fns';

const RegisterMedication = () => {
  const [medicationName, setMedicationName] = useState('');
  const [laboratoryName, setLaboratoryName] = useState('');
  const [dosage, setDosage] = useState('');
  const [startDate, setStartDate] = useState(new Date());
  const [frequency, setFrequency] = useState('');
  const [startTime, setStartTime] = useState('00:00');
  const [currentStock, setCurrentStock] = useState('');
  const [additionalInfo, setAdditionalInfo] = useState('');
  const [showDatePicker, setShowDatePicker] = useState(false);
  const [isLoading, setIsLoading] = useState(false);
  const [duracaoTratamento, setDuracaoTratamento] = useState('');

  const [fontsLoaded] = useFonts({
    Katibeh_400Regular,
  });
  const { width } = useWindowDimensions();
  const cardWidth = Math.min(width - 32, 768);

  // função para checar se uma data é válida e não está no passado
  const validateDate = (date: Date) => {
    const today = new Date();
    today.setHours(0, 0, 0, 0); // resete o tempo para comparar apenas a data
    return date >= today;
  };

  const validateTime = (time: string) => {
    const [hours, minutes] = time.split(':').map(Number);
    return hours >= 0 && hours < 24 && minutes >= 0 && minutes < 60;
  };

  const validateDosage = (dosage: string) => {
    const dosageValue = parseFloat(dosage);
    return dosageValue > 0 && dosageValue <= 100; // Exemplo de intervalo razoável
  };

  const validateStock = (stock: string) => {
    const stockValue = parseInt(stock, 10);
    return stockValue >= 0;
  };

  const frequencyOptions = [
    { label: '1 em 1 hora', value: 1 },
    { label: '2 em 2 horas', value: 2 },
    { label: '4 em 4 horas', value: 4 },
    { label: '6 em 6 horas', value: 6 },
    { label: '8 em 8 horas', value: 8 },
    { label: '12 em 12 horas', value: 12 },
    { label: 'A cada 24 horas', value: 24 },
    { label: 'A cada 48 horas', value: 48 },
  ];

  const timeOptions = Array.from({ length: 24 }, (_, i) => {
    const hour = i.toString().padStart(2, '0');
    return { label: `${hour}:00`, value: `${hour}:00` };
  });

  const clearFormData = () => {
    setMedicationName('');
    setLaboratoryName('');
    setDosage('');
    setStartDate(new Date());
    setFrequency('');
    setStartTime('00:00');
    setCurrentStock('');
    setAdditionalInfo('');
  };

  const onDateChange = ({ date }: { date: Date }) => {
    if (date && validateDate(date)) {
      setStartDate(date);
      setShowDatePicker(false);
    }
  };

  const handleSubmit = async () => {
    // Validação básica dos campos
    if (!medicationName.trim() || !dosage.trim() || !frequency.trim() || !currentStock.trim()) {
      Alert.alert('Erro', 'Por favor, preencha todos os campos obrigatórios.');
      console.log('Campos obrigatórios não preenchidos');
      return;
    }

    // Validação de horário
    if (!validateTime(startTime)) {
      Alert.alert('Erro', 'Por favor, insira um horário válido.');
      console.log('Horário inválido');
      return;
    }

    // Validação de dosagem
    if (!validateDosage(dosage)) {
      Alert.alert('Erro', 'Por favor, insira uma dosagem válida.');
      console.log('Dosagem inválida');
      return;
    }

    // Validação de estoque atual
    if (!validateStock(currentStock)) {
      Alert.alert('Erro', 'Por favor, insira uma quantidade de estoque válida.');
      console.log('Estoque inválido');
      return;
    }

    setIsLoading(true);

    // criar entidade de medicamento
    const medicamento = {
      id: 0,
      nome: medicationName,
      laboratorio: laboratoryName,
      dosagem: dosage,
      dataInicial: startDate,
      frequencia: frequency,
      horaInicial: startTime,
      quantidade: currentStock,
      observacao: additionalInfo,
      status: null,
      duracao: parseInt(duracaoTratamento),
    };

    // criar instância da classe ApiServices
    const apiServices = new ApiServices();

    // chamar a função createMedicamento da classe ApiServices
    try{
      const medicamentoId = await apiServices.createMedicamento(medicationName, laboratoryName);
      medicamento.id = parseInt(medicamentoId);
      // chamar a função addMedicamentoUsuario da classe ApiServices
      await apiServices.addMedicamentoUsuario(medicamento, 1);
      // chamar a função addEstoque da classe ApiServices
      await apiServices.addEstoque(medicamento, 1);
      clearFormData();
    } catch (error) {
      console.error('Erro ao cadastrar medicação:', error);
      Alert.alert('Erro', 'Não foi possível cadastrar a medicação. Tente novamente.');
    } finally {
      setIsLoading(false);
    }
  };

  return (
      <View className="flex-1 bg-[#D8F1F5]">
        {/* Header */}
        <View className="flex-row items-center p-4 bg-white shadow">
          <Link href="/home" onPress={clearFormData} asChild>
            <TouchableOpacity>
              <ArrowLeft size={24} color="#2F4858" />
            </TouchableOpacity>
          </Link>
          <Text className="text-xl font-semibold ml-4 text-[#2F4858]">Cadastrar medicação</Text>
        </View>

        <ScrollView className="flex-1 p-4">
          <View className="items-center">
            <View className="mb-6 mt-6 w-full" style={{ maxWidth: cardWidth }}>
              <View className="bg-white rounded-2xl p-4 sm:p-6 relative shadow-md">
                {/* Basic Data */}
                <View className="mb-6">
                  <Text className="text-[#36555E] text-4xl sm:text-5xl font-light" style={{ fontFamily: 'Katibeh_400Regular' }}>Dados Básicos</Text>

                  <Text className="text-lg mb-2 text-[#2F4858]">Nome da medicação</Text>
                  <TextInput
                    className="bg-[#F3F3F3] p-3 rounded-lg mb-4 text-lg text-[#2F4858]"
                    value={medicationName}
                    onChangeText={setMedicationName}
                    placeholder="Nome do medicamento"
                  />

                  <Text className="text-lg mb-2 text-[#2F4858]">Laboratório</Text>
                  <TextInput
                    className="bg-[#F3F3F3] p-3 rounded-lg mb-4 text-lg text-[#2F4858]"
                    value={laboratoryName}
                    onChangeText={setLaboratoryName}
                    placeholder="Nome do laboratório"
                  />

                  <Text className="text-lg mb-2 text-[#2F4858]">Dosagem</Text>
                  <View className="flex-row gap-2">
                    <TextInput
                      className="flex-1 bg-[#F3F3F3] p-3 rounded-lg text-lg text-[#2F4858]"
                      value={dosage}
                      onChangeText={setDosage}
                      keyboardType="numeric"
                      placeholder="Quantidade de medicamento por dose (ex.: 1, 2, 0.5)"
                    />
                    <View className="bg-[#F3F3F3] px-4 rounded-lg flex-row items-center justify-between min-w-[150px]">
                      <Text className="text-[#2F4858]">Comprimidos</Text>
                    </View>
                  </View>
                </View>

                {/* Schedule and Frequency */}
                <View className="mb-6">
                  <Text className="text-[#36555E] mt-4 text-4xl sm:text-5xl font-light" style={{ fontFamily: 'Katibeh_400Regular' }}>Horários e Frequência</Text>

                  <View className="flex-row gap-2 mb-4">
                    <View className="flex-1">
                      <Text className="text-lg mb-2 text-[#2F4858]">Data de início</Text>
                      <TouchableOpacity
                        className="bg-[#F3F3F3] p-3 rounded-lg"
                        onPress={() => setShowDatePicker(true)}
                      >
                        <Text className="text-lg text-[#2F4858]">
                          {startDate.toLocaleDateString()}
                        </Text>
                      </TouchableOpacity>
                      <PaperProvider>
                      <DatePickerModal
                        mode="single"
                        visible={showDatePicker}
                        onDismiss={() => setShowDatePicker(false)}
                        date={startDate}
                        onConfirm={onDateChange}
                        validDate={(date) => {
                          // Disable dates before today
                          const today = new Date();
                          today.setHours(0, 0, 0, 0);
                          return date >= today;
                        }}
                        saveLabel="Selecionar"
                        label="Selecione a data de início"
                        cancelLabel="Cancelar"
                      />
                      </PaperProvider>
                    </View>
                  </View>

                  <Text className="text-lg mb-2 text-[#2F4858]">Horário de início</Text>
                  <View className="bg-[#F3F3F3] rounded-lg mb-4">
                    <Picker
                      selectedValue={startTime}
                      onValueChange={(itemValue) => setStartTime(itemValue)}
                      className='bg-[#F3F3F3] p-3 rounded-lg'
                    >
                      {timeOptions.map((option) => (
                        <Picker.Item key={option.value} label={option.label} value={option.value} />
                      ))}
                    </Picker>
                  </View>

                  <Text className="text-lg mb-2 text-[#2F4858]">Frequência</Text>
                  <View className="bg-[#F3F3F3] rounded-lg mb-4">
                    <Picker
                      selectedValue={frequency}
                      onValueChange={(itemValue) => setFrequency(itemValue)}
                      className='bg-[#F3F3F3] p-3 rounded-lg'
                      testID="frequency-picker"
                    >
                      <Picker.Item label="Selecionar frequência" value="" />
                      {frequencyOptions.map((option) => (
                        <Picker.Item key={option.value} label={option.label} value={option.value} />
                      ))}
                    </Picker>
                  </View>

                  <Text className="text-lg mb-2 text-[#2F4858]">Duração do Tratamento</Text>
                  <View className="flex-row gap-2">
                    <TextInput
                      className="flex-1 bg-[#F3F3F3] p-3 rounded-lg text-lg text-[#2F4858]"
                      value={duracaoTratamento}
                      onChangeText={setDuracaoTratamento}
                      keyboardType="numeric"
                      placeholder="Preencha com a duração do tratamento em número de dias"
                    />
                    <View className="bg-[#F3F3F3] px-4 rounded-lg flex-row items-center justify-between min-w-[150px]">
                      <Text className="text-[#2F4858]">Dias</Text>
                    </View>
                  </View>
                </View>

                {/* Additional Details */}
                <View className="mb-6">
                  <Text className="text-[#36555E] mt-4 text-4xl sm:text-5xl font-light" style={{ fontFamily: 'Katibeh_400Regular' }}>Detalhes Adicionais</Text>

                  <Text className="text-lg mb-2 text-[#2F4858]">Quantidade Atual em Estoque</Text>
                  <View className="flex-row gap-2">
                    <TextInput
                      className="flex-1 bg-[#F3F3F3] p-3 rounded-lg text-lg text-[#2F4858]"
                      value={currentStock}
                      onChangeText={setCurrentStock}
                      keyboardType="numeric"
                      placeholder="Quantidade de comprimidos"
                    />
                    <View className="bg-[#F3F3F3] px-4 rounded-lg flex-row items-center justify-between min-w-[150px]">
                      <Text className="text-[#2F4858]">Comprimidos</Text>
                    </View>
                  </View>

                  <Text className="text-lg mb-2 mt-4 text-[#2F4858]">Informações Adicionais</Text>
                  <TextInput
                    className="bg-[#F3F3F3] p-3 rounded-lg text-lg text-[#2F4858] min-h-[100px]"
                    value={additionalInfo}
                    onChangeText={setAdditionalInfo}
                    multiline
                    textAlignVertical="top"
                    placeholder="Campo livre para o usuário adicionar informações extras (ex.: 'Tomar com água', 'Evitar em jejum')."
                  />
                </View>

                {/* Footer Buttons */}
                <View className="flex-row gap-4">
                  <Link href="/home" onPress={clearFormData} asChild>
                    <TouchableOpacity className="flex-1 bg-[#B73E3E] p-4 rounded-lg items-center">
                      <Text className="text-white font-semibold text-lg">Cancelar</Text>
                    </TouchableOpacity>
                  </Link>
                  <TouchableOpacity 
                    className="flex-1 bg-[#75B7D1] p-4 rounded-lg items-center"
                    onPress={handleSubmit}
                    disabled={isLoading}
                  >
                    <Text className="text-white font-semibold text-lg">
                      {isLoading ? 'Enviando...' : 'Confirmar'}
                    </Text>
                  </TouchableOpacity>
                </View>
              </View>
            </View>
          </View>
        </ScrollView>
      </View>
  );
}

export default RegisterMedication;
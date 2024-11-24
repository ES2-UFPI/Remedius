import React, { useState } from 'react';
import { View, Text, TextInput, TouchableOpacity, ScrollView, useWindowDimensions } from 'react-native';
import { ArrowLeft, ChevronDown } from 'lucide-react-native';
import { useFonts } from 'expo-font';
import { Katibeh_400Regular } from '@expo-google-fonts/katibeh';
import { Link } from 'expo-router';

const RegisterMedication = () => {
  const [medicationName, setMedicationName] = useState('');
  const [dosage, setDosage] = useState('');
  const [dosageUnit, setDosageUnit] = useState('Comprimido');
  const [startDate, setStartDate] = useState('');
  const [duration, setDuration] = useState('');
  const [frequency, setFrequency] = useState('');
  const [currentStock, setCurrentStock] = useState('');
  const [additionalInfo, setAdditionalInfo] = useState('');

  const [fontsLoaded] = useFonts({
    Katibeh_400Regular,
  });
  const { width } = useWindowDimensions();
  const cardWidth = Math.min(width - 32, 768);

  const clearFormData = () => {
    setMedicationName('');
    setDosage('');
    setDosageUnit('Comprimido');
    setStartDate('');
    setDuration('');
    setFrequency('');
    setCurrentStock('');
    setAdditionalInfo('');
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
                />

                <Text className="text-lg mb-2 text-[#2F4858]">Dosagem</Text>
                <View className="flex-row gap-2">
                  <TextInput
                    className="flex-1 bg-[#F3F3F3] p-3 rounded-lg text-lg text-[#2F4858]"
                    value={dosage}
                    onChangeText={setDosage}
                    keyboardType="numeric"
                  />
                  <TouchableOpacity className="bg-[#F3F3F3] px-4 rounded-lg flex-row items-center justify-between min-w-[150px]">
                    <Text className="text-[#2F4858]">{dosageUnit}</Text>
                    <ChevronDown size={20} color="#2F4858" />
                  </TouchableOpacity>
                </View>
              </View>

              {/* Schedule and Frequency */}
              <View className="mb-6">
                <Text className="text-[#36555E] mt-4 text-4xl sm:text-5xl font-light" style={{ fontFamily: 'Katibeh_400Regular' }}>Horários e Frequência</Text>

                <View className="flex-row gap-2 mb-4">
                  <View className="flex-1">
                    <Text className="text-lg mb-2 text-[#2F4858]">Data de início</Text>
                    <TextInput
                      className="bg-[#F3F3F3] p-3 rounded-lg text-lg text-[#2F4858]"
                      value={startDate}
                      onChangeText={setStartDate}
                      placeholder="__/__/____"
                    />
                  </View>
                  <View className="flex-1">
                    <Text className="text-lg mb-2 text-[#2F4858]">Duração do tratamento</Text>
                    <TouchableOpacity className="bg-[#F3F3F3] p-3 rounded-lg flex-row items-center justify-between">
                      <Text className="text-[#2F4858]">Selecionar</Text>
                      <ChevronDown size={20} color="#2F4858" />
                    </TouchableOpacity>
                  </View>
                </View>

                <Text className="text-lg mb-2 text-[#2F4858]">Horários</Text>
                <TouchableOpacity className="bg-[#F3F3F3] p-3 rounded-lg mb-4 flex-row items-center justify-between">
                  <Text className="text-[#2F4858]">Selecionar horário</Text>
                  <ChevronDown size={20} color="#2F4858" />
                </TouchableOpacity>

                <Text className="text-lg mb-2 text-[#2F4858]">Frequência</Text>
                <TouchableOpacity className="bg-[#F3F3F3] p-3 rounded-lg flex-row items-center justify-between">
                  <Text className="text-[#2F4858]">Selecionar frequência</Text>
                  <ChevronDown size={20} color="#2F4858" />
                </TouchableOpacity>
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
                  />
                  <TouchableOpacity className="bg-[#F3F3F3] px-4 rounded-lg flex-row items-center justify-between min-w-[150px]">
                    <Text className="text-[#2F4858]">{dosageUnit}</Text>
                    <ChevronDown size={20} color="#2F4858" />
                  </TouchableOpacity>
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
              <View className="flex-row gap-4 p-4shadow-lg">
                <Link href="/home" onPress={clearFormData} asChild>
                  <TouchableOpacity className="flex-1 bg-[#B73E3E] p-4 rounded-lg items-center">
                    <Text className="text-white font-semibold text-lg">Cancelar</Text>
                  </TouchableOpacity>
                </Link>
                <TouchableOpacity className="flex-1 bg-[#75B7D1] p-4 rounded-lg items-center">
                  <Text className="text-white font-semibold text-lg">Confirmar</Text>
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
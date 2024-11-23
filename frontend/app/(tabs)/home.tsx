import MedicationSchedule from '@/components/MedicationSchedule';
import MedicationStock from '@/components/MedicationStock';
import React from 'react';
import { View, Text, ScrollView, TouchableOpacity } from 'react-native';
import { SafeAreaView } from 'react-native-safe-area-context';

const MedicationCard = ({ name, dosage, time }) => (
  <View className="bg-[#D8F1F5] rounded-md p-3 space-y-1">
    <Text className="text-lg font-semibold text-gray-800">{name}</Text>
    <Text className="text-base text-gray-600">{dosage}</Text>
    <Text className="text-[#5B8FB3]">{time}</Text>
  </View>
);

const Home = () => {
  return (
    <SafeAreaView className="flex-1 bg-[#D8F1F5]">
      <ScrollView className="flex-1 p-6">

        {/* Seção para a próxima medicação */}
        <View className="bg-[#74B2C3] rounded-lg mb-6 p-4 shadow-sm space-y-4">
          <Text className="text-lg font-semibold text-gray-800">Próxima medicação</Text>
          <MedicationCard name="ZOLPIDEM" dosage="1 comp." time="HOJE: 07:00" />
        </View>

        {/* Seção para o cronograma de medicações */}
        <View className="bg-[#74B2C3] rounded-lg mb-6 p-4 shadow-sm space-y-4">
          <Text className="text-lg font-semibold text-gray-800">Cronograma</Text>
          <MedicationSchedule />
        </View>

      </ScrollView>
    </SafeAreaView>
  );
}

export default Home
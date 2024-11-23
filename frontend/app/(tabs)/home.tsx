import MedicationSchedule from '@/components/MedicationSchedule';
import React from 'react';
import { View, Text, ScrollView, TouchableOpacity, useWindowDimensions } from 'react-native';
import { SafeAreaView } from 'react-native-safe-area-context';
import { Menu, Bell, User, FileText, Plus } from 'lucide-react-native';
import { useFonts, Katibeh_400Regular } from '@expo-google-fonts/katibeh';

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

/*
const Header = () => (
  <View className="flex-row justify-between items-center px-4 py-3">
    <TouchableOpacity>
      <Menu size={24} color="#2D3648" />
    </TouchableOpacity>
    <Text className="text-[#2D3648] text-xl font-semibold">Remedius</Text>
    <TouchableOpacity>
      <Bell size={24} color="#2D3648" />
    </TouchableOpacity>
  </View>
);
*/

const Home = () => {
  const [fontsLoaded] = useFonts({
    Katibeh_400Regular,
  });
  const { width } = useWindowDimensions();
  const cardWidth = Math.min(width - 32, 1024); // 1024px is equivalent to max-w-screen-lg

  return (
    <SafeAreaView className="flex-1 bg-[#E8F4F6]">
      <ScrollView className="flex-1 px-4">
        <View className="items-center">
          {/* Seção para a próxima medicação */}
          <View className="mb-6 mt-6 w-full" style={{ maxWidth: cardWidth }}>
            <View className="bg-white rounded-2xl p-4 sm:p-6 relative shadow-md">
              <Text className="text-[#36555E] text-4xl sm:text-5xl font-light" style={{ fontFamily: 'Katibeh_400Regular' }}>Próxima medicação</Text>
              <MedicationCard 
                name="ZOLPIDEM" 
                dosage="1 comp." 
                time="HOJE: 07:00" 
              />
            </View>
          </View>

          {/* Seção para o cronograma de medicações */}
          <View className="mb-6 w-full" style={{ maxWidth: cardWidth }}>
            <View className="bg-white rounded-2xl p-4 sm:p-6 relative shadow-md">
              <Text className="text-[#36555E] text-4xl sm:text-5xl font-light" style={{ fontFamily: 'Katibeh_400Regular' }}>Cronograma</Text>
              <MedicationSchedule />
            </View>
          </View>
        </View>
      </ScrollView>
    </SafeAreaView>
  );
}

export default Home;


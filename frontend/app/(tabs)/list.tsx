import React, { useRef } from 'react';
import { View, Text, ScrollView, TouchableOpacity, TextInput, useWindowDimensions, Animated } from 'react-native';
import { ArrowLeft, Edit2, Bell } from 'lucide-react-native';
import { SafeAreaView } from 'react-native-safe-area-context';
import { useFonts } from 'expo-font';
import { Katibeh_400Regular } from '@expo-google-fonts/katibeh';
import MedicationStock from '@/components/MedicationStock';
import AnimatedHeader from '../../components/Header';

const MedicationList = () => {
  const [fontsLoaded] = useFonts({
    Katibeh_400Regular,
  });
  const { width } = useWindowDimensions();
  const cardWidth = Math.min(width - 32, 768); // 768px is equivalent to max-w-screen-md
  const scrollY = useRef(new Animated.Value(0)).current; // Gerencia o estado de scroll
  
  return (
    <SafeAreaView className="flex-1 bg-[#D8F1F5]">

      {/* Header Animado */}
      <AnimatedHeader scrollY={scrollY} />

      {/* Conteúdo com scroll detectável */}
      <Animated.ScrollView
        className="flex-1"
        contentContainerStyle={{ paddingTop: 100 }} // Espaço para o header
        onScroll={Animated.event(
          [{ nativeEvent: { contentOffset: { y: scrollY } } }],
          { useNativeDriver: true } // Usa animações nativas para desempenho
        )}
        scrollEventThrottle={16} // Atualiza o evento a cada 16ms
      >
      {/*<ScrollView className="flex-1 p-4">*/}
        <View className="items-center">
          {/* Active Medications */}
          <View className="mb-6 mt-6 w-full" style={{ maxWidth: cardWidth }}>
            <View className="bg-white rounded-2xl p-4 sm:p-6 relative shadow-md">
              <Text className="text-[#36555E] text-4xl sm:text-5xl font-light" style={{ fontFamily: 'Katibeh_400Regular' }}>Medicações ativas</Text>
              <View className="flex-row justify-between items-center p-4 rounded-lg mb-2 bg-[#FFE4E4]">
                <Text className="text-base text-[#2F4858]">Zolpidem</Text>
                <View className="flex-row gap-3">
                  <TouchableOpacity className="p-1">
                    <Edit2 size={20} color="#2F4858" />
                  </TouchableOpacity>
                  <TouchableOpacity className="p-1">
                    <Bell size={20} color="#2F4858" />
                  </TouchableOpacity>
                </View>
              </View>
              <View className="flex-row justify-between items-center p-4 rounded-lg mb-2 bg-[#E4F2FF]">
                <Text className="text-base text-[#2F4858]">Dipirona</Text>
                <View className="flex-row gap-3">
                  <TouchableOpacity className="p-1">
                    <Edit2 size={20} color="#2F4858" />
                  </TouchableOpacity>
                  <TouchableOpacity className="p-1">
                    <Bell size={20} color="#2F4858" />
                  </TouchableOpacity>
                </View>
              </View>
              <View className="flex-row justify-between items-center p-4 rounded-lg mb-2 bg-[#E4FFE8]">
                <Text className="text-base text-[#2F4858]">Mirtazapina</Text>
                <View className="flex-row gap-3">
                  <TouchableOpacity className="p-1">
                    <Edit2 size={20} color="#2F4858" />
                  </TouchableOpacity>
                  <TouchableOpacity className="p-1">
                    <Bell size={20} color="#2F4858" />
                  </TouchableOpacity>
                </View>
              </View>
            </View>
          </View>

          {/* Suspended Medications */}
          <View className="mb-6 w-full" style={{ maxWidth: cardWidth }}>
            <View className="bg-white rounded-2xl p-4 sm:p-6 relative shadow-md">
              <Text className="text-[#36555E] text-4xl sm:text-5xl font-light" style={{ fontFamily: 'Katibeh_400Regular' }}>Medicações suspensas</Text>
              <View className="flex-row justify-between items-center p-4 rounded-lg mb-2 bg-[#E5E5E5]">
                <Text className="text-base text-[#2F4858]">Glifage</Text>
                <View className="flex-row gap-3">
                  <TouchableOpacity className="p-1">
                    <Edit2 size={20} color="#2F4858" />
                  </TouchableOpacity>
                  <TouchableOpacity className="p-1">
                    <Bell size={20} color="#2F4858" />
                  </TouchableOpacity>
                </View>
              </View>
              <View className="flex-row justify-between items-center p-4 rounded-lg mb-2 bg-[#E5E5E5]">
                <Text className="text-base text-[#2F4858]">Buscopam</Text>
                <View className="flex-row gap-3">
                  <TouchableOpacity className="p-1">
                    <Edit2 size={20} color="#2F4858" />
                  </TouchableOpacity>
                  <TouchableOpacity className="p-1">
                    <Bell size={20} color="#2F4858" />
                  </TouchableOpacity>
                </View>
              </View>
            </View>
          </View>

          {/* Medication Stock */}
          <View className="mb-6 w-full" style={{ maxWidth: cardWidth }}>
            <View className="bg-white rounded-2xl p-4 sm:p-6 relative shadow-md">
              <Text className="text-[#36555E] text-4xl sm:text-5xl font-light" style={{ fontFamily: 'Katibeh_400Regular' }}>Estoque de medicações</Text>
                <MedicationStock />
            </View>
          </View>
        </View>
      {/*</ScrollView>*/}
      </Animated.ScrollView>
    </SafeAreaView>
  );
}

export default MedicationList;


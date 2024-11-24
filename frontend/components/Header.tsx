import React, { useRef } from 'react';
import { Animated, View, TouchableOpacity, Text } from 'react-native';
import { Ionicons } from '@expo/vector-icons';
import { useFonts, Katibeh_400Regular } from '@expo-google-fonts/katibeh';

const AnimatedHeader = ({ scrollY }) => {
  const [fontsLoaded] = useFonts({
    Katibeh_400Regular,
  });

  // Translada o header com base no scrollY
  const translateY = scrollY.interpolate({
    inputRange: [0, 100], // Intervalo de scroll
    outputRange: [0, -100], // Movimento do header para cima
    extrapolate: 'clamp', // Evita valores fora do intervalo
  });

  return (
    <Animated.View
      style={{
        transform: [{ translateY }],
        position: 'absolute',
        top: 0,
        left: 0,
        right: 0,
        zIndex: 10,
        backgroundColor: 'white',
        shadowOpacity: 0.2,
        shadowRadius: 5,
        elevation: 5,
      }}
    >
      <View className="flex-row justify-between items-center px-10 py-5">
        <TouchableOpacity>
          <Ionicons name="menu" size={24} color="#333" />
        </TouchableOpacity>
        <Text
          className="text-[#36555E] text-5xl mt-4 font-normal"
          style={{ fontFamily: 'Katibeh_400Regular' }}
        >
          Remedius
        </Text>
        <TouchableOpacity>
          <Ionicons name="notifications-outline" size={24} color="#333" />
        </TouchableOpacity>
      </View>
    </Animated.View>
  );
};

export default AnimatedHeader;

import { View, Text, TouchableOpacity, Image } from 'react-native'
import React from 'react'
import { Tabs } from 'expo-router'
import { Ionicons } from '@expo/vector-icons'
import { SafeAreaView } from 'react-native-safe-area-context'
import { useFonts, Katibeh_400Regular } from '@expo-google-fonts/katibeh';

const Header = () => {
  const [fontsLoaded] = useFonts({
    Katibeh_400Regular,
  });
  
  return (
    <View className="flex-row justify-between items-center px-10 py-5 bg-white rounded shadow-lg">
      <TouchableOpacity>
        <Ionicons name="menu" size={24} color="#333" />
      </TouchableOpacity>
      <Text className="text-[#36555E] text-5xl mt-4 font-normal" style={{ fontFamily: 'Katibeh_400Regular' }}>Remedius</Text>
      <TouchableOpacity>
        <Ionicons name="notifications-outline" size={24} color="#333" />
      </TouchableOpacity>
    </View>
  )
}

const TabsLayout = () => {
  return (
    <SafeAreaView className="flex-1 bg-[#F0F4F8]">
      <Header />
      <Tabs
        screenOptions={{
          headerShown: false,
          tabBarStyle: {
            backgroundColor: '#74B2C3',
            borderTopColor: '#E5E7EB',
            borderTopWidth: 1,
            height: 60,
          },
          tabBarActiveTintColor: '[#66919D]',
          tabBarInactiveTintColor: 'white',
          tabBarLabelStyle: {
            fontSize: 12,
            marginBottom: 4,
          },
        }}
      >
        <Tabs.Screen
          name="home"
          options={{
            title: 'home',
            tabBarIcon: ({ color, size }) => (
              <Ionicons name="home-outline" color={color} size={size} />
            )
          }} />
        <Tabs.Screen
          name="list"
          options={{
            title: 'list',
            tabBarIcon: ({ color, size }) => (
              <Ionicons name="list-outline" color={color} size={size} />
            )
          }} />
        <Tabs.Screen
          name="add"
          options={{
            title: 'add',
            tabBarIcon: ({ color, size }) => (
              <Ionicons name="add" color={color} size={size} />
            )
          }} />
        <Tabs.Screen
          name="placeholder"
          options={{
            title: 'placeholder',
            tabBarIcon: ({ color, size }) => (
              <Ionicons name="document-text-outline" color={color} size={size} />
            )
          }} />
        <Tabs.Screen
          name="profile"
          options={{
            title: 'profile',
            tabBarIcon: ({ color, size }) => (
              <Ionicons name="person-outline" color={color} size={size} />
            )
          }} />
      </Tabs>
    </SafeAreaView>
  )
}

export default TabsLayout

import { View, Text } from 'react-native'
import React from 'react'
import { SafeAreaView } from 'react-native-safe-area-context'
import { ScrollView } from 'react-native-gesture-handler'
import { Link } from 'expo-router'
import "../global.css"

const Index = () => {
  return (
    <SafeAreaView className="flex-1">
      <ScrollView contentContainerStyle={{ flexGrow: 1 }}>
        <View className="flex-1 justify-center items-center">
          <Text className="text-center text-blue-500">
            <Link href="/(tabs)/home">Go to Home</Link>
          </Text>
        </View>
      </ScrollView>
    </SafeAreaView>
  )
}

export default Index
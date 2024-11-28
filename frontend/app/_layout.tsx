import { View, Text } from 'react-native'
import React, { useEffect } from 'react'
import { SplashScreen, Stack } from 'expo-router'
import { useFonts } from 'expo-font';
import { GestureHandlerRootView } from 'react-native-gesture-handler';

// Prevent the splash screen from auto-hiding before asset loading is complete.
SplashScreen.preventAutoHideAsync();

const RootLayout = () => {
  const [loaded] = useFonts({
    SpaceMono: require('../assets/fonts/SpaceMono-Regular.ttf'),
  });

  useEffect(() => {
    if (loaded) {
      SplashScreen.hideAsync();
    }
  }, [loaded]);

  if (!loaded) {
    return null;
  }

  return (
    <GestureHandlerRootView className='flex-1'>
      <Stack
        screenOptions={{
          headerShown: false
        }}
      >
        <Stack.Screen
          name="(tabs)"
        />
        <Stack.Screen
          name="index"
        />
        <Stack.Screen
          name="edit"
        />
      </Stack>
    </GestureHandlerRootView>
  )
}

export default RootLayout
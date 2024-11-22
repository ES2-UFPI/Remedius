import React from 'react';
import { View, Text, StyleSheet, TouchableOpacity, ScrollView } from 'react-native';
import { Ionicons } from '@expo/vector-icons';
import { SafeAreaView } from 'react-native-safe-area-context';
import { useFonts, Katibeh_400Regular } from '@expo-google-fonts/katibeh';
import * as SplashScreen from 'expo-splash-screen';
import MedicationSchedule from '../../components/MedicationSchedule';
import MedicationStock from '../../components/MedicationStock';
import "../../global.css"

// Mantem a splash screen visível enquanto carregamos as fontes
SplashScreen.preventAutoHideAsync();

export default function App() {
  const [fontsLoaded] = useFonts({
    Katibeh_400Regular,
  });

  const onLayoutRootView = async () => {
    if (fontsLoaded) {
      await SplashScreen.hideAsync();
    }
  };

  if (!fontsLoaded) {
    return null;
  }

  // pega a próxima medicação de uma requisição ao backend
  let nextMedication = ["Dipirona", "8:00", "2 comprimidos"];

  return (
    <SafeAreaView style={styles.container} onLayout={onLayoutRootView}>
      <View style={styles.header}>
        <TouchableOpacity style={styles.menuButton}>
          <Ionicons name="menu" size={30} color="#36555E" />
        </TouchableOpacity>
        <Text style={styles.logoText}>Remedius</Text>
        <TouchableOpacity style={styles.notificationsButton}>
          <Ionicons name="notifications" size={30} color="#36555E" />
        </TouchableOpacity>
      </View>

      <View style={styles.blueContainer}>
        <ScrollView contentContainerStyle={styles.scrollContainer} showsVerticalScrollIndicator={false}>
          <View style={styles.section}>
            <View style={styles.card}>
              <Text style={styles.sectionTitle}>Próxima medicação</Text>
              <View style={styles.medicationInfo}>
                <View>
                  <Text style={styles.nextMedicationName}>{nextMedication[0]}</Text>
                  <Text style={styles.nextMedicationDosage}>{nextMedication[2]}</Text>
                </View>
                <View>
                  <View style={styles.timeTag}>
                    <Text style={styles.timeText}>Horário: {nextMedication[1]}</Text>
                  </View>
                </View>
              </View>
            </View>
          </View>

          <View style={styles.section}>
            <View style={styles.card}>
              <Text style={styles.sectionTitle}>Cronograma</Text>
              <MedicationSchedule />
            </View>
          </View>

          
          <View style={styles.section}>
            <View style={styles.card}>
              <Text style={styles.sectionTitle}>Estoque de Medicamentos</Text>
              <View style={styles.blueContainer}>
                <MedicationStock />
              </View>
            </View>
          </View>

        </ScrollView>
      </View>
    </SafeAreaView>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#FFFFFF',
  },
  header: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    width: '100%',
    height: 80,
    backgroundColor: 'white',
  },
  menuButton: {
    padding: 20,
  },
  notificationsButton: {
    padding: 20,
  },
  logoText: {
    fontFamily: 'Katibeh_400Regular',
    fontSize: 45,
    color: '#36555E',
  },
  blueContainer: {
    flex: 1,
    width: '100%',
    alignItems: 'center',
    backgroundColor: '#D8F1F5',
    borderTopLeftRadius: 20,
    borderTopRightRadius: 20,
  },
  scrollContainer: {
    paddingBottom: 16, // Para espaçamento extra no final
    alignItems: 'center',
    maxWidth: 850
  },
  section: {
    width: '98%',
    padding: 16,
    gap: 16,
  },
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
  medicationInfo: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
  },
  nextMedicationName: {
    fontSize: 18,
    fontWeight: 'bold',
    color: '#36555E',
  },
  nextMedicationDosage: {
    fontSize: 16,
    color: '#666',
  },
  timeTag: {
    backgroundColor: '#D8F1F5',
    padding: 18,
    borderRadius: 40,
  },
  timeText: {
    color: '#000000',
    fontWeight: '800',
    fontSize: 18,
  },
});

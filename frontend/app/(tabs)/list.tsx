import type React from "react"
import { useState, useEffect, useRef } from "react"
import { View, Text, TouchableOpacity, useWindowDimensions, Animated } from "react-native"
import { Edit2, Bell } from "lucide-react-native"
import { SafeAreaView } from "react-native-safe-area-context"
import { useFonts } from "expo-font"
import { Katibeh_400Regular } from "@expo-google-fonts/katibeh"
import axios from "axios"
import AnimatedHeader from "../../components/Header"
import { router } from "expo-router"
import { MedicationPrototype } from "../../utils/MedicationPrototype"

// Define paleta de cores para as medicações
const MEDICATION_COLORS = [
  "#FFE4E4", // Light Red
  "#E4F2FF", // Light Blue
  "#E4FFE8", // Light Green
  "#FFF4E4", // Light Orange
  "#F4E4FF", // Light Purple
]

interface UsuarioMedicamento {
  id: number
  usuario: {
    id: number
    nome: string
    email: string
    senha: string
    peso: number | null
    altura: number | null
    dataNascimento: string | null
  }
  medicamento: {
    id: number
    nome: string
    laboratorio: string
    cor: string | null
  }
  cor: string
  tratamentos: Array<{
    id: number
    dataInicial: string
    frequencia: number
    duracao: number
    dosagem: number
    observacao: string
    status: string | null
    ativo: boolean
    eventos: Array<{
      id: number
      horario: string
      status: string
    }>
  }>
}

interface MedicationCardProps {
  name: string
  color: string
  medication: UsuarioMedicamento
}

const MedicationCard: React.FC<MedicationCardProps> = ({ name, color, medication }) => {
  const handleEdit = () => {
    const prototype = new MedicationPrototype()
      .setId(medication.id)
      .setNome(medication.medicamento.nome)
      .setLaboratorio(medication.medicamento.laboratorio)
      .setCor(medication.cor)

    if (medication.tratamentos.length > 0) {
      const tratamento = medication.tratamentos[0]
      prototype
        .setDataInicial(new Date(tratamento.dataInicial))
        .setFrequencia(tratamento.frequencia.toString())
        .setDosagem(tratamento.dosagem.toString())
        .setDuracaoTratamento(tratamento.duracao.toString())
        .setObservacao(tratamento.observacao)
        .setStatus(tratamento.status)
    }

    router.push({
      pathname: "/edit",
      params: { medication: JSON.stringify(prototype) },
    })
  }

  return (
    <View style={{ backgroundColor: color }} className="flex-row justify-between items-center p-4 rounded-lg mb-2">
      <Text className="text-base text-[#2F4858]">{name}</Text>
      <View className="flex-row gap-3">
        <TouchableOpacity className="p-1" onPress={handleEdit}>
          <Edit2 size={20} color="#2F4858" />
        </TouchableOpacity>
        <TouchableOpacity className="p-1">
          <Bell size={20} color="#2F4858" />
        </TouchableOpacity>
      </View>
    </View>
  )
}

const MedicationList = () => {
  const [fontsLoaded] = useFonts({
    Katibeh_400Regular,
  })
  const { width } = useWindowDimensions()
  const cardWidth = Math.min(width - 32, 768)
  const scrollY = useRef(new Animated.Value(0)).current

  const [medications, setMedications] = useState<UsuarioMedicamento[]>([])

  useEffect(() => {
    const fetchMedications = async () => {
      try {
        const response = await axios.get("http://localhost:8080/usuario-medicamentos")
        setMedications(response.data)
      } catch (error) {
        console.error("Error fetching medications:", error)
      }
    }

    fetchMedications()
  }, [])

  const activeMedications = medications.filter((med) => med.tratamentos.some((t) => t.ativo))
  const suspendedMedications = medications.filter((med) => !med.tratamentos.some((t) => t.ativo))

  return (
    <SafeAreaView className="flex-1 bg-[#D8F1F5]">
      <AnimatedHeader scrollY={scrollY} />

      <Animated.ScrollView
        className="flex-1"
        contentContainerStyle={{ paddingTop: 100 }}
        onScroll={Animated.event([{ nativeEvent: { contentOffset: { y: scrollY } } }], { useNativeDriver: true })}
        scrollEventThrottle={16}
      >
        <View className="items-center">
          {/* Active Medications */}
          <View className="mb-6 mt-6 w-full" style={{ maxWidth: cardWidth }}>
            <View className="bg-white rounded-2xl p-4 sm:p-6 relative shadow-md">
              <Text
                className="text-[#36555E] text-4xl sm:text-5xl font-light"
                style={{ fontFamily: "Katibeh_400Regular" }}
              >
                Medicações ativas
              </Text>
              {activeMedications.length === 0 ? ( <Text className="text-gray-800 text-lg mt-4">Nenhuma medicação ativa</Text>
                ) : (activeMedications.map((medication, index) => (
                <MedicationCard
                  key={medication.id}
                  name={medication.medicamento.nome}
                  color={MEDICATION_COLORS[index % MEDICATION_COLORS.length]}
                  medication={medication}
                />
              )))}
            </View>
          </View>

          {/* Suspended Medications */}
          <View className="mb-6 w-full" style={{ maxWidth: cardWidth }}>
            <View className="bg-white rounded-2xl p-4 sm:p-6 relative shadow-md">
              <Text
                className="text-[#36555E] text-4xl sm:text-5xl font-light"
                style={{ fontFamily: "Katibeh_400Regular" }}
              >
                Medicações suspensas
              </Text>
              {suspendedMedications.length === 0 ? ( <Text className="text-gray-800 text-lg mt-4">Nenhuma medicação suspensa</Text>
                ) : (suspendedMedications.map((medication, index) => (
                <MedicationCard
                  key={medication.id}
                  name={medication.medicamento.nome}
                  color="#E5E5E5"
                  medication={medication}
                />
              )))}
            </View>
          </View>
        </View>
      </Animated.ScrollView>
    </SafeAreaView>
  )
}

export default MedicationList


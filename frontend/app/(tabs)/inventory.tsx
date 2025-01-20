import React, { useRef, useState, useEffect } from "react"
import { View, Text, TouchableOpacity, ScrollView, SafeAreaView, useWindowDimensions, Animated } from "react-native"
import { ArrowLeft, Edit, Bell, ChevronDown, ChevronUp } from "lucide-react-native"
import { useFonts, Katibeh_400Regular } from "@expo-google-fonts/katibeh"
import axios from "axios"

const BACKGROUND_COLORS = ["bg-blue-100", "bg-purple-100", "bg-yellow-100", "bg-pink-100", "bg-green-100"]

interface Medication {
  id: number
  usuarioMedicamento: {
    id: number
    medicamento: {
      id: number
      nome: string
      laboratorio: string
      cor: string | null
    }
    cor: string
  }
  quantidade: number
  ultimaCompra: string
  status: "ATIVO" | "SUSPENSO"
}

interface MedicationCardProps {
  name: string
  laboratory: string
  quantity: number
  lastPurchase: string
  status: "ATIVO" | "SUSPENSO"
  color: string
  index: number
  onEdit: () => void
  onNotification: () => void
}

const MedicationCard = ({
  name,
  laboratory,
  quantity,
  lastPurchase,
  status,
  color,
  index,
  onEdit,
  onNotification,
}: MedicationCardProps) => {
  const [expanded, setExpanded] = useState(false)
  const animation = useRef(new Animated.Value(0)).current

  const toggleExpand = () => {
    setExpanded(!expanded)
    Animated.timing(animation, {
      toValue: expanded ? 0 : 1,
      duration: 300,
      useNativeDriver: false,
    }).start()
  }

  const contentHeight = animation.interpolate({
    inputRange: [0, 1],
    outputRange: [0, 210],
  })

  const getBgColor = () => {
    if (status === "SUSPENSO") return "bg-gray-200"
    return BACKGROUND_COLORS[index % BACKGROUND_COLORS.length]
  }

  const formatDate = (dateString: string) => {
    const date = new Date(dateString)
    return date.toLocaleDateString("pt-BR")
  }

  return (
    <View className={`${getBgColor()} rounded-lg p-4 mb-3`}>
      <TouchableOpacity onPress={toggleExpand} className="flex-row justify-between items-center">
        <View>
          <Text className="text-gray-800 text-2xl font-semibold">{name}</Text>
          <Text className="text-gray-600 text-sm">{laboratory}</Text>
        </View>
        {expanded ? (
          <ChevronUp className="text-gray-600" size={24} />
        ) : (
          <ChevronDown className="text-gray-600" size={24} />
        )}
      </TouchableOpacity>

      <Animated.View style={{ height: contentHeight, overflow: "hidden" }}>
        <View className="bg-white rounded-2xl p-4 sm:p-6 mt-4 mb-3">
          <Text className="text-gray-900 mb-1 text-lg">Quantidade: {quantity}</Text>
          <Text className="text-gray-900 mb-1 text-lg">Última compra: {formatDate(lastPurchase)}</Text>
        </View>

        <View className="flex-row justify-between">
          <TouchableOpacity onPress={onEdit} className="p-2">
            <View className="flex-row bg-white rounded-2xl p-2">
              <Edit className="text-gray-600" size={20} />
              <Text
                className="text-[#36555E] pl-2 mb-[-15px] text-2xl sm:text-3xl font-light"
                style={{ fontFamily: "Katibeh_400Regular" }}
              >
                Editar Estoque
              </Text>
            </View>
          </TouchableOpacity>

          <TouchableOpacity onPress={onNotification} className="p-2">
            <View className="flex-row bg-white rounded-2xl p-2">
              <Bell size={20} className="text-gray-600 mr-2" />
              <Text
                className="text-[#36555E] mb-[-15px] text-2xl sm:text-3xl font-light"
                style={{ fontFamily: "Katibeh_400Regular" }}
              >
                Notificações
              </Text>
            </View>
          </TouchableOpacity>
        </View>
      </Animated.View>
    </View>
  )
}

const Inventory = () => {
  const [fontsLoaded] = useFonts({
    Katibeh_400Regular,
  })
  const { width } = useWindowDimensions()
  const cardWidth = Math.min(width - 32, 768)
  const scrollY = useRef(new Animated.Value(0)).current

  const [medications, setMedications] = useState<Medication[]>([])

  useEffect(() => {
    fetchMedications()
  }, [])

  const fetchMedications = async () => {
    try {
      const response = await axios.get("http://localhost:8080/estoque")
      setMedications(response.data)
    } catch (error) {
      console.error("Erro ao buscar medicações:", error)
    }
  }

  const activeMedications = medications.filter((med) => med.status === "ATIVO")
  const suspendedMedications = medications.filter((med) => med.status === "SUSPENSO")

  if (!fontsLoaded) {
    return null
  }

  return (
    <SafeAreaView className="flex-1 bg-[#D8F1F5]">
      <View className="flex-1">
        <View className="flex-row items-center p-4 bg-white">
          <TouchableOpacity
            accessible={true}
            accessibilityLabel="Voltar"
            accessibilityHint="Navega para a tela anterior"
          >
            <ArrowLeft size={24} className="text-gray-800" />
          </TouchableOpacity>
          <Text className="text-xl font-semibold ml-4 text-gray-800">Estoque</Text>
        </View>

        <ScrollView className="flex-1 px-4 pt-4">
          <View className="items-center">
            <View className="mb-6 mt-6 w-full" style={{ maxWidth: cardWidth }}>
              <View className="bg-white rounded-2xl p-4 sm:p-6 relative shadow-md">
                <Text
                  className="text-[#36555E] text-4xl sm:text-5xl font-light"
                  style={{ fontFamily: "Katibeh_400Regular" }}
                >
                  Estoque de medicações ativas
                </Text>
                {activeMedications.length === 0 ? (
                  <Text className="text-gray-800 text-lg mt-4">Nenhuma medicação ativa</Text>
                ) : (
                  activeMedications.map((med, index) => (
                    <MedicationCard
                      key={med.id}
                      name={med.usuarioMedicamento.medicamento.nome}
                      laboratory={med.usuarioMedicamento.medicamento.laboratorio}
                      quantity={med.quantidade}
                      lastPurchase={med.ultimaCompra}
                      status={med.status}
                      color={med.usuarioMedicamento.cor}
                      index={index}
                      onEdit={() => console.log("Edit", med.usuarioMedicamento.medicamento.nome)}
                      onNotification={() => console.log("Notification", med.usuarioMedicamento.medicamento.nome)}
                    />
                  ))
                )}
              </View>
            </View>

            <View className="mb-6 mt-6 w-full" style={{ maxWidth: cardWidth }}>
              <View className="bg-white rounded-2xl p-4 sm:p-6 relative shadow-md">
                <Text
                  className="text-[#36555E] text-4xl sm:text-5xl font-light"
                  style={{ fontFamily: "Katibeh_400Regular" }}
                >
                  Estoque de medicações suspensas
                </Text>
                {suspendedMedications.length === 0 ? (
                  <Text className="text-gray-800 text-lg mt-4">Nenhuma medicação suspensa</Text>
                ) : (
                  suspendedMedications.map((med, index) => (
                    <MedicationCard
                      key={med.id}
                      name={med.usuarioMedicamento.medicamento.nome}
                      laboratory={med.usuarioMedicamento.medicamento.laboratorio}
                      quantity={med.quantidade}
                      lastPurchase={med.ultimaCompra}
                      status={med.status}
                      color={med.usuarioMedicamento.cor}
                      index={index + activeMedications.length}
                      onEdit={() => console.log("Edit", med.usuarioMedicamento.medicamento.nome)}
                      onNotification={() => console.log("Notification", med.usuarioMedicamento.medicamento.nome)}
                    />
                  ))
                )}
              </View>
            </View>
          </View>
        </ScrollView>
      </View>
    </SafeAreaView>
  )
}

export default Inventory


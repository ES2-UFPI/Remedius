import React, { useState } from "react"
import { View, Text, TextInput, TouchableOpacity, ScrollView, useWindowDimensions, Alert } from "react-native"
import { ArrowLeft } from "lucide-react-native"
import { useFonts } from "expo-font"
import { Katibeh_400Regular } from "@expo-google-fonts/katibeh"
import { Link, useRouter } from "expo-router"
import { Picker } from "@react-native-picker/picker"
import { DatePickerModal } from "react-native-paper-dates"
import { Provider as PaperProvider } from "react-native-paper"
import { ApiServices } from "../../services/apiServices"
import { MedicationPrototype } from "../../utils/MedicationPrototype"

const AddMedication = () => {
  const [medication, setMedication] = useState(() => new MedicationPrototype())
  const [showDatePicker, setShowDatePicker] = useState(false)
  const [isLoading, setIsLoading] = useState(false)

  const router = useRouter()

  const [fontsLoaded] = useFonts({
    Katibeh_400Regular,
  })
  const { width } = useWindowDimensions()
  const cardWidth = Math.min(width - 32, 768)

  const updateMedication = (key: keyof MedicationPrototype, value: any) => {
    setMedication((prev) => {
      const updated = prev.clone()
      return (updated as any)[`set${key.charAt(0).toUpperCase() + key.slice(1)}`](value)
    })
  }

  const validateDate = (date: Date) => {
    const today = new Date()
    today.setHours(0, 0, 0, 0)
    return date >= today
  }

  const validateTime = (time: string) => {
    const [hours, minutes] = time.split(":").map(Number)
    return hours >= 0 && hours < 24 && minutes >= 0 && minutes < 60
  }

  const validateDosage = (dosage: string) => {
    const dosageValue = Number.parseFloat(dosage)
    return dosageValue > 0 && dosageValue <= 100
  }

  const validateStock = (stock: string) => {
    const stockValue = Number.parseInt(stock, 10)
    return stockValue >= 0
  }

  const frequencyOptions = [
    { label: "1 em 1 hora", value: 1 },
    { label: "2 em 2 horas", value: 2 },
    { label: "4 em 4 horas", value: 4 },
    { label: "6 em 6 horas", value: 6 },
    { label: "8 em 8 horas", value: 8 },
    { label: "12 em 12 horas", value: 12 },
    { label: "A cada 24 horas", value: 24 },
    { label: "A cada 48 horas", value: 48 },
  ]

  const timeOptions = Array.from({ length: 24 }, (_, i) => {
    const hour = i.toString().padStart(2, "0")
    return { label: `${hour}:00`, value: `${hour}:00` }
  })

  const onDateChange = ({ date }: { date: Date }) => {
    if (date && validateDate(date)) {
      updateMedication("dataInicial", date)
      setShowDatePicker(false)
    }
  }

  const handleSubmit = async () => {
    if (!medication.nome || !medication.dosagem || !medication.frequencia || !medication.quantidade) {
      Alert.alert("Erro", "Por favor, preencha todos os campos obrigatórios.")
      return
    }

    if (!validateTime(medication.horaInicial)) {
      Alert.alert("Erro", "Por favor, insira um horário válido.")
      return
    }

    if (!validateDosage(medication.dosagem)) {
      Alert.alert("Erro", "Por favor, insira uma dosagem válida.")
      return
    }

    if (!validateStock(medication.quantidade)) {
      Alert.alert("Erro", "Por favor, insira uma quantidade de estoque válida.")
      return
    }

    setIsLoading(true)
    try {
      const apiServices = new ApiServices()

      const medicationId: number = await apiServices.createMedicamento(medication.nome, medication.laboratorio)

      if (medicationId) {
        setMedication((prev) => prev.clone().setId(medicationId))
        medication.setId(medicationId)

        const id_usuarioMedicamento: number = await apiServices.addMedicamentoUsuario(medication, 1)

        await apiServices.addEstoque(medication, id_usuarioMedicamento)

        await apiServices.addTratamento(medication, id_usuarioMedicamento, 1)

        Alert.alert("Sucesso", "Medicação adicionada com sucesso")
        router.back()
      } else {
        throw new Error("Failed to create medication")
      }
    } catch (error) {
      console.error("Erro ao adicionar medicação:", error)
      Alert.alert("Erro", "Não foi possível adicionar a medicação. Tente novamente.")
    } finally {
      setIsLoading(false)
    }
  }

  return (
    <View className="flex-1 bg-[#D8F1F5]">
      {/* Header */}
      <View className="flex-row items-center p-4 bg-white shadow">
        <TouchableOpacity onPress={() => router.back()}>
          <ArrowLeft size={24} color="#2F4858" />
        </TouchableOpacity>
        <Text className="text-xl font-semibold ml-4 text-[#2F4858]">Adicionar medicação</Text>
      </View>

      <ScrollView className="flex-1 p-4">
        <View className="items-center">
          <View className="mb-6 mt-6 w-full" style={{ maxWidth: cardWidth }}>
            <View className="bg-white rounded-2xl p-4 sm:p-6 relative shadow-md">
              {/* Basic Data */}
              <View className="mb-6">
                <Text
                  className="text-[#36555E] text-4xl sm:text-5xl font-light"
                  style={{ fontFamily: "Katibeh_400Regular" }}
                >
                  Dados Básicos
                </Text>

                <Text className="text-lg mb-2 text-[#2F4858]">Nome da medicação</Text>
                <TextInput
                  className="bg-[#F3F3F3] p-3 rounded-lg mb-4 text-lg text-[#2F4858]"
                  value={medication.nome}
                  onChangeText={(nome) => updateMedication("nome", nome)}
                  placeholder="Nome do medicamento"
                />

                <Text className="text-lg mb-2 text-[#2F4858]">Laboratório</Text>
                <TextInput
                  className="bg-[#F3F3F3] p-3 rounded-lg mb-4 text-lg text-[#2F4858]"
                  value={medication.laboratorio}
                  onChangeText={(laboratorio) => updateMedication("laboratorio", laboratorio)}
                  placeholder="Nome do laboratório"
                />

                <Text className="text-lg mb-2 text-[#2F4858]">Dosagem</Text>
                <View className="flex-row gap-2">
                  <TextInput
                    className="flex-1 bg-[#F3F3F3] p-3 rounded-lg text-lg text-[#2F4858]"
                    value={medication.dosagem}
                    onChangeText={(dosagem) => updateMedication("dosagem", dosagem)}
                    keyboardType="numeric"
                    placeholder="Quantidade de medicamento por dose (ex.: 1, 2, 0.5)"
                  />
                  <View className="bg-[#F3F3F3] px-4 rounded-lg flex-row items-center justify-between min-w-[150px]">
                    <Text className="text-[#2F4858]">Comprimidos</Text>
                  </View>
                </View>
              </View>

              {/* Schedule and Frequency */}
              <View className="mb-6">
                <Text
                  className="text-[#36555E] mt-4 text-4xl sm:text-5xl font-light"
                  style={{ fontFamily: "Katibeh_400Regular" }}
                >
                  Horários e Frequência
                </Text>

                <View className="flex-row gap-2 mb-4">
                  <View className="flex-1">
                    <Text className="text-lg mb-2 text-[#2F4858]">Data de início</Text>
                    <TouchableOpacity className="bg-[#F3F3F3] p-3 rounded-lg" onPress={() => setShowDatePicker(true)}>
                      <Text className="text-lg text-[#2F4858]">
                        {medication.dataInicial ? medication.dataInicial.toLocaleDateString() : "Selecionar data"}
                      </Text>
                    </TouchableOpacity>
                    <PaperProvider>
                      <DatePickerModal
                        mode="single"
                        visible={showDatePicker}
                        onDismiss={() => setShowDatePicker(false)}
                        date={medication.dataInicial || new Date()}
                        onConfirm={(params) => onDateChange({ date: params.date as Date })}
                        validRange={{
                          startDate: new Date(),
                        }}
                        saveLabel="Selecionar"
                        label="Selecione a data de início"
                        locale="pt-BR"
                      />
                    </PaperProvider>
                  </View>
                </View>

                <Text className="text-lg mb-2 text-[#2F4858]">Horário de início</Text>
                <View className="bg-[#F3F3F3] rounded-lg mb-4">
                  <Picker
                    selectedValue={medication.horaInicial}
                    onValueChange={(horaInicial) => updateMedication("horaInicial", horaInicial)}
                    className="bg-[#F3F3F3] p-3 rounded-lg"
                  >
                    {timeOptions.map((option) => (
                      <Picker.Item key={option.value} label={option.label} value={option.value} />
                    ))}
                  </Picker>
                </View>

                <Text className="text-lg mb-2 text-[#2F4858]">Frequência</Text>
                <View className="bg-[#F3F3F3] rounded-lg mb-4">
                  <Picker
                    selectedValue={medication.frequencia}
                    onValueChange={(frequencia) => updateMedication("frequencia", frequencia)}
                    className="bg-[#F3F3F3] p-3 rounded-lg"
                    testID="frequency-picker"
                  >
                    <Picker.Item label="Selecionar frequência" value="" />
                    {frequencyOptions.map((option) => (
                      <Picker.Item key={option.value} label={option.label} value={option.value} />
                    ))}
                  </Picker>
                </View>

                <Text className="text-lg mb-2 text-[#2F4858]">Duração do Tratamento</Text>
                <View className="flex-row gap-2">
                  <TextInput
                    className="flex-1 bg-[#F3F3F3] p-3 rounded-lg text-lg text-[#2F4858]"
                    value={medication.duracaoTratamento}
                    onChangeText={(duracaoTratamento) => updateMedication("duracaoTratamento", duracaoTratamento)}
                    keyboardType="numeric"
                    placeholder="Preencha com a duração do tratamento em número de dias"
                  />
                  <View className="bg-[#F3F3F3] px-4 rounded-lg flex-row items-center justify-between min-w-[150px]">
                    <Text className="text-[#2F4858]">Dias</Text>
                  </View>
                </View>
              </View>

              {/* Additional Details */}
              <View className="mb-6">
                <Text
                  className="text-[#36555E] mt-4 text-4xl sm:text-5xl font-light"
                  style={{ fontFamily: "Katibeh_400Regular" }}
                >
                  Detalhes Adicionais
                </Text>

                <Text className="text-lg mb-2 text-[#2F4858]">Quantidade Atual em Estoque</Text>
                <View className="flex-row gap-2">
                  <TextInput
                    className="flex-1 bg-[#F3F3F3] p-3 rounded-lg text-lg text-[#2F4858]"
                    value={medication.quantidade}
                    onChangeText={(quantidade) => updateMedication("quantidade", quantidade)}
                    keyboardType="numeric"
                    placeholder="Quantidade de comprimidos"
                  />
                  <View className="bg-[#F3F3F3] px-4 rounded-lg flex-row items-center justify-between min-w-[150px]">
                    <Text className="text-[#2F4858]">Comprimidos</Text>
                  </View>
                </View>

                <Text className="text-lg mb-2 mt-4 text-[#2F4858]">Informações Adicionais</Text>
                <TextInput
                  className="bg-[#F3F3F3] p-3 rounded-lg text-lg text-[#2F4858] min-h-[100px]"
                  value={medication.observacao}
                  onChangeText={(observacao) => updateMedication("observacao", observacao)}
                  multiline
                  textAlignVertical="top"
                  placeholder="Campo livre para o usuário adicionar informações extras (ex.: 'Tomar com água', 'Evitar em jejum')."
                />
              </View>

              {/* Footer Buttons */}
              <View className="flex-row gap-4">
                <TouchableOpacity
                  className="flex-1 bg-[#B73E3E] p-4 rounded-lg items-center"
                  onPress={() => router.back()}
                >
                  <Text className="text-white font-semibold text-lg">Cancelar</Text>
                </TouchableOpacity>
                <TouchableOpacity
                  className="flex-1 bg-[#75B7D1] p-4 rounded-lg items-center"
                  onPress={handleSubmit}
                  disabled={isLoading}
                >
                  <Text className="text-white font-semibold text-lg">{isLoading ? "Adicionando..." : "Adicionar"}</Text>
                </TouchableOpacity>
              </View>
            </View>
          </View>
        </View>
      </ScrollView>
    </View>
  )
}

export default AddMedication


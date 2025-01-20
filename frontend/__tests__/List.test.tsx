import React from "react"
import { render, waitFor } from "@testing-library/react-native"
import axios from "axios"
import MedicationList from "@/app/(tabs)/list"

// Mock the necessary dependencies
jest.mock("axios")
jest.mock("expo-font", () => ({
  useFonts: () => [true],
}))
jest.mock("@expo-google-fonts/katibeh", () => ({
  Katibeh_400Regular: "Katibeh_400Regular",
}))
jest.mock("expo-router", () => ({
  router: {
    push: jest.fn(),
  },
}))
jest.mock("@/components/Header", () => "AnimatedHeader")

const mockMedications = [
  {
    id: 1,
    usuario: { id: 1, nome: "John Doe" },
    medicamento: { id: 1, nome: "Paracetamol", laboratorio: "Lab A", cor: null },
    cor: "#FFE4E4",
    tratamentos: [
      {
        id: 1,
        dataInicial: "2023-01-01",
        frequencia: 8,
        duracao: 7,
        dosagem: 500,
        observacao: "",
        status: null,
        ativo: true,
        eventos: [],
      },
    ],
  },
  {
    id: 2,
    usuario: { id: 1, nome: "John Doe" },
    medicamento: { id: 2, nome: "Ibuprofeno", laboratorio: "Lab B", cor: null },
    cor: "#E4F2FF",
    tratamentos: [
      {
        id: 2,
        dataInicial: "2023-01-02",
        frequencia: 12,
        duracao: 5,
        dosagem: 400,
        observacao: "",
        status: null,
        ativo: false,
        eventos: [],
      },
    ],
  },
]

describe("MedicationList", () => {
  beforeEach(() => {
    ;(axios.get as jest.Mock).mockClear()
  })

  it("renders correctly and fetches medications", async () => {
    ;(axios.get as jest.Mock).mockResolvedValueOnce({ data: mockMedications })

    const { getByText, queryByText } = render(<MedicationList />)

    // Check loading state
    expect(getByText("Medicações ativas")).toBeTruthy()
    expect(getByText("Medicações suspensas")).toBeTruthy()

    // Wait for the medications to be loaded
    await waitFor(() => {
      expect(getByText("Paracetamol")).toBeTruthy()
      expect(getByText("Ibuprofeno")).toBeTruthy()
    })

    // Check if medications are categorized correctly
    expect(queryByText("Nenhuma medicação ativa")).toBeNull()
    expect(queryByText("Nenhuma medicação suspensa")).toBeNull()
  })

  it('displays "No active medications" when there are no active medications', async () => {
    const noActiveMedications = mockMedications.map((med) => ({
      ...med,
      tratamentos: med.tratamentos.map((t) => ({ ...t, ativo: false })),
    }))
    ;(axios.get as jest.Mock).mockResolvedValueOnce({ data: noActiveMedications })

    const { getByText } = render(<MedicationList />)

    await waitFor(() => {
      expect(getByText("Nenhuma medicação ativa")).toBeTruthy()
    })
  })

  it('displays "No suspended medications" when there are no suspended medications', async () => {
    const noSuspendedMedications = mockMedications.map((med) => ({
      ...med,
      tratamentos: med.tratamentos.map((t) => ({ ...t, ativo: true })),
    }))
    ;(axios.get as jest.Mock).mockResolvedValueOnce({ data: noSuspendedMedications })

    const { getByText } = render(<MedicationList />)

    await waitFor(() => {
      expect(getByText("Nenhuma medicação suspensa")).toBeTruthy()
    })
  })

  it("handles API error gracefully", async () => {
    ;(axios.get as jest.Mock).mockRejectedValueOnce(new Error("API Error"))

    const { getByText } = render(<MedicationList />)

    await waitFor(() => {
      expect(getByText("Medicações ativas")).toBeTruthy()
      expect(getByText("Medicações suspensas")).toBeTruthy()
    })

    // You might want to add more specific error handling checks here
    // depending on how your component handles API errors
  })
})


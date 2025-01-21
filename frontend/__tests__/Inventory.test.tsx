import React, { act } from "react"
import { render, waitFor, fireEvent } from "@testing-library/react-native"
import axios from "axios"
import Inventory from "../app/(tabs)/inventory"

jest.mock("axios")
jest.mock("@expo-google-fonts/katibeh", () => ({
  useFonts: jest.fn().mockReturnValue([true]),
}))

const mockMedications = [
  {
    id: 1,
    usuarioMedicamento: {
      id: 1,
      medicamento: {
        id: 1,
        nome: "Paracetamol",
        laboratorio: "Medley",
        cor: "#FFB6C1",
      },
      cor: "#FFB6C1",
    },
    quantidade: 30,
    ultimaCompra: "2025-01-01",
    status: "ATIVO",
  },
  {
    id: 2,
    usuarioMedicamento: {
      id: 2,
      medicamento: {
        id: 2,
        nome: "Ibuprofeno",
        laboratorio: "EMS",
        cor: "#ADD8E6",
      },
      cor: "#ADD8E6",
    },
    quantidade: 20,
    ultimaCompra: "2025-01-05",
    status: "ATIVO",
  },
  {
    id: 3,
    usuarioMedicamento: {
      id: 3,
      medicamento: {
        id: 3,
        nome: "Omeprazol",
        laboratorio: "Medley",
        cor: null,
      },
      cor: "#FFFFFF",
    },
    quantidade: 0,
    ultimaCompra: "2024-12-15",
    status: "SUSPENSO",
  },
]

describe("Inventory", () => {
  beforeEach(() => {
    jest.clearAllMocks()
  })

  it("carrega corretamente e busca as medicações", async () => {
    ;(axios.get as jest.Mock).mockResolvedValue({ data: mockMedications })

    const { getByText, getAllByText } = render(<Inventory />)

    await waitFor(() => {
      expect(getByText("Estoque")).toBeTruthy()
      expect(getByText("Estoque de medicações ativas")).toBeTruthy()
      expect(getByText("Estoque de medicações suspensas")).toBeTruthy()
      expect(getAllByText("Paracetamol")).toBeTruthy()
      expect(getAllByText("Ibuprofeno")).toBeTruthy()
      expect(getAllByText("Omeprazol")).toBeTruthy()
    })
  })

  it('exibe "Nenhuma medicação ativa" quando não há medicações ativas', async () => {
    const emptyMockMedications = mockMedications.filter((med) => med.status === "SUSPENSO")
    ;(axios.get as jest.Mock).mockResolvedValue({ data: emptyMockMedications })

    const { getByText } = render(<Inventory />)

    await waitFor(() => {
      expect(getByText("Nenhuma medicação ativa")).toBeTruthy()
    })
  })

  it('exibe "Nenhuma medicação suspensa" quando não há medicações suspensas', async () => {
    const activeMockMedications = mockMedications.filter((med) => med.status === "ATIVO")
    ;(axios.get as jest.Mock).mockResolvedValue({ data: activeMockMedications })

    const { getByText } = render(<Inventory />)

    await waitFor(() => {
      expect(getByText("Nenhuma medicação suspensa")).toBeTruthy()
    })
  })
})

function findByText(arg0: string): any {
  throw new Error("Function not implemented.")
}


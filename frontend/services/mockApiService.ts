import { MedicationPrototype } from "../utils/MedicationPrototype"

// Dados mockados para teste
const mockMedications: MedicationPrototype[] = [
  new MedicationPrototype(
    1,
    "Paracetamol",
    "Medley",
    new Date("2025-01-14T10:00:00"),
    "10:00",
    "8 em 8 horas",
    "750mg",
    "1 comprimido",
    "Tomar após as refeições",
    "ATIVO",
    "7 dias",
    "#FFB6C1",
  ),
  new MedicationPrototype(
    2,
    "Ibuprofeno",
    "EMS",
    new Date("2025-01-14T14:00:00"),
    "14:00",
    "12 em 12 horas",
    "600mg",
    "1 comprimido",
    "Tomar com água",
    "ATIVO",
    "5 dias",
    "#ADD8E6",
  ),
]

// Mock das funções da API
export const mockApiService = {
  getMedicacoesRecentes: async (): Promise<MedicationPrototype[]> => {
    // Simula um delay de rede
    await new Promise((resolve) => setTimeout(resolve, 1000))
    return mockMedications
  },

  updateStatusMedicacao: async (id: number, status: string): Promise<void> => {
    // Simula um delay de rede
    await new Promise((resolve) => setTimeout(resolve, 500))
    console.log(`Medicação ${id} marcada como ${status}`)
  },
}

// Interface for Inventory item
interface InventoryItem {
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
  duracaoEstimada?: number
}

const mockInventory: InventoryItem[] = [
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

export const getInventory = async (userId: number): Promise<InventoryItem[]> => {
  await new Promise((resolve) => setTimeout(resolve, 500))
  return mockInventory
}

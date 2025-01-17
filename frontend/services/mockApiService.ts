// mockApiService.ts
import { MedicamentoEntity } from '../entities/medicamentoEntity';

// Dados mockados para teste
const mockMedications = [
  {
    id: 1,
    nome: "Paracetamol",
    laboratorio: "Medley",
    dataInicial: new Date('2025-01-14T10:00:00'),
    horaInicial: "10:00",
    frequencia: "8 em 8 horas",
    dosagem: "750mg",
    quantidade: "1 comprimido",
    observacao: "Tomar após as refeições",
    status: null,
    duracaoTratamento: 7
  },
  {
    id: 2,
    nome: "Ibuprofeno",
    laboratorio: "EMS",
    dataInicial: new Date('2025-01-14T14:00:00'),
    horaInicial: "14:00",
    frequencia: "12 em 12 horas",
    dosagem: "600mg",
    quantidade: "1 comprimido",
    observacao: "Tomar com água",
    status: null,
    duracaoTratamento: 5
  }
];

// Mock das funções da API
export const mockApiService = {
  getMedicacoesRecentes: async (): Promise<MedicamentoEntity[]> => {
    // Simula um delay de rede
    await new Promise(resolve => setTimeout(resolve, 1000));
    return mockMedications;
  },

  updateStatusMedicacao: async (id: number, status: string): Promise<void> => {
    // Simula um delay de rede
    await new Promise(resolve => setTimeout(resolve, 500));
    console.log(`Medicação ${id} marcada como ${status}`);
  }
};
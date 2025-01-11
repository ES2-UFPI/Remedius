// __tests__/Inventory.test.tsx
import React from 'react';
import { render, waitFor } from '@testing-library/react-native';
import Inventory from '../app/(tabs)/inventory';
import axios from 'axios';

// Mock das fontes
jest.mock('@expo-google-fonts/katibeh', () => ({
    useFonts: () => [true],
    Katibeh_400Regular: 'Katibeh_400Regular',
  }));

jest.mock('axios');
const mockedAxios = axios as jest.Mocked<typeof axios>;

const mockMedicacoes = [
  {
    id: 1,
    medicamento: {
      id: 1,
      nome: "Paracetamol",
      laboratorio: "Lab A",
      cor: "#E6F3FF"
    },
    quantidade: 15,
    status: "Ativo",
    duracaoEstimada: 20
  },
  {
    id: 2,
    medicamento: {
      id: 2,
      nome: "Dipirona",
      laboratorio: "Lab B",
      cor: "#FFE6E6"
    },
    quantidade: 10,
    status: "Suspenso",
    duracaoEstimada: 15
  }
];

describe('Testes do Inventory', () => {
  beforeEach(() => {
    mockedAxios.get.mockClear();
  });

  it('deve renderizar corretamente com dados da API', async () => {
    mockedAxios.get.mockResolvedValueOnce({ data: mockMedicacoes });
    
    const { getByText } = render(<Inventory />);
    
    await waitFor(() => {
      expect(getByText('Paracetamol')).toBeTruthy();
      expect(getByText('Dipirona')).toBeTruthy();
      expect(getByText('Total: 15 comps')).toBeTruthy();
    });
  });

  it('deve exibir medicações ativas e suspensas separadamente', async () => {
    mockedAxios.get.mockResolvedValueOnce({ data: mockMedicacoes });
    
    const { getByText } = render(<Inventory />);
    
    await waitFor(() => {
      expect(getByText('Estoque de medicações ativas')).toBeTruthy();
      expect(getByText('Estoque de medicações suspensas')).toBeTruthy();
    });
  });
});
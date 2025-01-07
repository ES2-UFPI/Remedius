import React from 'react';
import { render, fireEvent, waitFor } from '@testing-library/react-native';
import RegisterMedication from '../app/(tabs)/add'
import axios from 'axios';
import { Alert } from 'react-native';

jest.mock('axios');
jest.mock('expo-router', () => ({
  Link: ({ children }) => children,
}));
jest.mock('expo-font', () => ({
  useFonts: () => [true],
}));
jest.mock('@expo-google-fonts/katibeh', () => ({
  Katibeh_400Regular: 'mocked-font',
}));
// Mock do Alert
jest.spyOn(Alert, 'alert');

describe('RegisterMedication', () => {
  beforeEach(() => {
    jest.clearAllMocks();
  });

  test('renderiza todos os campos do formulário', () => {
    const { getByPlaceholderText, getByText } = render(<RegisterMedication />);
    
    expect(getByPlaceholderText('Nome do medicamento')).toBeTruthy();
    expect(getByPlaceholderText('Nome do laboratório')).toBeTruthy();
    expect(getByText('Dosagem')).toBeTruthy();
    expect(getByText('Data de início')).toBeTruthy();
    expect(getByText('Horário de início')).toBeTruthy();
    expect(getByText('Frequência')).toBeTruthy();
  });

  test('mostra erro quando campos obrigatórios estão vazios', async () => {
    const { getByText } = render(<RegisterMedication />);
    
    fireEvent.press(getByText('Confirmar'));
    
    await waitFor(() => {
      expect(Alert.alert).toHaveBeenCalledWith('Erro', 'Por favor, preencha todos os campos obrigatórios.');
    });
  });

  test('valida dosagem inválida', async () => {
    const { getByPlaceholderText, getByText } = render(<RegisterMedication />);
    
    fireEvent.changeText(getByPlaceholderText('Nome do medicamento'), 'Dipirona');
    fireEvent.changeText(getByPlaceholderText('Quantidade de medicamento por dose (ex.: 1, 2, 0.5)'), '101');
    
    fireEvent.press(getByText('Confirmar'));
    
    await waitFor(() => {
      expect(Alert.alert).toHaveBeenCalledWith('Erro', 'Por favor, insira uma dosagem válida.');
    });
  });

  test('cadastra medicação com sucesso', async () => {
    const mockMedicamentoResponse = { data: { id: 1 } };
    (axios.post as jest.Mock)
      .mockResolvedValueOnce(mockMedicamentoResponse)
      .mockResolvedValueOnce({ data: { success: true } });
    
    const { getByPlaceholderText, getByText } = render(<RegisterMedication />);
    
    // Preenche todos os campos obrigatórios
    fireEvent.changeText(getByPlaceholderText('Nome do medicamento'), 'Dipirona');
    fireEvent.changeText(getByPlaceholderText('Nome do laboratório'), 'EMS');
    fireEvent.changeText(getByPlaceholderText('Quantidade de medicamento por dose (ex.: 1, 2, 0.5)'), '1');
    fireEvent.changeText(getByPlaceholderText('Quantidade de comprimidos'), '30');
    
    // Seleciona frequência
    const picker = getByText('Selecionar frequência');
    fireEvent(picker, 'onValueChange', '12');
  
    fireEvent.press(getByText('Confirmar'));
    
    await waitFor(() => {
      expect(axios.post).toHaveBeenCalledWith('http://localhost:8080/medicamentos', {
        nome: 'Dipirona',
        laboratorio: 'EMS'
      });
      expect(axios.post).toHaveBeenCalledWith('http://localhost:8080/usuarios-medicamentos/1', expect.any(Object));
      expect(Alert.alert).toHaveBeenCalledWith('Sucesso', 'Medicação cadastrada com sucesso!');
    }, { timeout: 3000 });
  });

  test('trata erro na API corretamente', async () => {
    (axios.post as jest.Mock).mockRejectedValueOnce(new Error('Erro de API'));
    
    const { getByPlaceholderText, getByText } = render(<RegisterMedication />);
    
    fireEvent.changeText(getByPlaceholderText('Nome do medicamento'), 'Dipirona');
    fireEvent.changeText(getByPlaceholderText('Nome do laboratório'), 'EMS');
    fireEvent.changeText(getByPlaceholderText('Quantidade de medicamento por dose (ex.: 1, 2, 0.5)'), '1');
    fireEvent.changeText(getByPlaceholderText('Quantidade de comprimidos'), '30');
    
    fireEvent.press(getByText('Confirmar'));
    
    await waitFor(() => {
      expect(Alert.alert).toHaveBeenCalledWith('Erro', 'Não foi possível cadastrar a medicação. Tente novamente.');
    });
  });

  test('limpa formulário ao pressionar cancelar', () => {
    const { getByPlaceholderText, getByText } = render(<RegisterMedication />);
    
    fireEvent.changeText(getByPlaceholderText('Nome do medicamento'), 'Dipirona');
    fireEvent.press(getByText('Cancelar'));
    
    expect(getByPlaceholderText('Nome do medicamento').props.value).toBe('');
  });
});
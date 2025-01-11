// criar uma classe que implementa funções para fazer requisições para a API
import { MedicamentoEntity } from '../entities/medicamentoEntity'; // Ensure this path is correct
import axios from 'axios';
import { Alert } from 'react-native';

export class ApiServices {
  
    // função para criar um medicamento
    async createMedicamento(nome: string, laboratorio: string) {
        try {
            const createMedicamentoResponse = await axios.post(`http://localhost:8080/medicamentos`, {
                nome: nome,
                laboratorio: laboratorio,
            });
            const medicamentoId = createMedicamentoResponse.data.id;
            return medicamentoId;
        } catch (error) {
            console.error(error);
        }
    }

    // função para adicionar medicação ao usuário
    async addMedicamentoUsuario(medicamento: MedicamentoEntity, usuarioId: number) {
        const formattedDate = `${medicamento.dataInicial.toISOString().split('T')[0]}T${medicamento.horaInicial}:00`;
        try {
            await axios.post(`http://localhost:8080/usuarios-medicamentos/${usuarioId}`, {
                medicamentoId: medicamento.id,
                dataInicial: formattedDate,
                frequencia: medicamento.frequencia,
                dosagem: parseFloat(medicamento.dosagem),
                quant_inicial: parseInt(medicamento.quantidade || '0'),
                observacao: medicamento.observacao,
            });
        } catch (error) {
            console.error('Erro ao cadastrar medicação:', error);
            Alert.alert('Não foi possível cadastrar a medicação. Tente novamente.'); 
        }
    };

    // função para adicionar estoque ao medicamento
    async addEstoque(medicamento: MedicamentoEntity, usuarioId: number) {
        try {
            await axios.post(`http://localhost:8080/estoque`, {
                usuarioId: usuarioId,
                medicamentoId: medicamento.id,
                quantidade: parseInt(medicamento.quantidade|| '0'),
                ultimaCompra: new Date().toISOString(),
                status: 'Ativo'
            });
        } catch (error) {
            console.error('Erro ao adicionar estoque:', error);
            Alert.alert('Não foi possível adicionar estoque ao medicamento. Tente novamente.');
        }
    }
}
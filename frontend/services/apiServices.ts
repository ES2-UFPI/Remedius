import axios from 'axios';
import { MedicationPrototype } from '../utils/MedicationPrototype';
import { Alert } from 'react-native';

export class ApiServices {
    // função para criar um novo medicamento
    async createMedicamento(nome: string, laboratorio: string | null) {
        try {
            const createMedicamentoResponse = await axios.post('http://localhost:8080/medicamentos', {
                nome: nome,
                laboratorio: laboratorio,
            });
            return createMedicamentoResponse.data.id;
            
        } catch (error) {
            console.error(error);
        }
    }

    // função para adicionar medicação ao usuário
    async addMedicamentoUsuario(medicamento: MedicationPrototype, usuarioId: number) {
        const formattedDate = `${medicamento.dataInicial.toISOString().split('T')[0]}T${medicamento.horaInicial}:00`;
        try {
            await axios.post(`http://localhost:8080/usuarios-medicamentos/${usuarioId}`, {
                medicamentoId: medicamento.id,
                dataInicial: formattedDate,
                frequencia: medicamento.frequencia,
                dosagem: parseFloat(medicamento.dosagem),
                quant_inicial: parseInt(medicamento.quantidade || '0'),
                observacao: medicamento.observacao,
                duracaoTratamento: medicamento.duracaoTratamento
            });
        } catch (error) {
            console.error('Erro ao cadastrar medicação:', error);
            Alert.alert('Não foi possível cadastrar a medicação. Tente novamente.'); 
        }
    }

    // função para adicionar estoque ao medicamento
    async addEstoque(medicamento: MedicationPrototype, usuarioId: number) {
        try {
            await axios.post(`http://localhost:8080/estoque`, {
                usuarioId: usuarioId,
                medicamentoId: medicamento.id,
                quantidade: parseInt(medicamento.quantidade || '0'),
                ultimaCompra: new Date().toISOString(),
                status: 'Ativo'
            });
        } catch (error) {
            console.error('Erro ao adicionar estoque:', error);
            Alert.alert('Não foi possível adicionar estoque ao medicamento. Tente novamente.');
        }
    }
}

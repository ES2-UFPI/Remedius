import axios from 'axios';
import { MedicationPrototype } from '../utils/MedicationPrototype';
import { Alert } from 'react-native';

export class ApiServices {
    // função para criar um novo medicamento
    async createMedicamento(nome: string, laboratorio: string | null): Promise <number> {
        try {
            const createMedicamentoResponse = await axios.post('http://localhost:8080/medicamentos', {
                nome: nome,
                laboratorio: laboratorio
            });
            return createMedicamentoResponse.data.id;
        } catch (error) {
            console.error(error);
        }
        return 0;
    }

    // função para adicionar medicação ao usuário
    async addMedicamentoUsuario(medicamento: MedicationPrototype, usuarioId: number): Promise<number> {
        try {
            const relacoes = await axios.post(`http://localhost:8080/usuario-medicamentos`, {
                medicamentoId: medicamento.id,
                usuarioId: usuarioId,
                cor: "vermelho"
            });
            const id_usuarioMedicamento = relacoes.data.id;
            return id_usuarioMedicamento;
        } catch (error) {
            console.error('Erro ao cadastrar medicação:', error);
            Alert.alert('Não foi possível cadastrar a medicação. Tente novamente.'); 
        }
        return 0;
    }

    async addTratamento(medicamento: MedicationPrototype, id_usuarioMedicamento: number, usuario_id: number) {
        const formattedDate = `${medicamento.dataInicial.toISOString().split('T')[0]}T${medicamento.horaInicial}:00`;
        try {
            const response = await axios.post(`http://localhost:8080/tratamentos`, {
                usuarioMedicamentoId: id_usuarioMedicamento,
                dataInicial: formattedDate,
                frequencia: parseInt(medicamento.frequencia),
                duracao: medicamento.duracaoTratamento,
                dosagem: parseFloat(medicamento.dosagem),
                observacao: medicamento.observacao,
               }
            );
        } catch (error) {
            console.error('Erro ao cadastrar medicação:', error);
            Alert.alert('Não foi possível cadastrar a medicação. Tente novamente.'); 
        }
    }

    // função para adicionar estoque ao medicamento
    async addEstoque(medicamento: MedicationPrototype, id_usuarioMedicamento: number) {
        try {
            await axios.post(`http://localhost:8080/estoque`, {
                usuarioMedicamentoId: id_usuarioMedicamento,
                quantidade: parseInt(medicamento.quantidade || '0'),
                ultimaCompra: new Date().toISOString(),
                status: 'ATIVO'
            });
        } catch (error) {
            console.error('Erro ao adicionar estoque:', error);
            Alert.alert('Não foi possível adicionar estoque ao medicamento. Tente novamente.');
        }
    }
    // função para buscar medicações recentes sem status atualizado
    async getMedicacoesRecentes(usuarioId: number) {
        try {
            // IMPLEMENTAR
        } catch (error) {
            console.error('Erro ao buscar medicações recentes:', error);
        }
        return [];
    }

    // função para atualizar status da medicação
    async updateStatusMedicacao(medicacaoId: number, status: string) {
        try {
            // IMPLEMENTAR
        } catch (error) {
            console.error('Erro ao atualizar status da medicação:', error);
        }
    }

}

// criando a entidade Medicamento
type MedicamentoEntity = {
    id: number;
    nome: string;
    laboratorio: string | null;
    dataInicial: Date;
    horaInicial: string;
    frequencia: string;
    dosagem: string;
    quantidade: string;
    observacao: string;
    status: string | null;
    duracaoTratamento: number;
    };

export type { MedicamentoEntity };


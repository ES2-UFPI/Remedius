export interface MedicationData {
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
}

export class MedicationPrototype implements MedicationData {
  constructor(
    public id: number = 0,
    public nome: string = '',
    public laboratorio: string | null = null,
    public dataInicial: Date = new Date(),
    public horaInicial: string = '08:00',
    public frequencia: string = '12 em 12 horas',
    public dosagem: string = '',
    public quantidade: string = '',
    public observacao: string = 'Tomar com água',
    public status: string | null = null,
    public duracaoTratamento: number = 0
  ) { }

  clone(): MedicationPrototype {
    return new MedicationPrototype(
      this.id,
      this.nome,
      this.laboratorio,
      this.dataInicial,
      this.horaInicial,
      this.frequencia,
      this.dosagem,
      this.quantidade,
      this.observacao,
      this.status,
      this.duracaoTratamento
    );
  }

  setNome(nome: string): this {
    this.nome = nome;
    return this;
  }

  setDosagem(dosagem: string): this {
    this.dosagem = dosagem;
    return this;
  }

  setFrequencia(frequencia: string): this {
    this.frequencia = frequencia;
    return this;
  }

  setHorarioInicio(horaInicial: string): this {
    this.horaInicial = horaInicial;
    return this;
  }

  setInformacoesAdicionais(observacao: string): this {
    this.observacao = observacao;
    return this;
  }

  setLaboratorio(laboratorio: string | null): this {
    this.laboratorio = laboratorio;
    return this;
  }

  setStatus(status: string | null): this {
    this.status = status;
    return this;
  }

  setDuracaoTratamento(duracao: number): this {
    this.duracaoTratamento = duracao;
    return this;
  }
}

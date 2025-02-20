export interface MedicationData {
  id: number;
  nome: string;
  laboratorio: string;
  dataInicial: Date;
  horaInicial: string;
  frequencia: string;
  dosagem: string;
  quantidade: string;
  observacao: string;
  status: string | null;
  duracaoTratamento: string;
  cor: string | null;
}

export class MedicationPrototype implements MedicationData {
  constructor(
    public id: number = 0,
    public nome: string = '',
    public laboratorio: string = '',
    public dataInicial: Date = new Date(),
    public horaInicial: string = '08:00',
    public frequencia: string = '12 em 12 horas',
    public dosagem: string = '',
    public quantidade: string = '',
    public observacao: string = '',
    public status: string | null = null,
    public duracaoTratamento: string = '',
    public cor: string | null = null
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
      this.duracaoTratamento,
      this.cor
    );
  }

  static fromJSON(json: any): MedicationPrototype {
    return new MedicationPrototype(
      json.id,
      json.nome,
      json.laboratorio,
      new Date(json.dataInicial),
      json.horaInicial,
      json.frequencia,
      json.dosagem,
      json.quantidade,
      json.observacao,
      json.status,
      json.duracaoTratamento,
      json.cor
    );
  }

  setId(id: number): this {
    this.id = id;
    return this;
  }

  setNome(nome: string): this {
    this.nome = nome;
    return this;
  }

  setLaboratorio(laboratorio: string): this {
    this.laboratorio = laboratorio;
    return this;
  }

  setDataInicial(dataInicial: Date): this {
    this.dataInicial = dataInicial;
    return this;
  }

  setHorarioInicio(horaInicial: string): this {
    this.horaInicial = horaInicial;
    return this;
  }

  setFrequencia(frequencia: string): this {
    this.frequencia = frequencia;
    return this;
  }

  setDosagem(dosagem: string): this {
    this.dosagem = dosagem;
    return this;
  }

  setQuantidade(quantidade: string): this {
    this.quantidade = quantidade;
    return this;
  }

  setObservacao(observacao: string): this {
    this.observacao = observacao;

    return this;
  }

  setStatus(status: string | null): this {
    this.status = status;
    return this;
  }


  setDuracaoTratamento(duracao: string): this {
    this.duracaoTratamento = duracao;
    return this;
  }

  setCor(cor: string | null): this {
    this.cor = cor;
    return this;
  }
}

const fs = require("fs");
const { faker } = require("@faker-js/faker");

// Função para gerar medicamentos aleatórios
const gerarMedicamentos = (n) => {
  const medicamentos = [];
  for (let i = 0; i < n; i++) {
    medicamentos.push({
      id: i + 1,
      name: faker.lorem.word(),
    });
  }
  return medicamentos;
};

// Gerando os dados e gravando no arquivo db.json
const data = {
  medicamentos: gerarMedicamentos(10), // Gera 10 usuários
};

fs.writeFileSync("db.json", JSON.stringify(data, null, 2));
console.log("Dados gerados e salvos no db.json");

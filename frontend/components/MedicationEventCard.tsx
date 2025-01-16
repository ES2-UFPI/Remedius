import React from 'react';
import { View, Text, StyleSheet } from 'react-native';

export interface MedicationEventProps {
  nome: string;
  dosagem: string;
  horario: Date;
  cor: string;
  status: 'tomado' | 'rejeitado' | 'pendente';
}

const statusColors = {
  tomado: 'green',
  rejeitado: 'red',
  pendente: 'gray',
};

const MedicationEventCard: React.FC<MedicationEventProps> = ({
  nome,
  dosagem,
  horario,
  cor,
  status
}) => {
  return (
    <View style={[styles.card, { backgroundColor: `#${cor}` }]}>
      {/* Indicador do status do evento */}
      <View 
        style={[styles.statusIndicator, { backgroundColor: statusColors[status] }]}
      />
      
      {/* Informações da medicação */}
      <Text style={styles.nome}>
        {nome}
      </Text>
      <View style={styles.infoContainer}>
        <Text style={styles.dosagem}>
          {dosagem}
        </Text>
        <Text style={styles.horario}>
          {horario.toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' })}
        </Text>
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  card: {
    borderRadius: 10,
    padding: 16,
    marginBottom: 8,
    position: 'relative',
  },
  statusIndicator: {
    position: 'absolute',
    top: 8,
    right: 8,
    width: 12,
    height: 12,
    borderRadius: 6,
  },
  nome: {
    color: '#2D3648',
    fontSize: 18,
    fontWeight: 'bold',
    marginBottom: 4,
  },
  infoContainer: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
  },
  dosagem: {
    color: '#2D3648',
    fontSize: 16,
  },
  horario: {
    color: '#2D3648',
    fontSize: 14,
    fontWeight: '500',
  },
});

export default MedicationEventCard;
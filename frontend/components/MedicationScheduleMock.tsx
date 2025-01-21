import React from 'react';
import { View, Text, ScrollView } from 'react-native';
import MedicationEventCard from './MedicationEventCard';

// dados simulados temporários para demonstração
const mockEvents = [
  {
    id: 1,
    name: 'Dipirona',
    dosage: '1 comprimido',
    time: new Date(Date.parse('2025-01-17T08:00:00')),
    cor: 'a3c99b',
    status: 'tomado' as const,
  },
  {
    id: 2,
    name: 'Amoxicilina',
    dosage: '2 comprimidos',
    time: new Date(Date.parse('2025-01-17T10:00:00')),
    cor: 'c3e2e3',
    status: 'rejeitado' as const,
  },
  {
    id: 3,
    name: 'Paracetamol',
    dosage: '1 comprimido',
    time: new Date(Date.parse('2025-01-17T16:00:00')),
    cor: 'e3c3c6',
    status: 'pendente' as const,
  },
];

const MedicationSchedule: React.FC = () => {
  return (
    <View className="mt-4">
      <ScrollView>
        {mockEvents.map((event) => (
          <MedicationEventCard
            key={event.id}
            nome={event.name}
            dosagem={event.dosage}
            horario={event.time}
            cor={event.cor}
            status={event.status}
          />
        ))}
      </ScrollView>
    </View>
  );
};

export default MedicationSchedule;


import React, { useState, useEffect } from 'react';
import { View, Text, TouchableOpacity, ActivityIndicator, useWindowDimensions } from 'react-native';
import { MedicamentoEntity } from '../entities/medicamentoEntity';
import { mockApiService } from '../services/mockApiService';

interface MedicationCardProps {
  medicamento: MedicamentoEntity;
  onUpdateStatus: (id: number, status: string) => Promise<void>;
}

const MedicationCard: React.FC<MedicationCardProps> = ({ medicamento, onUpdateStatus }) => {
  const [isUpdating, setIsUpdating] = useState(false);

  const handleStatusUpdate = async (status: string) => {
    setIsUpdating(true);
    try {
      await onUpdateStatus(medicamento.id, status);
    } catch (error) {
      console.error('Erro ao atualizar status:', error);
    } finally {
      setIsUpdating(false);
    }
  };

  return (
    <View style={{ backgroundColor: '#f9fafb', borderRadius: 12, padding: 16, marginBottom: 12 }}>
      <View style={{ flexDirection: 'row', justifyContent: 'space-between', alignItems: 'center' }}>
        {/* Informações da medicação */}
        <View style={{ flex: 1, marginRight: 16 }}>
          <Text style={{ fontSize: 18, fontWeight: '600', color: '#36555E' }}>{medicamento.nome}</Text>
          <Text style={{ fontSize: 14, color: '#4b5563' }}>
            Dose: {medicamento.dosagem} - {medicamento.quantidade}
          </Text>
          <Text style={{ fontSize: 14, color: '#4b5563' }}>
            Horário: {new Date(medicamento.dataInicial).toLocaleDateString()} às {medicamento.horaInicial}
          </Text>
        </View>

        {/* Botões */}
        {isUpdating ? (
          <ActivityIndicator color="#36555E" />
        ) : (
          <View style={{ flexDirection: 'row', gap: 8 }}>
            <TouchableOpacity
              style={{ backgroundColor: '#22c55e', paddingHorizontal: 12, paddingVertical: 8, borderRadius: 8 }}
              onPress={() => handleStatusUpdate('TOMADO')}
            >
              <Text style={{ color: 'white', textAlign: 'center', fontSize: 14 }}>Tomei</Text>
            </TouchableOpacity>
            
            <TouchableOpacity
              style={{ backgroundColor: '#ef4444', paddingHorizontal: 12, paddingVertical: 8, borderRadius: 8 }}
              onPress={() => handleStatusUpdate('NAO_TOMADO')}
            >
              <Text style={{ color: 'white', textAlign: 'center', fontSize: 14 }}>Não Tomei</Text>
            </TouchableOpacity>
          </View>
        )}
      </View>
    </View>
  );
};

const RecentMedications: React.FC = () => {
  const [medications, setMedications] = useState<MedicamentoEntity[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);
  const { width } = useWindowDimensions();
  const cardWidth = Math.min(width - 32, 768);
    

  const fetchRecentMedications = async () => {
    setLoading(true);
    try {
      const response = await mockApiService.getMedicacoesRecentes();
      setMedications(response);
      setError(null);
    } catch (err) {
      setError('Erro ao carregar medicações');
      console.error('Erro ao buscar medicações:', err);
    } finally {
      setLoading(false);
    }
  };

  const updateMedicationStatus = async (id: number, status: string) => {
    try {
      await mockApiService.updateStatusMedicacao(id, status);
      await fetchRecentMedications();
    } catch (error) {
      console.error('Erro ao atualizar status da medicação:', error);
    }
  };

  useEffect(() => {
    fetchRecentMedications();
  }, []);

  if (loading) {
    return (
      <View style={{ flex: 1, alignItems: 'center', justifyContent: 'center', padding: 16 }}>
        <ActivityIndicator size="large" color="#36555E" testID="loading-indicator" />
      </View>
    );
  }

  return (
    <View className="mb-6 w-full" style={{ maxWidth: cardWidth }}>
      <View className="bg-white rounded-2xl p-4 sm:p-6 relative shadow-md">
        <Text 
          className="text-[#36555E] text-4xl sm:text-5xl font-light" 
          style={{ fontFamily: 'Katibeh_400Regular' }}
        >
          Medicações Recentes
        </Text>
        
        <View style={{ marginTop: 16 }}>
          {error ? (
            <Text style={{ color: '#ef4444', textAlign: 'center', fontSize: 18, marginTop: 16 }}>
              {error}
            </Text>
          ) : medications.length === 0 ? (
            <Text style={{ color: '#2D3648', fontSize: 18, marginTop: 16 }}>
              Nenhuma medicação pendente
            </Text>
          ) : (
            medications.map((med) => (
              <MedicationCard
                key={med.id}
                medicamento={med}
                onUpdateStatus={updateMedicationStatus}
              />
            ))
          )}
        </View>
      </View>
    </View>
  );
};

export default RecentMedications;

import React from 'react';
import { View, Text, ScrollView, TouchableOpacity, TextInput, StyleSheet } from 'react-native';
import { ArrowLeft, Edit2, Bell, Home, Menu, Plus, User, FileText } from 'lucide-react-native';

export default function Component() {
  return (
    <View style={styles.container}>
      {/* Header */}
      <View style={styles.header}>
        <TouchableOpacity>
          <ArrowLeft size={24} color="#2F4858" />
        </TouchableOpacity>
        <Text style={styles.headerTitle}>Minhas Medicações</Text>
      </View>

      <ScrollView style={styles.content}>
        {/* Active Medications */}
        <View style={styles.section}>
          <Text style={styles.sectionTitle}>Medicações ativas</Text>
          <View style={[styles.medicationItem, { backgroundColor: '#FFE4E4' }]}>
            <Text style={styles.medicationText}>Zolpidem</Text>
            <View style={styles.iconContainer}>
              <TouchableOpacity style={styles.icon}>
                <Edit2 size={20} color="#2F4858" />
              </TouchableOpacity>
              <TouchableOpacity style={styles.icon}>
                <Bell size={20} color="#2F4858" />
              </TouchableOpacity>
            </View>
          </View>
          <View style={[styles.medicationItem, { backgroundColor: '#E4F2FF' }]}>
            <Text style={styles.medicationText}>Dipirona</Text>
            <View style={styles.iconContainer}>
              <TouchableOpacity style={styles.icon}>
                <Edit2 size={20} color="#2F4858" />
              </TouchableOpacity>
              <TouchableOpacity style={styles.icon}>
                <Bell size={20} color="#2F4858" />
              </TouchableOpacity>
            </View>
          </View>
          <View style={[styles.medicationItem, { backgroundColor: '#E4FFE8' }]}>
            <Text style={styles.medicationText}>Mirtazapina</Text>
            <View style={styles.iconContainer}>
              <TouchableOpacity style={styles.icon}>
                <Edit2 size={20} color="#2F4858" />
              </TouchableOpacity>
              <TouchableOpacity style={styles.icon}>
                <Bell size={20} color="#2F4858" />
              </TouchableOpacity>
            </View>
          </View>
        </View>

        {/* Suspended Medications */}
        <View style={styles.section}>
          <Text style={styles.sectionTitle}>Medicações suspensas</Text>
          <View style={[styles.medicationItem, { backgroundColor: '#E5E5E5' }]}>
            <Text style={styles.medicationText}>Glifage</Text>
            <View style={styles.iconContainer}>
              <TouchableOpacity style={styles.icon}>
                <Edit2 size={20} color="#2F4858" />
              </TouchableOpacity>
              <TouchableOpacity style={styles.icon}>
                <Bell size={20} color="#2F4858" />
              </TouchableOpacity>
            </View>
          </View>
          <View style={[styles.medicationItem, { backgroundColor: '#E5E5E5' }]}>
            <Text style={styles.medicationText}>Buscopam</Text>
            <View style={styles.iconContainer}>
              <TouchableOpacity style={styles.icon}>
                <Edit2 size={20} color="#2F4858" />
              </TouchableOpacity>
              <TouchableOpacity style={styles.icon}>
                <Bell size={20} color="#2F4858" />
              </TouchableOpacity>
            </View>
          </View>
        </View>

        {/* Medication Stock */}
        <View style={styles.section}>
          <Text style={styles.sectionTitle}>Estoque de medicações</Text>
          <TextInput style={styles.input} placeholder="Zolpidem" />
          <TextInput style={styles.input} placeholder="Dipirona" />
          <TextInput style={styles.input} placeholder="Mirtazapina" />
        </View>
      </ScrollView>

    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#F0F9FF',
  },
  header: {
    flexDirection: 'row',
    alignItems: 'center',
    padding: 16,
    backgroundColor: '#FFF',
    elevation: 2,
  },
  headerTitle: {
    fontSize: 20,
    fontWeight: '600',
    marginLeft: 16,
    color: '#2F4858',
  },
  content: {
    flex: 1,
    padding: 16,
  },
  section: {
    marginBottom: 24,
  },
  sectionTitle: {
    fontSize: 18,
    fontWeight: '600',
    marginBottom: 12,
    color: '#2F4858',
  },
  medicationItem: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    padding: 16,
    borderRadius: 8,
    marginBottom: 8,
  },
  medicationText: {
    fontSize: 16,
    color: '#2F4858',
  },
  iconContainer: {
    flexDirection: 'row',
    gap: 12,
  },
  icon: {
    padding: 4,
  },
  input: {
    backgroundColor: '#FFF',
    padding: 12,
    borderRadius: 8,
    marginBottom: 8,
    fontSize: 16,
    color: '#2F4858',
  },
  addButton: {
    backgroundColor: '#75B7D1',
    width: 48,
    height: 48,
    borderRadius: 24,
    justifyContent: 'center',
    alignItems: 'center',
    elevation: 4,
  },
});
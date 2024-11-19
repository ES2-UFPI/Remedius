import React from 'react';
import { View, Text, TouchableOpacity, StyleSheet, useWindowDimensions } from 'react-native';
import { Ionicons } from '@expo/vector-icons'; // Importação de ícones do Expo

export default function BottomNav() {
  const { width: windowWidth } = useWindowDimensions();

  // Define o tamanho do botão de adição como uma proporção da largura da tela
  const addButtonSize = windowWidth * 0.08; // 8% da largura da tela

  return (
    <View style={styles.bottomNav}>
      <TouchableOpacity style={[styles.navItem, styles.activeNavItem]}>
        <Ionicons name="home-outline" size={Math.max(addButtonSize * 0.5, 30)} color="#FFF" />
        <Text style={styles.navText}>Home</Text>
      </TouchableOpacity>
      <TouchableOpacity style={styles.navItem}>
        <Ionicons name="time-outline" size={Math.max(addButtonSize * 0.5, 30)} color="#FFF" />
        <Text style={styles.navText}>Horários</Text>
      </TouchableOpacity>
      <TouchableOpacity
        style={[
          styles.navItem,
          styles.addButton,
          {
            width: addButtonSize,
            minWidth: 100,
            height: addButtonSize,
            minHeight: 100,
            borderRadius: Math.max(addButtonSize, 100) / 2,
          },
        ]}
      >
        <Ionicons name="add-outline" size={Math.min(addButtonSize*1.5, 100)} color="white" />
      </TouchableOpacity>
      <TouchableOpacity style={styles.navItem}>
        <Ionicons name="person-outline" size={Math.max(addButtonSize * 0.5, 30)} color="#FFF" />
        <Text style={styles.navText}>Perfil</Text>
      </TouchableOpacity>
      <TouchableOpacity style={styles.navItem}>
        <Ionicons name="document-text-outline" size={Math.max(addButtonSize * 0.5, 30)} color="#FFF" />
        <Text style={styles.navText}>Docs</Text>
      </TouchableOpacity>
    </View>
  );
}

const styles = StyleSheet.create({
  bottomNav: {
    flexDirection: 'row',
    justifyContent: 'space-around',
    alignItems: 'center',
    paddingVertical: 10,
    backgroundColor: '#74B2C3', // Azul mais escuro para a navbar
    //sombra acima
    shadowColor: '#000',
    shadowOffset: { width: 0, height: -2 },
    shadowOpacity: 0.3,
    shadowRadius: 5,
    elevation: 3,

    //position: 'absolute',
    //bottom: 0,
    //left: 0,
    //right: 0,
  },
  navItem: {
    padding: 8,
    alignItems: 'center',
  },
  activeNavItem: {
    backgroundColor: '#66919D', // Azul ainda mais escuro para indicar ativo
    borderRadius: 12,
    //padding: 8,
  },
  navText: {
    color: '#FFFFFF',
    fontSize: 12,
    marginTop: 4,
  },
  addButton: {
    backgroundColor: '#70A5B4',
    justifyContent: 'center',
    alignItems: 'center',
    marginTop: -28,
    marginLeft: -20,
    elevation: 2,
    shadowColor: '#000',
    shadowOffset: { width: 0, height: 1 },
    shadowOpacity: 0.2,
    shadowRadius: 2,
  },
});

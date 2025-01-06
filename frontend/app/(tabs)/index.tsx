// "HELLO WORLD REMEDIUS"
 
import React, { useState } from 'react';
import { View, Text, TextInput, Button, StyleSheet } from 'react-native';
 
export default function App() {
  const [text, setText] = useState('Hello, Remedius!');
 
  return (
    <View style={styles.container}>
      <Text style={styles.title}>{text}</Text>
      <TextInput
        style={styles.input}
        placeholder="Digite algo"
        onChangeText={(newText) => setText(newText)}
      />
      <Button title="Limpar Texto" onPress={() => setText('')} />
    </View>
  );
}
 
const styles = StyleSheet.create({
  container: { flex: 1, justifyContent: 'center', alignItems: 'center' },
  title: { fontSize: 24, marginBottom: 20 },
  input: { height: 40, borderColor: 'gray', borderWidth: 1, marginBottom: 10, width: '80%', padding: 10 },
});
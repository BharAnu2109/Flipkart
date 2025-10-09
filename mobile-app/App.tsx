import React from 'react';
import {
  SafeAreaView,
  ScrollView,
  StatusBar,
  StyleSheet,
  Text,
  View,
} from 'react-native';
import ProductListScreen from './src/screens/ProductListScreen';

function App(): JSX.Element {
  return (
    <SafeAreaView style={styles.container}>
      <StatusBar barStyle="light-content" backgroundColor="#2874f0" />
      <View style={styles.header}>
        <Text style={styles.headerTitle}>Flipkart</Text>
      </View>
      <ScrollView contentInsetAdjustmentBehavior="automatic">
        <ProductListScreen />
      </ScrollView>
    </SafeAreaView>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
  },
  header: {
    backgroundColor: '#2874f0',
    padding: 15,
    alignItems: 'center',
  },
  headerTitle: {
    color: '#fff',
    fontSize: 24,
    fontWeight: 'bold',
    fontStyle: 'italic',
  },
});

export default App;

import { View, StyleSheet } from 'react-native';
import { StrokeText } from '@studiokico/react-native-stroke-text';

export default function App() {
  return (
    <View style={styles.container}>
      <StrokeText
        text="Test Stroke Text Component"
        fontSize={12}
        color="#000000"
        strokeColor="#c334eb"
        strokeWidth={2}
        align="center"
        numberOfLines={4}
        ellipsis={true}
        style={styles.box}
      />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
    backgroundColor: '#ffffff',
  },
  box: {
    width: 60,
    height: 60,
    marginVertical: 20,
  },
});

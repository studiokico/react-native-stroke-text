import { View, StyleSheet, Text } from 'react-native';
import { StrokeText } from '@studiokico/react-native-stroke-text';

export default function App() {
  return (
    <View style={styles.container}>
      <Text>1. Fixed Height (120)</Text>
      <StrokeText
        text="Fixed Height"
        fontSize={20}
        color="#000000"
        strokeColor="#c334eb"
        strokeWidth={2}
        style={styles.fixedBox}
        align={'center'}
      />

      <Text style={{ marginTop: 20 }}>2. Auto Height (No height style)</Text>
      <StrokeText
        text="Auto Height Test Long Text Long Text Long Text Long Text Long Text"
        fontSize={20}
        color="#ffffff"
        strokeColor="#000000"
        strokeWidth={2}
        numberOfLines={2}
        ellipsis={true}
        width={200}
        style={styles.autoBox}
        align={'center'}
      />

      <Text style={{ marginTop: 20, fontFamily: 'WantedSans-Medium' }}>
        3. Auto Height (No height style) with font.
      </Text>
      <StrokeText
        text="가나다라마바사아자"
        fontSize={13}
        fontFamily={'WantedSans-Medium'}
        color="#000000"
        strokeColor="#ffffff"
        strokeWidth={2}
        numberOfLines={2}
        ellipsis={true}
        width={80}
        style={styles.autoBox}
        align={'center'}
      />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
    backgroundColor: '#eeeeee',
  },
  fixedBox: {
    width: 200,
    height: 120,
    backgroundColor: 'yellow',
    marginBottom: 10,
  },
  autoBox: {
    backgroundColor: 'cyan',
  },
});

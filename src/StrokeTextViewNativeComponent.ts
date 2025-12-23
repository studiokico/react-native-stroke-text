import {
  codegenNativeComponent,
  type ColorValue,
  type ViewProps,
} from 'react-native';
import type {
  Float,
  Int32,
  WithDefault,
} from 'react-native/Libraries/Types/CodegenTypes';

type TextAlign = 'center' | 'left' | 'right';

export interface NativeProps extends ViewProps {
  width?: Float;
  text: string;
  fontSize?: Float;
  color?: ColorValue;
  strokeColor?: ColorValue;
  strokeWidth?: Float;
  fontFamily?: string;
  align?: WithDefault<TextAlign, 'left'>;
  numberOfLines?: Int32;
  ellipsis?: boolean;
}

export default codegenNativeComponent<NativeProps>('StrokeTextView');
// export default codegenNativeComponent<NativeProps>(
//   'StrokeTextView'
// ) as HostComponent<NativeProps>;

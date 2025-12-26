import React from 'react';
import {
  StyleSheet,
  Text,
  View,
  type TextStyle,
  type StyleProp,
} from 'react-native';
import StrokeTextViewNativeComponent, {
  type NativeProps,
} from './StrokeTextViewNativeComponent';

export const StrokeText = (props: NativeProps): React.JSX.Element => {
  const {
    style,
    text,
    fontSize = 14,
    color,
    strokeColor,
    strokeWidth = 0,
    fontFamily,
    align = 'left',
    numberOfLines,
    ellipsis,
    width,
    ...rest
  } = props;

  const ghostTextStyle: StyleProp<TextStyle> = {
    fontSize: fontSize,
    fontFamily: fontFamily,
    textAlign: align || undefined,
    width: width ? Number(width) : undefined,
    margin: strokeWidth,
  };

  return (
    <View style={[styles.container, style]}>
      <Text
        style={[ghostTextStyle, styles.ghostText]}
        numberOfLines={numberOfLines}
        ellipsizeMode={ellipsis ? 'tail' : undefined}
      >
        {text}
      </Text>

      <StrokeTextViewNativeComponent
        style={styles.fill}
        text={text}
        fontSize={fontSize}
        color={color}
        strokeColor={strokeColor}
        strokeWidth={strokeWidth}
        fontFamily={fontFamily}
        align={align}
        numberOfLines={numberOfLines}
        ellipsis={ellipsis}
        width={width}
        {...rest}
      />
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    alignItems: 'center',
    justifyContent: 'center',
  },
  ghostText: {
    opacity: 0,
    zIndex: -1,
    includeFontPadding: false,
    textAlignVertical: 'center',
  },
  fill: {
    position: 'absolute',
    left: 0,
    right: 0,
    top: 0,
    bottom: 0,
  },
});

export type { NativeProps };
export default StrokeText;

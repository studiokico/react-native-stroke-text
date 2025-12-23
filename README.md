# @studiokico/react-native-stroke-text

React Native Stroke Text (New Arch Only)

## Installation


```sh
npm install @studiokico/react-native-stroke-text
```


## Usage


```js
import React from "react";
import { StrokeText } from "@studiokico/react-native-stroke-text";
import { View } from "react-native";

export default function Screen() {
  return (
    <View style={{ flex: 1, justifyContent: "center", alignItems: "center" }}>
      <StrokeText
        text="Test"
        fontSize={50}
        color="#000000"
        strokeColor="#c334eb"
        strokeWidth={20}
        fontFamily="Nunito-Black"
      />
    </View>
  );
}

```

### Props

The following table outlines the props available for the `StrokeText` component:

| Prop            | Type    | Description                                                     |
|-----------------|---------|-----------------------------------------------------------------|
| `text`          | string  | The text content you want to display.                           |
| `fontSize`      | number  | Size of the text font, defining how large the text will be.     |
| `color`         | string  | Color of the text, can use any valid color format.              |
| `strokeColor`   | string  | Color of the stroke (outline) around the text.                  |
| `strokeWidth`   | number  | Width of the stroke, determining the thickness of the outline.  |
| `fontFamily`    | string  | Font family for the text, should match available project fonts. |
| `align`         | string  | Text alignment (default: `center`)                              |
| `numberOfLines` | number  | Number of lines (default: `0`)                                  |
| `ellipsis`      | boolean | Ellipsis (...) (default: `false`)                               |
| `width`         | number  | Text width to enable ellipsis (default: `undefined`)            |

## Ellipsis

```jsx
<StrokeText
  text="Lorem ipsum"
    width={150} // +
    ellipsis={true} // +
    numberOfLines={1} // +
  fontSize={32}
  color="#FFFFFF"
  strokeColor="#000000"
  strokeWidth={2}
  fontFamily="Nunito-Black"
  align="center"
/>

```

<h1 align="center">
  <img width="450" src="docs/ellipsis.jpeg"/>
</h1>



## Contributing

- [Development workflow](CONTRIBUTING.md#development-workflow)
- [Sending a pull request](CONTRIBUTING.md#sending-a-pull-request)
- [Code of conduct](CODE_OF_CONDUCT.md)

## License

MIT

---

Made with [create-react-native-library](https://github.com/callstack/react-native-builder-bob)

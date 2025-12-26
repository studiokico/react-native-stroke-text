#import "RNStrokeTextView.h"

#import <react/renderer/components/RNStrokeTextSpec/ComponentDescriptors.h>
#import <react/renderer/components/RNStrokeTextSpec/EventEmitters.h>
#import <react/renderer/components/RNStrokeTextSpec/Props.h>
#import <react/renderer/components/RNStrokeTextSpec/RCTComponentViewHelpers.h>

#import <React/RCTFabricComponentsPlugins.h>
#import <React/RCTConversions.h>

#if __has_include("StrokeText-Swift.h")
#import "StrokeText-Swift.h"
#else
#import <StrokeText/StrokeText-Swift.h>
#endif

using namespace facebook::react;

@interface RNStrokeTextView () <RCTRNStrokeTextViewViewProtocol>
@end

@implementation RNStrokeTextView {
    StrokeTextView *_view;
}

+ (ComponentDescriptorProvider)componentDescriptorProvider {
    return concreteComponentDescriptorProvider<RNStrokeTextViewComponentDescriptor>();
}

- (instancetype)initWithFrame:(CGRect)frame {
    if (self = [super initWithFrame:frame]) {
        static const auto defaultProps = std::make_shared<const RNStrokeTextViewProps>();
        _props = defaultProps;

        _view = [[StrokeTextView alloc] initWithFrame:self.bounds];

        self.contentView = _view;
    }
    return self;
}

- (void)updateProps:(Props::Shared const &)props oldProps:(Props::Shared const &)oldProps {
    const auto &oldViewProps = *std::static_pointer_cast<const RNStrokeTextViewProps>(_props);
    const auto &newViewProps = *std::static_pointer_cast<const RNStrokeTextViewProps>(props);

    if (oldViewProps.text != newViewProps.text) {
        _view.text = RCTNSStringFromString(newViewProps.text);
    }

    if (oldViewProps.fontFamily != newViewProps.fontFamily || oldViewProps.fontSize != newViewProps.fontSize) {
        NSString *fontName = RCTNSStringFromString(newViewProps.fontFamily);
        CGFloat fontSize = newViewProps.fontSize; // Float -> CGFloat

        UIFont *font = [UIFont fontWithName:fontName size:fontSize];
        if (!font) {
            font = [UIFont systemFontOfSize:fontSize];
        }
        _view.font = font;
    }

    if (oldViewProps.color != newViewProps.color) {
        _view.textColor = RCTUIColorFromSharedColor(newViewProps.color);
    }
    if (oldViewProps.strokeColor != newViewProps.strokeColor) {
        _view.strokeColor = RCTUIColorFromSharedColor(newViewProps.strokeColor);
    }

    if (oldViewProps.strokeWidth != newViewProps.strokeWidth) {
        _view.strokeWidth = newViewProps.strokeWidth;
    }

    if (oldViewProps.align != newViewProps.align) {
        switch (newViewProps.align) {
            case RNStrokeTextViewAlign::Center:
                _view.textAlignment = NSTextAlignmentCenter;
                break;
            case RNStrokeTextViewAlign::Right:
                _view.textAlignment = NSTextAlignmentRight;
                break;
            case RNStrokeTextViewAlign::Left:
            default:
                _view.textAlignment = NSTextAlignmentLeft;
                break;
        }
    }

    if (oldViewProps.numberOfLines != newViewProps.numberOfLines) {
        _view.numberOfLines = newViewProps.numberOfLines;
    }

    if (oldViewProps.ellipsis != newViewProps.ellipsis) {
        _view.lineBreakMode = newViewProps.ellipsis ? NSLineBreakByTruncatingTail : NSLineBreakByWordWrapping;
    }

    if (oldViewProps.width != newViewProps.width) {
        _view.customWidth = newViewProps.width;
    }

    [super updateProps:props oldProps:oldProps];
}

@end

Class<RCTComponentViewProtocol> RNStrokeTextViewCls(void)
{
  return RNStrokeTextView.class;
}

import UIKit

@objc(StrokeTextView)
public class StrokeTextView: UILabel {
    @objc public var strokeColor: UIColor = .white {
        didSet { setNeedsDisplay() }
    }

    @objc public var strokeWidth: CGFloat = 0 {
        didSet {
            invalidateIntrinsicContentSize()
            setNeedsDisplay()
        }
    }

    @objc public var customWidth: CGFloat = 0 {
        didSet { invalidateIntrinsicContentSize() }
    }

    public override init(frame: CGRect) {
        super.init(frame: frame)
        self.commonInit()
    }

    public required init?(coder: NSCoder) {
        super.init(coder: coder)
        self.commonInit()
    }

    private func commonInit() {
        self.numberOfLines = 0
        self.clipsToBounds = false
        self.backgroundColor = .clear

        self.setContentCompressionResistancePriority(.required, for: .horizontal)
        self.setContentCompressionResistancePriority(.required, for: .vertical)
    }

    public override func layoutSubviews() {
        super.layoutSubviews()

        if customWidth > 0 {
            self.preferredMaxLayoutWidth = customWidth
        } else {
            if self.bounds.width > 0 {
                self.preferredMaxLayoutWidth = self.bounds.width
            }
        }
    }

    public override func drawText(in rect: CGRect) {
        let shadowOffset = self.shadowOffset
        let originalTextColor = self.textColor

        let context = UIGraphicsGetCurrentContext()
        context?.saveGState()

        let height = self.textRect(forBounds: rect, limitedToNumberOfLines: self.numberOfLines).height
        let topAlignedRect = CGRect(x: rect.origin.x, y: rect.origin.y, width: rect.width, height: height)

        if strokeWidth > 0 {
            context?.setLineWidth(strokeWidth)
            context?.setLineJoin(.round)
            context?.setTextDrawingMode(.stroke)
            self.textColor = strokeColor

            super.drawText(in: topAlignedRect)
        }

        context?.setTextDrawingMode(.fill)
        self.textColor = originalTextColor
        self.shadowOffset = CGSize.zero

        super.drawText(in: topAlignedRect)

        self.shadowOffset = shadowOffset
        context?.restoreGState()
    }

    public override var intrinsicContentSize: CGSize {
        let size = super.intrinsicContentSize
        let extra = strokeWidth * 2

        let newWidth = size.width + extra
        let newHeight = size.height + extra

        return CGSize(width: newWidth, height: newHeight)
    }
}

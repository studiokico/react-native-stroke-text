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
    }

    public override func drawText(in rect: CGRect) {
        let shadowOffset = self.shadowOffset
        let originalTextColor = self.textColor

        let context = UIGraphicsGetCurrentContext()
        context?.saveGState()

        if strokeWidth > 0 {
            context?.setLineWidth(strokeWidth)
            context?.setLineJoin(.round)
            context?.setTextDrawingMode(.stroke)
            self.textColor = strokeColor
            super.drawText(in: rect)
        }

        context?.setTextDrawingMode(.fill)
        self.textColor = originalTextColor
        self.shadowOffset = CGSize.zero
        super.drawText(in: rect)

        self.shadowOffset = shadowOffset
        context?.restoreGState()
    }

    public override var intrinsicContentSize: CGSize {
        let size = super.intrinsicContentSize
        let extra = strokeWidth
        return CGSize(width: size.width + extra, height: size.height + extra)
    }
}

package com.studiokico.stroketext

import com.facebook.react.module.annotations.ReactModule
import com.facebook.react.uimanager.SimpleViewManager
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.ViewManagerDelegate
import com.facebook.react.uimanager.annotations.ReactProp
import com.facebook.react.viewmanagers.RNStrokeTextViewManagerDelegate
import com.facebook.react.viewmanagers.RNStrokeTextViewManagerInterface

@ReactModule(name = StrokeTextViewManager.REACT_CLASS)
class StrokeTextViewManager : SimpleViewManager<StrokeTextView>(), RNStrokeTextViewManagerInterface<StrokeTextView> {

    companion object {
        const val REACT_CLASS = "RNStrokeTextView"
    }

    private val delegate: ViewManagerDelegate<StrokeTextView> = RNStrokeTextViewManagerDelegate(this)

    override fun getDelegate(): ViewManagerDelegate<StrokeTextView> = delegate

    override fun getName(): String = REACT_CLASS

    override fun createViewInstance(context: ThemedReactContext): StrokeTextView {
        return StrokeTextView(context)
    }

    @ReactProp(name = "text")
    override fun setText(view: StrokeTextView, text: String?) {
        view.text = text ?: ""
    }

    @ReactProp(name = "fontSize")
    override fun setFontSize(view: StrokeTextView, fontSize: Float) {
        view.fontSize = fontSize
    }

    @ReactProp(name = "color", customType = "Color")
    override fun setColor(view: StrokeTextView, color: Int?) {
        view.textColor = color ?: 0xFF000000.toInt()
    }

    @ReactProp(name = "strokeColor", customType = "Color")
    override fun setStrokeColor(view: StrokeTextView, color: Int?) {
        view.strokeColor = color ?: 0xFFFFFFFF.toInt()
    }

    @ReactProp(name = "strokeWidth")
    override fun setStrokeWidth(view: StrokeTextView, width: Float) {
        view.strokeWidth = width
    }

    @ReactProp(name = "fontFamily")
    override fun setFontFamily(view: StrokeTextView, fontFamily: String?) {
        view.fontFamily = fontFamily ?: ""
    }

    @ReactProp(name = "width")
    override fun setWidth(view: StrokeTextView, width: Float) {
        view.customWidth = width
    }

    @ReactProp(name = "align")
    override fun setAlign(view: StrokeTextView, align: String?) {
        view.align = align ?: "left"
    }

    @ReactProp(name = "numberOfLines", defaultInt = 0)
    override fun setNumberOfLines(view: StrokeTextView, numberOfLines: Int) {
        view.numberOfLines = numberOfLines
    }

    @ReactProp(name = "ellipsis")
    override fun setEllipsis(view: StrokeTextView, ellipsis: Boolean) {
        view.ellipsis = ellipsis
    }
}

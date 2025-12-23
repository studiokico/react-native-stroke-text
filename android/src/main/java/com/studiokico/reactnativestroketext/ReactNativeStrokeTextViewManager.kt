package com.studiokico.reactnativestroketext

import android.graphics.Color
import com.facebook.react.module.annotations.ReactModule
import com.facebook.react.uimanager.SimpleViewManager
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.ViewManagerDelegate
import com.facebook.react.uimanager.annotations.ReactProp
import com.facebook.react.viewmanagers.ReactNativeStrokeTextViewManagerInterface
import com.facebook.react.viewmanagers.ReactNativeStrokeTextViewManagerDelegate

@ReactModule(name = ReactNativeStrokeTextViewManager.NAME)
class ReactNativeStrokeTextViewManager : SimpleViewManager<ReactNativeStrokeTextView>(),
  ReactNativeStrokeTextViewManagerInterface<ReactNativeStrokeTextView> {
  private val mDelegate: ViewManagerDelegate<ReactNativeStrokeTextView>

  init {
    mDelegate = ReactNativeStrokeTextViewManagerDelegate(this)
  }

  override fun getDelegate(): ViewManagerDelegate<ReactNativeStrokeTextView>? {
    return mDelegate
  }

  override fun getName(): String {
    return NAME
  }

  public override fun createViewInstance(context: ThemedReactContext): ReactNativeStrokeTextView {
    return ReactNativeStrokeTextView(context)
  }

  @ReactProp(name = "color")
  override fun setColor(view: ReactNativeStrokeTextView?, color: String?) {
    view?.setBackgroundColor(Color.parseColor(color))
  }

  companion object {
    const val NAME = "ReactNativeStrokeTextView"
  }
}

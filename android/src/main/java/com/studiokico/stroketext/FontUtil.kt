package com.studiokico.stroketext

import android.content.Context
import android.content.res.AssetManager
import android.graphics.Typeface
import com.facebook.react.views.text.ReactFontManager
import java.util.Locale
import java.util.concurrent.ConcurrentHashMap

object FontUtil {
    private val CACHE = ConcurrentHashMap<String, Typeface>()
    @Volatile
    private var ASSET_INDEX: Map<String, String>? = null

    fun getFont(context: Context, family: String?, style: Int = Typeface.NORMAL): Typeface {
        if (family.isNullOrEmpty()) return Typeface.defaultFromStyle(style)

        val appCtx = context.applicationContext
        val am = appCtx.assets
        val key = normalizeKey(family, style)

        CACHE[key]?.let { return it }

        ReactFontManager.getInstance().getTypeface(family, style, am)?.let {
            CACHE[key] = it
            return it
        }

        resolveAssetPath(am, family)?.let { path ->
            try {
                var tf = Typeface.createFromAsset(am, path)
                if (style != Typeface.NORMAL) {
                    tf = Typeface.create(tf, style)
                }
                CACHE[key] = tf
                return tf
            } catch (e: Exception) {
            }
        }

        val defaultTf = Typeface.defaultFromStyle(style)
        CACHE[key] = defaultTf
        return defaultTf
    }

    private fun resolveAssetPath(am: AssetManager, family: String): String? {
        if (ASSET_INDEX == null) {
            synchronized(this) {
                if (ASSET_INDEX == null) {
                    ASSET_INDEX = buildAssetIndex(am, "fonts")
                }
            }
        }
        return ASSET_INDEX?.get(family.lowercase(Locale.US))
    }

    private fun buildAssetIndex(am: AssetManager, folder: String): Map<String, String> {
        val map = HashMap<String, String>()
        try {
            val files = am.list(folder)
            files?.forEach { filename ->
                val lower = filename.lowercase(Locale.US)
                if (lower.endsWith(".ttf") || lower.endsWith(".otf")) {
                    val base = lower.replace(Regex("\\.(ttf|otf)$"), "")
                    map[base] = "$folder/$filename"
                }
            }
        } catch (ignored: Exception) { }
        return map
    }

    private fun normalizeKey(family: String, style: Int): String {
        return "${family.lowercase(Locale.US)}|$style"
    }
}

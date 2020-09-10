package alzaichsank.com.simple_note.utils

import android.content.Context
import android.os.Build
import id.digiva.health.data.local.LanguageServiceManager
import id.digiva.health.scheme.appsetting.LanguageSetting
import java.util.*

object LocalizationHelper {

    private var languageManager: LanguageServiceManager? = null

    fun getLocale(context: Context): Locale {
        val languageProvider = getLanguage(context)
        val country = languageProvider.countryCode
        val language = languageProvider.languageCode
        return Locale(language, country)
    }

    fun applyLanguageContext(context: Context): Context {
        try {
            val locale = getLocale(context)
            val configuration = context.resources.configuration
            val displayMetrics = context.resources.displayMetrics

            Locale.setDefault(locale)

            return if (isAtLeastSdkVersion(Build.VERSION_CODES.JELLY_BEAN_MR1)) {
                configuration.setLocale(locale)
                context.createConfigurationContext(configuration)
            } else {
                configuration.locale = locale
                context.resources.updateConfiguration(configuration, displayMetrics)
                context
            }
        } catch (exception: Exception) {
            return context
        }
    }

    private fun getLanguage(context: Context) : LanguageSetting {
        if (languageManager == null) languageManager = LanguageServiceManager.instance(context)
        return languageManager?.getLanguage() ?: LanguageSetting.indonesia()
    }

    private fun isAtLeastSdkVersion(versionCode: Int): Boolean {
        return Build.VERSION.SDK_INT >= versionCode
    }

}
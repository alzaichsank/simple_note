package alzaichsank.com.simple_note.data

import alzaichsank.com.simple_note.data.base.BasePreferenceService
import alzaichsank.com.simple_note.schema.LanguageSetting
import alzaichsank.com.simple_note.schema.constanta.LanguageType
import android.content.Context

class LanguageServiceManager private constructor(context: Context) :
    BasePreferenceService(
        context,
        PREF_CONSENT
    ) {

    init {
        INSTANCE = this
    }

    fun saveLanguage(data: LanguageSetting) {
        putObject(KEY_LANGUAGE_DATA, data)
    }

    fun getLanguage(): LanguageSetting {
        return try {
            val defaultLanguage =
                if (getString(
                        KEY_LANGUAGE_DATA,
                        VALUE_LANGUAGE_DATA
                    ).contains(LanguageType.ENGLISH)
                ) {
                    LanguageSetting.english()
                } else {
                    LanguageSetting.indonesia()
                }
            getObject(KEY_LANGUAGE_DATA, defaultLanguage, LanguageSetting::class.java)
        } catch (exception: Exception) {
            LanguageSetting.indonesia()
        }
    }

    companion object {
        @JvmStatic
        @Volatile
        private var INSTANCE: LanguageServiceManager? = null

        @Synchronized
        fun instance(context: Context): LanguageServiceManager {
            if (INSTANCE == null) {
                INSTANCE =
                    LanguageServiceManager(context)
            }

            return INSTANCE!!
        }

        private const val PREF_CONSENT = "LanguagePreference"

        private const val KEY_LANGUAGE_DATA =
            "app.local.preferences.language"

        private const val VALUE_LANGUAGE_DATA = "id"
    }
}
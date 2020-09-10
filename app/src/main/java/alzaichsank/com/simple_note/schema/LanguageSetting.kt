package alzaichsank.com.simple_note.schema

import alzaichsank.com.simple_note.schema.constanta.CountryName
import alzaichsank.com.simple_note.schema.constanta.CountryType
import alzaichsank.com.simple_note.schema.constanta.LanguageType
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LanguageSetting(
    val countryName: String,
    val countryCode: String,
    val languageCode: String
) : Parcelable {

    companion object {
        fun indonesia() =
            LanguageSetting(CountryName.INDONESIA, CountryType.INDONESIA, LanguageType.INDONESIA)
        fun english() =
            LanguageSetting(CountryName.INDONESIA, CountryType.ENGLISH, LanguageType.ENGLISH)
    }
}
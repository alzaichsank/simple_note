package alzaichsank.com.simple_note.data.base

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import com.google.gson.internal.bind.DateTypeAdapter
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import org.json.JSONException
import java.util.*
import kotlin.collections.ArrayList

open class BasePreferenceService(context: Context, name: String = DEFAULT_PREFERENCE_NAME) {

    private val gson = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
        .registerTypeAdapter(Date::class.java, DateTypeAdapter())
        .create()
    private val sharedPreferences: SharedPreferences = if (name == DEFAULT_PREFERENCE_NAME) {
        PreferenceManager.getDefaultSharedPreferences(context)
    } else {
        context.getSharedPreferences(name, Context.MODE_PRIVATE)
    }

    fun putBoolean(key: String, value: Boolean) {
        getEditor().putBoolean(key, value).apply()
    }

    fun getBoolean(key: String, defValue: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, defValue)
    }

    fun putInt(key: String, value: Int) {
        getEditor().putInt(key, value).apply()
    }

    fun getInt(key: String, defValue: Int): Int {
        return sharedPreferences.getInt(key, defValue)
    }

    fun putString(key: String, value: String) {
        val editor = getEditor()
        editor.putString(key, value)
        editor.apply()
    }

    fun getString(key: String, defValue: String): String {
        return sharedPreferences.getString(key, defValue) ?: defValue
    }

    fun putFloat(key: String, value: Float) {
        getEditor().putFloat(key, value).apply()
    }

    fun getFloat(key: String, defValue: Float): Float {
        return sharedPreferences.getFloat(key, defValue)
    }

    fun putLong(key: String, value: Long) {
        getEditor().putLong(key, value).apply()
    }

    fun getLong(key: String, defValue: Long): Long {
        return sharedPreferences.getLong(key, defValue)
    }

    fun putObject(key: String, `object`: Any) {
        val objectString = convertObjectToString(`object`)
        putString(key, objectString)
    }

    fun <T> getObject(key: String, defValue: T, tClass: Class<T>): T {
        val defValueString = convertObjectToString(defValue)
        val jsonValue = sharedPreferences.getString(key, defValueString) ?: defValueString
        return convertJsonStringToObject(jsonValue, tClass) ?: defValue
    }

    private fun <T> convertJsonStringToObject(jsonValue: String, tClass: Class<T>): T? {
        return gson.fromJson(jsonValue, tClass)
    }

    fun putListString(key: String, value: List<String>) {
        putListObject(key, value)
    }

    @Throws(JSONException::class)
    fun getListString(key: String, defValue: List<String>): List<String> {
        val defValueString = convertObjectToString(defValue)
        val jsonValue = sharedPreferences.getString(key, defValueString) ?: defValueString
        return convertJsonStringToListString(jsonValue)
    }

    private fun convertJsonStringToListString(jsonValue: String): List<String> {
        val type = object : TypeToken<List<String>>() {

        }.type
        return gson.fromJson(jsonValue, type)
    }

    fun putListObject(key: String, value: List<Any>) {
        val jsonValue = convertObjectToString(value)
        val editor = getEditor()
        editor.putString(key, jsonValue)
        editor.apply()
    }

    @Throws(JSONException::class)
    fun <T> getListObject(key: String, defValue: List<T>, type: Class<T>): List<T> {
        val defValueString = convertObjectToString(defValue)
        val jsonValue = sharedPreferences.getString(key, defValueString) ?: defValueString
        return convertJsonStringToListObject(jsonValue, type)
    }

    private fun <T> convertObjectToString(value: T): String {
        return gson.toJson(value)
    }

    @Throws(JSONException::class)
    private fun <T> convertJsonStringToListObject(jsonValue: String, type: Class<T>): List<T> {
        val value = ArrayList<T>()
        var jsonElement: JsonElement
        val jsonArray = JSONArray(jsonValue)
        for (i in 0 until jsonArray.length()) {
            jsonElement = JsonParser().parse(jsonArray.getString(i))
            val t = gson.fromJson(jsonElement, type)
            value.add(t)
        }
        return value
    }

    fun contains(key: String): Boolean {
        return sharedPreferences.contains(key)
    }

    fun clearPreference() {
        sharedPreferences.edit().clear().apply()
    }

    fun removePreference(key: String) {
        getEditor().remove(key).apply()
    }

    private fun getEditor(): SharedPreferences.Editor {
        return sharedPreferences.edit()
    }

    companion object {
        const val DEFAULT_PREFERENCE_NAME = "default"
    }

}
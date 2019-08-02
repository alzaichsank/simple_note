package alzaichsank.com.base_template_kotlin.utils

import android.util.Log
//import com.crashlytics.android.Crashlytics

object Logger {

    var logEnabled: Boolean = true
    private const val TAG = "Edokterku User"

//    fun crashlytics(message: String) {
//        Crashlytics.logException(Throwable(message))
//        Logger.debug(message)
//    }

    fun debug(message: String) {
        debug(TAG, message)
    }

    fun debug(tag: String, message: String) {
        if (logEnabled) {
            Log.d(tag, ">> $message")
        }
    }

    fun error(message: String, throwable: Throwable) {
        error(TAG, message, throwable)
    }

    fun error(tag: String, message: String, throwable: Throwable) {
        if (logEnabled) {
            Log.e(tag, ">> $message", throwable)
        }
    }
}
package alzaichsank.com.base_template_kotlin.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
import android.provider.Settings
import java.net.NetworkInterface
import java.util.*

object DeviceHelper {
    private val PHONE = "Phone"
    private val TABLET = "Tablet"

    fun initAlert(): SoundPool {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val builder = SoundPool.Builder()
            builder.setAudioAttributes(AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build())
                .setMaxStreams(10)
                .build()
        } else {
            SoundPool(10, AudioManager.STREAM_NOTIFICATION, 1)
        }
    }

    fun getDeviceOsVersionName(): String {
        val builder = StringBuilder()
        builder.append("Android ").append(Build.VERSION.RELEASE)

        val fields = Build.VERSION_CODES::class.java.fields
        for (field in fields) {
            val fieldName = field.name
            var fieldValue = -1

            try {
                fieldValue = field.getInt(Any())
            } catch (e: Exception) {
                e.printStackTrace()
            }

            if (fieldValue == Build.VERSION.SDK_INT) {
                builder.append(" / ").append(fieldName).append(" / ")
                builder.append("Version Code ").append(fieldValue)
            }
        }
        return builder.toString()
    }

    fun getAppVersionName(context: Context): String {
        try {
            val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            return packageInfo.versionName
        } catch (e: PackageManager.NameNotFoundException) {
            Logger.error("DeviceUtil", e)
            return ""
        }

    }

    @SuppressLint("HardwareIds")
    fun getUniqueId(context: Context): String {
        try {
            return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
        } catch (e: Exception) {
            Logger.error("DeviceUtil", e)
            return "NA"
        }

    }

    fun getIPAddress(): String {
        try {
            val interfaces = Collections.list(NetworkInterface.getNetworkInterfaces())
            for (networkInterface in interfaces) {
                val addressList = Collections.list(networkInterface.inetAddresses)
                for (address in addressList) {
                    if (!address.isLoopbackAddress) {
                        return address.hostAddress
                    }
                }
            }
        } catch (e: Exception) {
            Logger.error("DeviceUtil", e)
        }

        return "NA"
    }


    fun getDeviceType(context: Context): String {
        return if (context.resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK >= Configuration.SCREENLAYOUT_SIZE_LARGE)
            TABLET
        else
            PHONE
    }

    fun getDeviceOs(): String {
        return android.os.Build.VERSION.SDK_INT.toString()
    }
}
package alzaichsank.com.base_template_kotlin.extensions

import android.util.Patterns
import java.util.regex.Pattern

fun String.toAuthorization(): String {
    return "Bearer $this"
}

fun String.isValidEmail(): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun String.isValidPhoneNumber(): Boolean {
    return Patterns.PHONE.matcher(this).matches()
}

fun String?.toRealAmount(): Int {
    return if (this != null) {
        val amountBuilder = this.replace("Rp. ", "")
        if (amountBuilder.contains(".")) {
            val amount = amountBuilder.substring(0, indexOf(".")).replace(",", "")
            amount.toInt()
        } else if (!amountBuilder.contains(".")) {
            val amount = amountBuilder.replace(",", ".")
            amount.toInt()
        } else {
            this.toInt()
        }
    } else {
        0
    }
}

fun String.capitalizeEveryFirstLetter(): String {
    val capBuffer = StringBuffer()
    val capMatcher = Pattern.compile("([a-z])([a-z]*)", Pattern.CASE_INSENSITIVE).matcher(this)
    while (capMatcher.find()) {
        capMatcher.appendReplacement(capBuffer, capMatcher.group(1).toUpperCase() + capMatcher.group(2).toLowerCase())
    }

    return capMatcher.appendTail(capBuffer).toString()
}
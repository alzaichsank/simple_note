package alzaichsank.com.simple_note.extensions

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

fun Double?.toFormattedAmount(): String {
    val amountBuilder = StringBuilder().append("Rp. ")
    if (this != null) {
        val realAmount = this
        try {
            val otherSymbols = DecimalFormatSymbols(Locale.US)
            otherSymbols.decimalSeparator = '.'
            otherSymbols.groupingSeparator = ','
            val formatted = DecimalFormat("#,###.##", otherSymbols).format(realAmount)
            amountBuilder.append(formatted)
        } catch (e: NullPointerException) {
            amountBuilder.append(realAmount)
        } catch (e: IllegalArgumentException) {
            amountBuilder.append(realAmount)
        }
        amountBuilder.toString()
    } else {
        amountBuilder.append("0").toString()
    }
    return amountBuilder.toString()
}

fun Double?.toFormattedAmountWithoutCurrency(): String {
    val amountBuilder = StringBuilder().append("")

    if (this != null) {
        val realAmount = this
        try {
            val otherSymbols = DecimalFormatSymbols(Locale.US)
            otherSymbols.decimalSeparator = '.'
            otherSymbols.groupingSeparator = ','
            val formatted = DecimalFormat("#,###.##", otherSymbols).format(realAmount)
            amountBuilder.append(formatted)
        } catch (e: NullPointerException) {
            amountBuilder.append(realAmount)
        } catch (e: IllegalArgumentException) {
            amountBuilder.append(realAmount)
        }
        amountBuilder.toString()
    } else {
        amountBuilder.append("0").toString()
    }
    return amountBuilder.toString()
}
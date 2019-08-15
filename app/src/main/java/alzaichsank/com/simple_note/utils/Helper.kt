package alzaichsank.com.simple_note.utils

import alzaichsank.com.simple_note.utils.Constants.HIDE_ERROR
import alzaichsank.com.simple_note.utils.Constants.SHOW_ERROR
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.View
import com.google.android.material.textfield.TextInputLayout
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

object Helper {
    const val WIDTH_INDEX = 0
    const val HEIGHT_INDEX = 1
    fun isScreenSizeRetrieved(widthHeight: IntArray): Boolean {
        return widthHeight[WIDTH_INDEX] != 0 && widthHeight[HEIGHT_INDEX] != 0
    }

    /*This method use for show and hide error message if user's input not valid for EditText*/
    fun showErrorMessage(view: TextInputLayout, whichCase: Int, message: String?) {
        when (whichCase) {
            /*If field not error, hide error message*/
            HIDE_ERROR -> {
                if (view.childCount == 2) {
                    view.getChildAt(1).visibility = View.INVISIBLE
                }
                view.error = ""
            }
            /*If field error not, show error message*/
            SHOW_ERROR -> {
                if (view.childCount == 2) {
                    view.getChildAt(1).visibility = View.VISIBLE
                }
                view.error = message
            }
        }
    }

    fun getFormattedAmount(amount: Double): String {
        return try {
            val otherSymbols = DecimalFormatSymbols(Locale.US)
            otherSymbols.decimalSeparator = '.'
            otherSymbols.groupingSeparator = ','
            DecimalFormat("#,###.##", otherSymbols).format(amount)
        }
        catch (e: NullPointerException) {
            "$amount"
        }
        catch (e: IllegalArgumentException) {
            "$amount"
        }
    }

    fun replace_underScoreWithSpace(value: String?): CharSequence? {
        if (value.isNullOrBlank()) {
            return ""
        }
        return value!!.replace('_', ' ')
    }


    fun copyFileText(context: Context, text: String?) {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText(android.R.attr.label.toString(), text)
        clipboard.primaryClip = clip
    }
}
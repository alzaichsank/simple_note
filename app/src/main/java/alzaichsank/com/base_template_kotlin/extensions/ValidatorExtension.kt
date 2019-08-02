package alzaichsank.com.base_template_kotlin.extensions

import alzaichsank.com.base_template_kotlin.utils.Constants.EMAIL_FORMAT
import alzaichsank.com.base_template_kotlin.utils.Constants.PASSWORD_FORMAT
import android.text.TextUtils
import android.util.Patterns
import java.util.regex.Pattern

/*This method use for checking user's input value and return boolean*/
fun CharSequence?.isUserInputValid(whichCase: Int): Boolean {
    if (this != null) {
        val PASSWORD_PATTERN: String =
            "((?=.*\\d)(?=.*[a-z]).{8,20})"
        var pattern = Pattern.compile(PASSWORD_PATTERN)

        when (whichCase) {
            /*Check user's input for email format*/
//            EMAIL_FORMAT -> return (!TextUtils.isEmpty(this) && Patterns.EMAIL_ADDRESS.matcher(this).matches()) && !this.isEmpty()
            /*Check user's input for password, minimum 8 character*/
            PASSWORD_FORMAT -> return this.length >= 8 && pattern.matcher(this).matches()
        }
    }
    return false
}

fun String.isUserInputValidOrEmpty(whichCase: Int): Boolean {
    when (whichCase) {
        /*Check user's input for email format*/
//        EMAIL_FORMAT -> return (!TextUtils.isEmpty(this) && Patterns.EMAIL_ADDRESS.matcher(this).matches()) || this.isEmpty()
        /*Check user's input for password, minimum 6 character*/
        PASSWORD_FORMAT -> return this.length >= 6
    }
    return false
}
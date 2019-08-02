package alzaichsank.com.base_template_kotlin.utils

import alzaichsank.com.base_template_kotlin.R
import android.annotation.SuppressLint
import android.content.Context
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.joda.time.LocalDate
import org.joda.time.LocalDateTime
import org.joda.time.format.DateTimeFormat
import java.util.*

object DateTimeHelper {

    private val indonesiaLocale = Locale("in")
    private val usLocale = Locale("us")

    private const val HISTORY_MONTH_YEAR_FORMAT_PATTERN = "MM-yyyy"
    private val historyMonthYearFormat = DateTimeFormat
            .forPattern(HISTORY_MONTH_YEAR_FORMAT_PATTERN)
            .withLocale(indonesiaLocale)

    private const val HISTORY_TODAY_DATE_FORMAT_PATTERN = "dd"
    private val historyTodayDateFormat = DateTimeFormat
            .forPattern(HISTORY_TODAY_DATE_FORMAT_PATTERN)
            .withLocale(indonesiaLocale)

    private const val HISTORY_DATE_FORMAT_PATTERN = "dd-MM-yyyy"
    private val historyDateFormat = DateTimeFormat
            .forPattern(HISTORY_DATE_FORMAT_PATTERN)
            .withLocale(indonesiaLocale)

    private const val HISTORY_TIME_FORMAT_PATTERN = "HH:mm"
    private val historyTimeFormat = DateTimeFormat
            .forPattern(HISTORY_TIME_FORMAT_PATTERN)
            .withLocale(indonesiaLocale)

    private const val REPORT_DATE_FORMAT_PATTERN = "MMMM yyyy"
    private val reportDateFormatter = DateTimeFormat
            .forPattern(REPORT_DATE_FORMAT_PATTERN)
            .withLocale(indonesiaLocale)

    private const val DATE_TIME_FORMAT_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"
    private val dateTimeFormatter = DateTimeFormat
            .forPattern(DATE_TIME_FORMAT_PATTERN)
            .withLocale(usLocale)

    private const val DATE_FORMAT_PATTERN = "EEEE, dd MMMM yyyy"
    private val dateFormatter = DateTimeFormat
            .forPattern(DATE_FORMAT_PATTERN)
            .withLocale(indonesiaLocale)

    private const val SHORT_DATE_FORMAT_PATTERN = "dd MMMM yyyy"
    private val shortDateFormatter = DateTimeFormat
            .forPattern(SHORT_DATE_FORMAT_PATTERN)
            .withLocale(indonesiaLocale)

    private const val TRANSACTION_DATE_HORIZONTAL_FORMAT_PATTERN = "dd MMM, HH:mm"
    private val transactionDateHorizontalFormatter = DateTimeFormat
            .forPattern(TRANSACTION_DATE_HORIZONTAL_FORMAT_PATTERN)
            .withLocale(indonesiaLocale)

    private const val TIMELINE_FORMAT_PATTERN = "dd MMM, HH:mm"
    private val timelineFormatter = DateTimeFormat
            .forPattern(TIMELINE_FORMAT_PATTERN)
            .withLocale(indonesiaLocale)

    private const val STRUCT_FORMAT = "MMddHHmmss"
    private val structFormatter = DateTimeFormat
            .forPattern(STRUCT_FORMAT)
            .withLocale(indonesiaLocale)

    private const val SHORT_TINY_TIME_FORMAT_PATTERN = "mm"
    private val tinyTransactionTimeFormatter = DateTimeFormat
            .forPattern(SHORT_TINY_TIME_FORMAT_PATTERN)
            .withLocale(indonesiaLocale)

    private const val SHORT_TRANSACTION_TIME_FORMAT_PATTERN = "HH:mm"
    private val shortTransactionTimeFormatter = DateTimeFormat
            .forPattern(SHORT_TRANSACTION_TIME_FORMAT_PATTERN)
            .withLocale(indonesiaLocale)

    private const val SHORT_TRANSACTION_DATE_FORMAT_PATTERN = "dd MMM yyyy"
    private val shortTransactionDateFormatter = DateTimeFormat
            .forPattern(SHORT_TRANSACTION_DATE_FORMAT_PATTERN)
            .withLocale(indonesiaLocale)

    private const val FULL_DATE_TIME_FORMAT_PATTERN = "EEEE, dd MMMM yyyy, h:mm a"
    private val fullDateTimeFormatter = DateTimeFormat
            .forPattern(FULL_DATE_TIME_FORMAT_PATTERN)
            .withLocale(indonesiaLocale)

    private const val TRANSACTION_DATE_MONTH_HORIZONTAL_FORMAT_PATTERN = "dd MMM"
    private val transactionDateMonthHorizontalFormatter = DateTimeFormat
            .forPattern(TRANSACTION_DATE_HORIZONTAL_FORMAT_PATTERN)
            .withLocale(indonesiaLocale)

    private const val DATE_TIME_FULL_FORMAT_PATTERN = "dd MMMM yyyy h:mm a"
    private val dateTimeFullFormatter = DateTimeFormat
            .forPattern(DATE_TIME_FULL_FORMAT_PATTERN)
            .withZone(DateTimeZone.forID("Asia/Jakarta"))
            .withLocale(usLocale)

    private const val DATE_TIME_FORMAT_PATTERN_DEFAULT = "yyyy-MM-dd HH:mm:ss"
    private val dateTimeFormatterDefault = DateTimeFormat
            .forPattern(DATE_TIME_FORMAT_PATTERN_DEFAULT)
            .withZone(DateTimeZone.forID("Asia/Jakarta"))
            .withLocale(indonesiaLocale)

    private const val LOCAL_DATE_FORMAT_PATTERN = "yyyy-MM-dd"
    private val localDateFormatter = DateTimeFormat
            .forPattern(LOCAL_DATE_FORMAT_PATTERN)
            .withLocale(indonesiaLocale)

    private const val DATE_FIRST_FORMAT_PATTERN = "d MMMM, yyyy"
    private val DateFirstFormatter = DateTimeFormat
            .forPattern(DATE_FIRST_FORMAT_PATTERN)
            .withZone(DateTimeZone.forID("Asia/Jakarta"))
            .withLocale(indonesiaLocale)

    private const val DIRECTORY_DATE_FORMAT_PATTERN = "yyyy-MM-dd_hhmmss"
    private val directoryDateFormatter = DateTimeFormat
            .forPattern(DIRECTORY_DATE_FORMAT_PATTERN)
            .withLocale(indonesiaLocale)

    fun getCurrentDateForDirectory(): String {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.MINUTE, 59)
        calendar.set(Calendar.SECOND, 59)
        calendar.set(Calendar.HOUR_OF_DAY, 23)
        val dateTime = calendar.timeInMillis
        return directoryDateFormatter.print(dateTime)
    }

    fun getDateFromIso8601TimeDefault(iso8601time: String): DateTime =
            dateTimeFormatterDefault.parseDateTime(iso8601time)

    fun parseDate(iso8601time: String): String {
        val time = getDateFromIso8601TimeDefault(iso8601time).millis
        return localDateFormatter.print(time)
    }

    fun getDateFormat(time: Long): String {
        return dateTimeFormatterDefault.print(time)
    }

    fun getLongTime(iso8601time: String): Long {
        val time = getDateFromIso8601TimeDefault(iso8601time).millis
        return time
    }

    fun parseDateTime(iso8601time: String): String {
        val time = getDateFromIso8601TimeDefault(iso8601time).millis
        return fullDateTimeFormatter.print(time)
    }

    fun parseDateFirst(iso8601time: String): String {
        val time = getDateFromIso8601TimeDefault(iso8601time).millis
        return DateFirstFormatter.print(time)
    }


    fun getFullFormattedDate(iso8601time: String, context: Context, isTimeAgo: Boolean): String {
        if (iso8601time.isEmpty()) {
            return ""
        }
        if (isTimeAgo) {
            if (isCurrentMinute(iso8601time)) {
                val time = getDateFromIso8601Time(iso8601time).millis
                val formattedTime = shortTransactionTimeFormatter.print(time)
                return context.getString(R.string.transaction_details_hour_only_format, formattedTime)
            }
            if (isCurrentHour(iso8601time)) {
                val time = getDateFromIso8601Time(iso8601time).millis
                val formattedTime = shortTransactionTimeFormatter.print(time)
                return context.getString(R.string.transaction_details_hour_only_format, formattedTime)
            }
            if (isToday(iso8601time)) {
                val time = getDateFromIso8601Time(iso8601time).millis
                val formattedTime = shortTransactionTimeFormatter.print(time)
                return context.getString(R.string.transaction_details_today_format, formattedTime)
            }
            if (isYesterday(iso8601time)) {
                val time = getDateFromIso8601Time(iso8601time).millis
                val formattedTime = shortTransactionTimeFormatter.print(time)
                return context.getString(R.string.transaction_details_yesterday_format, formattedTime)
            }
        }
        return dateTimeFullFormatter.print(getDateFromIso8601Time(iso8601time))
    }

    fun getFormattedDate(iso8601time: String): String {
        if (iso8601time.isEmpty()) {
            return ""
        }
        return dateFormatter.print(getDateFromIso8601Time(iso8601time))
    }

    fun getFormattedDate(iso8601time: DateTime): String = dateFormatter.print(iso8601time)

    fun getFormattedTransactionDateHorizontal(iso8601time: String): String {
        if (iso8601time.isEmpty()) {
            return ""
        }
        return transactionDateHorizontalFormatter.print(getDateFromIso8601Time(iso8601time))
    }

    fun getFormattedTransactionDateHorizontalDefaultTime(iso8601time: String): String {
        if (iso8601time.isEmpty()) {
            return ""
        }
        return transactionDateHorizontalFormatter.print(getDateFromIso8601TimeDefault(iso8601time))
    }

    fun getFormattedFullDefaultTime(iso8601time: String): String {
        if (iso8601time.isEmpty()) {
            return ""
        }
        return fullDateTimeFormatter.print(getDateFromIso8601TimeDefault(iso8601time))
    }

    fun getFormattedTimeline(iso8601time: String): String {
        if (iso8601time.isEmpty()) {
            return ""
        }
        return timelineFormatter.print(getDateFromIso8601Time(iso8601time))
    }

    fun getDateFromIso8601Time(iso8601time: String): DateTime =
            dateTimeFormatter.parseDateTime(iso8601time)

    fun getCurrentDateInIso8601String(): String = dateTimeFormatter.print(DateTime())

    fun isToday(dateIso8601: String): Boolean {
        if (dateIso8601.isBlank()) {
            return false
        }
        return getDateFromIso8601Time(dateIso8601).toLocalDate() == LocalDate()
    }

    fun isCurrentHour(dateIso8601: String): Boolean {
        if (dateIso8601.isBlank()) {
            return false
        }
        val currentTime = LocalDateTime()
        val time = getDateFromIso8601Time(dateIso8601)
        if (time.toLocalDate() == LocalDate()) {
            if (time.hourOfDay == currentTime.hourOfDay) {
                return true
            }
        }
        return false
    }

    fun isCurrentMinute(dateIso8601: String): Boolean {
        if (dateIso8601.isBlank()) {
            return false
        }
        val currentTime = LocalDateTime()
        val time = getDateFromIso8601Time(dateIso8601)
        return time.minuteOfHour == currentTime.minuteOfHour
    }

    fun isCurrentSecond(dateIso8601: String): Boolean {
        if (dateIso8601.isBlank()) {
            return false
        }
        val currentTime = LocalDateTime()
        val time = getDateFromIso8601Time(dateIso8601)
        return time.secondOfMinute == currentTime.secondOfMinute
    }

    fun isYesterday(dateIso8601: String): Boolean {
        if (dateIso8601.isBlank()) {
            return false
        }
        return getDateFromIso8601Time(dateIso8601).toLocalDate() == LocalDate.now().minusDays(1)
    }

    fun getShortDate(dateIso8601: String): String =
            shortDateFormatter.print(getDateFromIso8601Time(dateIso8601))

    fun getCurrentShortDate(): String = shortDateFormatter.print(DateTime())

    fun getIso8601String(dateTime: DateTime): String = dateTimeFormatter.print(dateTime)

    fun getTransactionTime(context: Context,
                           dateIso8601: String): String {

        if (dateIso8601.isBlank()) {
            return ""
        }

        if (isCurrentHour(dateIso8601)) {
            val time = getDateFromIso8601Time(dateIso8601).millis
            val formattedTime = shortTransactionTimeFormatter.print(time)
            return context.getString(R.string.transaction_details_hour_only_format, formattedTime)
        }

        if (isToday(dateIso8601)) {
            val time = getDateFromIso8601Time(dateIso8601).millis
            val formattedTime = shortTransactionTimeFormatter.print(time)
            return context.getString(R.string.transaction_details_today_format, formattedTime)
        }

        if (isYesterday(dateIso8601)) {
            val time = getDateFromIso8601Time(dateIso8601).millis
            val formattedTime = shortTransactionTimeFormatter.print(time)
            return context.getString(R.string.transaction_details_yesterday_format, formattedTime)
        }

        return transactionDateMonthHorizontalFormatter.print(getDateFromIso8601Time(dateIso8601))
    }

    fun getCustomerDetailsFormattedDate(dateIso8601: String): String {
        if (dateIso8601.isBlank()) {
            return ""
        }
        val time = getDateFromIso8601Time(dateIso8601).millis
        return shortTransactionDateFormatter.print(time)
    }

    fun getFullDateTimeFormattedDate(dateIso8601: String): String {
        if (dateIso8601.isEmpty()) {
            return ""
        }
        val time = getDateFromIso8601Time(dateIso8601).millis
        return fullDateTimeFormatter.print(time)
    }

    fun getNullableTransactionTime(context: Context?,
                                   dateIso8601: String?): String? {
        if (dateIso8601?.isBlank() == true) {
            return ""
        }
        if (isToday(dateIso8601 ?: "")) {
            val time = getDateFromIso8601Time(dateIso8601 ?: "").millis
            val formattedTime = shortTransactionTimeFormatter.print(time)
            return context?.getString(R.string.transaction_details_today_format, formattedTime)
        }
        if (isYesterday(dateIso8601 ?: "")) {
            val time = getDateFromIso8601Time(dateIso8601 ?: "").millis
            val formattedTime = shortTransactionTimeFormatter.print(time)
            return context?.getString(R.string.transaction_details_yesterday_format, formattedTime)
        }
        return transactionDateHorizontalFormatter.print(getDateFromIso8601Time(dateIso8601 ?: ""))
    }

    /**
     * Get formatted 24 hours time using standard millis timestamp.
     */
    fun getFormattedTime(timestamp: Long): String = shortTransactionTimeFormatter.print(timestamp)

    fun isFirstCalendarMoreThanSecond(first: Calendar, second: Calendar): Boolean = when {
        first.get(Calendar.YEAR) < second.get(Calendar.YEAR) -> false
        first.get(Calendar.YEAR) == second.get(Calendar.YEAR) -> when {
            first.get(Calendar.MONTH) < second.get(Calendar.MONTH) -> false
            first.get(Calendar.MONTH) == second.get(Calendar.MONTH) -> {
                first.get(Calendar.DAY_OF_MONTH) >= second.get(Calendar.DAY_OF_MONTH)
            }
            else -> true
        }
        else -> true
    }

    fun getYesterdayAsIso8601(): String {
        val calendar = Calendar.getInstance()
        val todayDate = calendar.get(Calendar.DATE)
        calendar.set(Calendar.DATE, todayDate - 1)
        calendar.set(Calendar.MINUTE, 59)
        calendar.set(Calendar.SECOND, 59)
        calendar.set(Calendar.HOUR_OF_DAY, 23)

        val dateTime = calendar.timeInMillis
        return dateTimeFormatter.print(dateTime)
    }

    fun gatDateTodayTimeDefault(): String {
        val current = LocalDateTime.now()
        return dateTimeFormatterDefault.print(current)
    }

    fun getTodayAsIso8601(): String {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.MINUTE, 59)
        calendar.set(Calendar.SECOND, 59)
        calendar.set(Calendar.HOUR_OF_DAY, 23)

        val dateTime = calendar.timeInMillis
        return dateTimeFormatter.print(dateTime)
    }

    fun getTodayDateTime(): String {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.MINUTE, 59)
        calendar.set(Calendar.SECOND, 59)
        calendar.set(Calendar.HOUR_OF_DAY, 23)

        val dateTime = calendar.timeInMillis
        return historyDateFormat.print(dateTime)
    }

    fun getDateTimeAfterToday(): String {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.MINUTE, 59)
        calendar.set(Calendar.SECOND, 59)
        calendar.set(Calendar.HOUR_OF_DAY, 23)
        calendar.add(Calendar.DATE, 1)

        val dateTime = calendar.timeInMillis
        return historyDateFormat.print(dateTime)
    }

    fun getTodayTime(): String {
        val calendar = Calendar.getInstance()
        val dateTime = calendar.timeInMillis
        return historyTimeFormat.print(dateTime)
    }

    fun getTodayDate(): String {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.MINUTE, 59)
        calendar.set(Calendar.SECOND, 59)
        calendar.set(Calendar.HOUR_OF_DAY, 23)

        val dateTime = calendar.timeInMillis
        return historyDateFormat.print(dateTime)
    }

    fun getTodayMonthYear(): String {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.MINUTE, 59)
        calendar.set(Calendar.SECOND, 59)
        calendar.set(Calendar.HOUR_OF_DAY, 23)

        val dateTime = calendar.timeInMillis
        return historyMonthYearFormat.print(dateTime)
    }

    fun getTodayDateForReport(): String {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.MINUTE, 59)
        calendar.set(Calendar.SECOND, 59)
        calendar.set(Calendar.HOUR_OF_DAY, 23)

        val dateTime = calendar.timeInMillis
        return historyTodayDateFormat.print(dateTime)
    }

    fun getCurrentTimeStamp(): String {
        return (System.currentTimeMillis() / 1000).toString()
    }

    @SuppressLint("SimpleDateFormat")
    fun convertStringDateToDate(date: Calendar): String {
        return historyDateFormat.print(date.timeInMillis)
    }

    @SuppressLint("SimpleDateFormat")
    fun convertStringDateToDateDefaultFormat(date: Calendar): String {
        return localDateFormatter.print(date.timeInMillis)
    }
}

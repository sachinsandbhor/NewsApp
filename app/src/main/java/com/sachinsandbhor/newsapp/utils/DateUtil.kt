package com.sachinsandbhor.newsapp.utils

import java.text.SimpleDateFormat
import java.util.*

class DateUtil {

    companion object {
        val DATE_PATTERN = "yyyy-MM-dd"
        val IST_TIME_ZONE = "IST"
        val LEADING_ZERO_TEMPLATE = "%02d"

        fun formatDate(originalDate: String): String {

            try {
                val sdf = SimpleDateFormat(DATE_PATTERN, Locale.ENGLISH)
                val timeZone = TimeZone.getTimeZone(IST_TIME_ZONE)
                sdf.timeZone = timeZone
                val date = sdf.parse(originalDate)
                val calendar = Calendar.getInstance()
                calendar.time = date
                val month = calendar.getDisplayName(
                    Calendar.MONTH,
                    Calendar.SHORT,
                    Locale.getDefault())
                val dayOFMonth = calendar.get(Calendar.DAY_OF_MONTH)
                val hours = calendar.get(Calendar.HOUR_OF_DAY)
                val minutes = calendar.get(Calendar.MINUTE)

                return StringBuilder()
                    .append(dayOFMonth.toString())
                    .append(" ")
                    .append(month)
                    .append(" ")
                    .append(String.format(Locale.getDefault(), LEADING_ZERO_TEMPLATE, hours))
                    .append(":")
                    .append(String.format(Locale.getDefault(), LEADING_ZERO_TEMPLATE, minutes))
                    .toString()
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return originalDate
        }
    }

}
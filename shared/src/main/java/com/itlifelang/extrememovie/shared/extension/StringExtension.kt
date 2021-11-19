package com.itlifelang.extrememovie.shared.extension

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Get the search past time that is represent by [String].
 * @param delimiter The delimiter separates time components of [String] such as [-]
 */
fun String.getHistorySearchTime(delimiter: String = "-"): String {
    val day = split(delimiter).getOrNull(2)?.toIntOrNull() ?: return ""
    val currentDay = SimpleDateFormat("dd", Locale.getDefault())
        .format(Date())
        .toIntOrNull() ?: return ""
    return when {
        currentDay - day == 0 -> "Today"
        currentDay - day == 1 -> "Yesterday"
        currentDay - day <= 7 -> "This week"
        else -> "For a long time"
    }
}
package com.rpn.newskmpapp.domain.use_case

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes

class FormatDateTimeUseCase {

    fun execute(dateString: String): String {
        return parseAndFormatDate(dateString)
    }

    private fun parseAndFormatDate(dateString: String): String {
        val currentTime = Clock.System.now()
        val timeZone = TimeZone.currentSystemDefault()

        val parsedDate = try {
            Instant.parse(dateString)
        } catch (e: Exception) {
            try {
                val dateTime = LocalDateTime.parse(dateString.replace(" ", "T"))
                dateTime.toInstant(timeZone)
            } catch (e: Exception) {
                return "Invalid date format"
            }
        }

        val duration = currentTime - parsedDate

        return when {
            duration < 1.minutes -> "Just now"
            duration < 1.hours -> "${duration.inWholeMinutes} minutes ago"
            duration < 24.hours -> "${duration.inWholeHours} hours ago"
            duration < 48.hours -> "Yesterday at " + parsedDate.toLocalDateTime(timeZone)
                .toString("HH:mm")

            else -> parsedDate.toLocalDateTime(timeZone).toString("dd MMM yyyy at HH:mm")
        }
    }

    private fun LocalDateTime.toString(pattern: String): String {
        return pattern
            .replace("dd", this.dayOfMonth.toString().padStart(2, '0'))
            .replace(
                "MMM",
                this.month.name.lowercase().replaceFirstChar { it.uppercase() }.substring(0, 3)
            )
            .replace("yyyy", this.year.toString().substring(0, 4))
            .replace("HH", this.hour.toString().padStart(2, '0'))
            .replace("mm", this.minute.toString().padStart(2, '0'))
        //.replace("a", if (this.hour < 12) "AM" else "PM")
    }
}
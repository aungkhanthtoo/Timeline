package dev.htoo.timeline.ui.time

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.isActive

/**
 * A composable that recomposes it's [content] with a continuously updating timestamp based on
 * a given [TimelineSchedule].
 *
 * This is useful when you want to update UI content on a periodic basis—such as clocks,
 * countdowns, or live dashboards—without managing the timing logic manually.
 *
 * @param schedule The [TimelineSchedule] that determines the interval and logic for ticking.
 * @param content A content composable that receives the current time (in milliseconds).
 *
 * ```
 * Timeline(schedule = rememberPeriodicSchedule(Duration.ofSeconds(1))) { timeMillis ->
 *     Text("Now: ${Instant.ofEpochMilli(timeMillis)}")
 * }
 * ```
 */
@Composable
fun Timeline(
    schedule: TimelineSchedule = TimelineSchedule.Companion.animation,
    content: @Composable (Long) -> Unit
) {
    val time = remember {
        mutableLongStateOf(System.currentTimeMillis())
    }
    LaunchedEffect(schedule) {
        while (isActive) {
            schedule.tick(time)
        }
    }
    content(time.longValue)
}

@Preview(showBackground = true)
@Composable
private fun Preview() {


}
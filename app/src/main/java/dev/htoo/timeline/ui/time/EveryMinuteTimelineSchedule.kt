package dev.htoo.timeline.ui.time

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import kotlinx.coroutines.android.awaitFrame

/**
 * A schedule for updating a timeline at the start of every minute.
 */
@Composable
fun rememberEveryMinuteSchedule(): TimelineSchedule {
    return remember {
        EveryMinuteTimelineSchedule()
    }
}

private class EveryMinuteTimelineSchedule: TimelineSchedule {

    private var prevMinute = -1L

    override suspend fun tick(state: MutableState<Long>) {
        val currentMinute = (System.currentTimeMillis() / (60 * 1000)) % 60
        if (currentMinute != prevMinute) {
            prevMinute = currentMinute
            state.value = System.currentTimeMillis()
        }
        awaitFrame()
    }
}
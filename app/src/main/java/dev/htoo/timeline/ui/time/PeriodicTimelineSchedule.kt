package dev.htoo.timeline.ui.time

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import kotlinx.coroutines.delay
import kotlin.time.Duration

/**
 * A schedule for updating a timeline at regular intervals.
 */
@Composable
fun rememberPeriodicSchedule(duration: Duration, paused: Boolean = false): TimelineSchedule {
    val pausedState = rememberUpdatedState(paused)
    return remember(duration) {
        PeriodicTimelineSchedule(duration, pausedState)
    }
}

private data class PeriodicTimelineSchedule(
    val duration: Duration,
    private val pausedState: State<Boolean>
) : TimelineSchedule {

    override suspend fun tick(state: MutableState<Long>) {
        if (!pausedState.value) {
            state.value = System.currentTimeMillis()
        }
        delay(duration)
    }
}


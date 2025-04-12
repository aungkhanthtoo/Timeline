package dev.htoo.timeline.ui.time

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import kotlinx.coroutines.android.awaitFrame

@Stable
interface TimelineSchedule {

    suspend fun tick(state: MutableState<Long>)

    companion object {

        /**
         * A schedule for updating a timeline at every animation frame.
         */
        val animation = object : TimelineSchedule {
            override suspend fun tick(state: MutableState<Long>) {
                state.value = System.currentTimeMillis()
                awaitFrame()
            }
        }
    }
}
package dev.htoo.timeline

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.htoo.timeline.ui.time.Timeline
import dev.htoo.timeline.ui.time.rememberEveryMinuteSchedule
import dev.htoo.timeline.ui.time.rememberPeriodicSchedule
import dev.htoo.timeline.ui.theme.TimelineTheme
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import kotlin.time.Duration.Companion.seconds

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TimelineTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Sample(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Sample(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Timeline {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Every frame", style = MaterialTheme.typography.labelMedium)
                Text("$it")
            }
        }
        Spacer(Modifier.height(36.dp))
        Timeline(rememberPeriodicSchedule(3.seconds)) {
            val time = DateTimeFormatter.ofPattern("HH:mm:ss.nn").format(
                Instant.ofEpochMilli(it)
                    .atZone(ZoneId.systemDefault())
                    .toLocalTime()
            )
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Every 3 seconds:", style = MaterialTheme.typography.labelMedium)
                Text(time)
            }
        }
        Spacer(Modifier.height(36.dp))
        Timeline(rememberEveryMinuteSchedule()) {
            val time = DateTimeFormatter.ofPattern("HH:mm:ss").format(
                Instant.ofEpochMilli(it)
                    .atZone(ZoneId.systemDefault())
                    .toLocalTime()
            )
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Every minute:", style = MaterialTheme.typography.labelMedium)
                Text(time)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TimelineTheme {
        Sample(Modifier.fillMaxSize())
    }
}
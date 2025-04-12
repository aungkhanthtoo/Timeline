# 🕒 Timeline Demo (Inspired by SwiftUI's TimelineView)

This Android project demonstrates a **Jetpack Compose timeline rendering**, inspired by **SwiftUI's `TimelineView` API**. 
It leverages custom `Timeline` composable to render UI updates on a regular interval — from frame-level updates to periodic intervals like seconds or minutes.

## ✨ Features

- `Timeline`: A composable that updates it's content at a defined interval.
- Supports:
    - **Every frame rendering**
    - **Fixed-interval updates** (e.g., every 3 seconds)
    - **Every minute updates**

```kotlin
Timeline {
    Text("Every frame", style = MaterialTheme.typography.labelMedium)
    Text("$it")
}
```

```kotlin
Timeline(rememberPeriodicSchedule(3.seconds)) {
  Text("Every 3 seconds:", style = MaterialTheme.typography.labelMedium)
  Text(
    DateTimeFormatter.ofPattern("HH:mm:ss.nn").format(
        Instant.ofEpochMilli(it)
            .atZone(ZoneId.systemDefault())
            .toLocalTime()
    )
  )
}
```

```kotlin
Timeline(rememberEveryMinuteSchedule()) {
  Text("Every minute:", style = MaterialTheme.typography.labelMedium)
  Text(
    DateTimeFormatter.ofPattern("HH:mm:ss").format(
        Instant.ofEpochMilli(it)
            .atZone(ZoneId.systemDefault())
            .toLocalTime()
    )
  )
}
```

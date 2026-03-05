package com.example.projectpart1

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity

class Screen2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screen2)

        // Inside onCreate in Screen2Activity.kt
        val gestureName = intent.getStringExtra("GESTURE_NAME") ?: "LightOn"
        val videoView = findViewById<VideoView>(R.id.videoViewExpert)

        val gestureMap = mapOf(
            "LightOn" to "light_on",
            "LightOff" to "light_off",
            "FanOn" to "fan_on",
            "FanOff" to "fan_off",
            "FanUp" to "increase_fan_speed",
            "FanDown" to "decrease_fan_speed",
            "SetThermo" to "set_thermo",
            "Num0" to "h_0", "Num1" to "h_1", "Num2" to "h_2", "Num3" to "h_3", "Num4" to "h_4",
            "Num5" to "h_5", "Num6" to "h_6", "Num7" to "h_7", "Num8" to "h_8", "Num9" to "h_9"
        )

        val rawFileName = gestureMap[gestureName] ?: gestureName.lowercase().replace(" ", "_")
        val resId = resources.getIdentifier(rawFileName, "raw", packageName)

        if (resId != 0) {
            videoView.setVideoURI(Uri.parse("android.resource://$packageName/$resId"))

            // This helps smooth out the start of the video
            videoView.setOnPreparedListener { mediaPlayer ->
                mediaPlayer.isLooping = false // Ensure it plays only once
                videoView.start()
            }
        }

        findViewById<Button>(R.id.btnReplay).setOnClickListener {
            videoView.start()
        }

        findViewById<Button>(R.id.btnPractice).setOnClickListener {
            val intent = Intent(this, Screen3Activity::class.java)
            intent.putExtra("GESTURE_NAME", rawFileName)
            startActivity(intent)
        }
    }
}
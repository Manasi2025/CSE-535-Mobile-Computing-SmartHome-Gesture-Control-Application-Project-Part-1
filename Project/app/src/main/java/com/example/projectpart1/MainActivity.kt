package com.example.projectpart1

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val gestures = arrayOf("Select Gesture","light_on", "fan_on", "fan_off", "light_off", "increase_fan_speed", "decrease_fan_speed", "set_thermo","h_0", "h_1", "h_2", "h_3", "h_4", "h_5", "h_6", "h_7", "h_8", "h_9" )
        val gestures = arrayOf(
            "Select Gesture",
            "LightOn", "LightOff", "FanOn", "FanOff", "FanUp", "FanDown", "SetThermo",
            "Num0", "Num1", "Num2", "Num3", "Num4", "Num5", "Num6", "Num7", "Num8", "Num9"
        )

        val spinner = findViewById<Spinner>(R.id.spinnerGestures)
        val button = findViewById<Button>(R.id.btnGoToScreen2)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, gestures)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        button.setOnClickListener {
            val selectedGesture = spinner.selectedItem.toString()

            if (selectedGesture == "Select Gesture") {
                Toast.makeText(this, "Please select a gesture first", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, Screen2Activity::class.java)
                intent.putExtra("GESTURE_NAME", selectedGesture)
                startActivity(intent)
            }
        }
    }
}
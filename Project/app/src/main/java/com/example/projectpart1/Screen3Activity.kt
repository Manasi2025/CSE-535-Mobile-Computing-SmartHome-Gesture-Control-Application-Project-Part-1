package com.example.projectpart1

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.video.*
import androidx.camera.view.PreviewView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.IOException

class Screen3Activity : AppCompatActivity() {
    private var recording: Recording? = null
    private var videoCapture: VideoCapture<Recorder>? = null
    private lateinit var lastRecordedFile: File
    private var isRecording = false
    private val displayNames = mapOf(
        "light_on" to "LightOn",
        "light_off" to "LightOff",
        "fan_on" to "FanOn",
        "fan_off" to "FanOff",
        "increase_fan_speed" to "FanUp",
        "decrease_fan_speed" to "FanDown",
        "set_thermo" to "SetThermo",
        "h_0" to "Num0", "h_1" to "Num1", "h_2" to "Num2", "h_3" to "Num3", "h_4" to "Num4",
        "h_5" to "Num5", "h_6" to "Num6", "h_7" to "Num7", "h_8" to "Num8", "h_9" to "Num9"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screen3)

        val rawGesture = intent.getStringExtra("GESTURE_NAME") ?: "h_0"
        val gesture = displayNames[rawGesture] ?: rawGesture

        val btnRecord = findViewById<Button>(R.id.btnRecord)
        val btnUpload = findViewById<Button>(R.id.btnUpload)

        if (allPermissionsGranted()) {
            startCamera()
        } else {
            ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS, 10)
        }

        btnRecord.setOnClickListener {
            if (!isRecording) {
                startRecording(gesture)
                btnRecord.text = "STOP RECORDING"
                isRecording = true
            } else {
                stopRecordingManually()
            }
        }

        btnUpload.setOnClickListener {
            if (::lastRecordedFile.isInitialized && lastRecordedFile.exists()) {
                uploadVideo(lastRecordedFile)
            } else {
                Toast.makeText(this, "Please record a video first", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)
        cameraProviderFuture.addListener({
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
            val preview = Preview.Builder().build().also {
                it.setSurfaceProvider(findViewById<PreviewView>(R.id.viewFinder).surfaceProvider)
            }
            val recorder = Recorder.Builder().setQualitySelector(QualitySelector.from(Quality.LOWEST)).build()
            videoCapture = VideoCapture.withOutput(recorder)

            val cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA

            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(this, cameraSelector, preview, videoCapture)
                Log.d("CameraX", "Binding Successful")
            } catch (e: Exception) {
                Log.e("CameraX", "Binding Failed", e)
            }
        }, ContextCompat.getMainExecutor(this))
    }

    private fun startRecording(gesture: String) {
        val folder = externalCacheDir

        val practiceNum = (folder?.listFiles { _, name ->
            name.startsWith("${gesture}_PRACTICE_")
        }?.size ?: 0) + 1

        val fileName = "${gesture}_PRACTICE_${practiceNum}_Patil.mp4"

        val file = File(folder, fileName)
        lastRecordedFile = file

        val outputOptions = FileOutputOptions.Builder(file).build()
        recording = videoCapture?.output?.prepareRecording(this, outputOptions)
            ?.start(ContextCompat.getMainExecutor(this)) { }

        // Automatically stop after 5 seconds per project limits
        Handler(Looper.getMainLooper()).postDelayed({
            if (isRecording) {
                stopRecordingManually()
                Toast.makeText(this, "Video Limit Reached (5s).", Toast.LENGTH_SHORT).show()
            }
        }, 5000)
    }

    private fun stopRecordingManually() {
        recording?.stop()
        isRecording = false
        findViewById<Button>(R.id.btnRecord).text = "START RECORDING"
    }

    private fun uploadVideo(file: File) {
        val client = OkHttpClient()
        val requestBody = MultipartBody.Builder().setType(MultipartBody.FORM)
            .addFormDataPart("video", file.name, file.asRequestBody("video/mp4".toMediaTypeOrNull()))
            .build()

        val request = Request.Builder().url("http://192.168.1.93:5000/upload").post(requestBody).build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread { Toast.makeText(this@Screen3Activity, "Upload Failed", Toast.LENGTH_SHORT).show() }
            }
            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    runOnUiThread {
                        Toast.makeText(this@Screen3Activity, "Video saved Successfully!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@Screen3Activity, MainActivity::class.java))
                    }
                }
            }
        })
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
    }

    companion object {
        private val REQUIRED_PERMISSIONS = arrayOf(android.Manifest.permission.CAMERA, android.Manifest.permission.RECORD_AUDIO)
    }
}
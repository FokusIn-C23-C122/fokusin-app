package com.capstone.fokusin.ui.fragment

//import android.graphics.Camera
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.*
import android.widget.Button
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.capstone.fokusin.R
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class RecordFragment : Fragment(), SurfaceHolder.Callback {

    private lateinit var cameraExecutor: ExecutorService
    private lateinit var camera: Camera
    private lateinit var imageCapture: ImageCapture
    private lateinit var preview: SurfaceView
    private lateinit var btnStartRecording: Button

    private var isRecording = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_record, container, false)

        // Inisialisasi komponen UI
        preview = view.findViewById(R.id.preview)
        btnStartRecording = view.findViewById(R.id.btn_start_recording)

        // Mengatur aksi tombol Start Recording
        btnStartRecording.setOnClickListener {
            if (isRecording) {
                stopRecording()
            } else {
                startRecording()
            }
        }

        // Inisialisasi executor untuk penggunaan kamera
        cameraExecutor = Executors.newSingleThreadExecutor()

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        cameraExecutor.shutdown()
    }

    override fun onResume() {
        super.onResume()
        preview.holder.addCallback(this)
    }

    override fun onPause() {
        super.onPause()
        preview.holder.removeCallback(this)
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        startCamera(holder)
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
        // Ignored, the Camera does all the work for us
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
//        camera.close()
    }

    private fun startCamera(holder: SurfaceHolder) {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())
        cameraProviderFuture.addListener({
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            val preview = Preview.Builder().build().also { builder ->

            }

            val imageCapture = ImageCapture.Builder().build()

            val cameraSelector = CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                .build()

            try {
                cameraProvider.unbindAll()
                camera = cameraProvider.bindToLifecycle(viewLifecycleOwner, cameraSelector, preview, imageCapture)
            } catch (exc: Exception) {
                Log.e(TAG, "Error starting camera: ${exc.message}")
            }
        }, ContextCompat.getMainExecutor(requireContext()))
    }


    private fun startRecording() {
        isRecording = true
        btnStartRecording.text = "Stop Recording"
        capturePhotoPeriodically()
    }

    private fun stopRecording() {
        isRecording = false
        btnStartRecording.text = "Start Recording"
    }

    private fun capturePhotoPeriodically() {
        cameraExecutor.execute {
            while (isRecording) {
                try {
                    val imageCapture = imageCapture ?: return@execute
                    val outputFile = createOutputFile()
                    val outputOptions = ImageCapture.OutputFileOptions.Builder(outputFile).build()

                    imageCapture.takePicture(outputOptions, cameraExecutor, object : ImageCapture.OnImageSavedCallback {
                        override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                            // Foto berhasil disimpan, lakukan aksi yang diinginkan
                            Log.d(TAG, "Photo saved: ${outputFile.absolutePath}")
                        }

                        override fun onError(exc: ImageCaptureException) {
                            Log.e(TAG, "Error capturing photo: ${exc.message}")
                        }
                    })

                    Thread.sleep(60000) // Tunggu 1 menit
                } catch (e: InterruptedException) {
                    Log.e(TAG, "Capture photo interrupted: ${e.message}")
                    stopRecording()
                }
            }
        }
    }

    private fun createOutputFile(): File {
        val outputDir = requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val fileName = "IMG_${timeStamp}.jpg"
        return File(outputDir, fileName)
    }

    companion object {
        private const val TAG = "CameraFragment"
    }
}



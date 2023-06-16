package com.capstone.fokusin.ui.fragment

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import android.Manifest
import android.os.Handler
import android.widget.Button
import androidx.camera.core.ImageCaptureException
import com.capstone.fokusin.R
import com.capstone.fokusin.databinding.FragmentRecordBinding
import java.io.File

class RecordFragment : Fragment() {
    private lateinit var cameraExecutor: ExecutorService
    private var imageCapture: ImageCapture? = null
    private var cameraSelector: CameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA
    private var _binding: FragmentRecordBinding? = null
    private val binding get() = _binding!!
    private lateinit var handler: Handler
    private var isCapturingPhotos: Boolean = false
    private lateinit var captureRunnable: Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cameraExecutor = Executors.newSingleThreadExecutor()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecordBinding.inflate(inflater, container, false)
        val btn: Button = binding.btnDemo
        btn.setOnClickListener {
            lodFragment(DemoFragment())
        }
        val cameraPermission = Manifest.permission.CAMERA

        val requestCode = 100 // Anda dapat menggunakan kode permintaan yang sesuai

        // Memeriksa apakah izin kamera sudah pernah diberikan sebelumnya
        if (ContextCompat.checkSelfPermission(requireContext(), cameraPermission) != PackageManager.PERMISSION_GRANTED) {
            // Jika izin belum diberikan, meminta izin secara runtime
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(cameraPermission), requestCode)
        } else {
            // Izin kamera sudah diberikan, melanjutkan dengan pengaturan kamera
            startCamera()

        }


        return binding.root
    }
    private fun lodFragment(fragment: Fragment) {
        val trans = parentFragmentManager.beginTransaction()
        trans.replace(R.id.konten, fragment)
        trans.commit()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startCamera()
        binding.btnCamera.setOnClickListener {
            startPhotoCaptureSchedule()
            if (isCapturingPhotos) {
                stopPhotoCaptureSchedule()
                binding.btnCamera.text = "Stop Session"
            } else {
                startPhotoCaptureSchedule()
                binding.btnCamera.text = "Start Session"
            }
        }
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())

        cameraProviderFuture.addListener({
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
            val preview = Preview.Builder().build().also {
                it.setSurfaceProvider(binding.viewCams.surfaceProvider)
            }

            imageCapture = ImageCapture.Builder().build()

            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    this,
                    cameraSelector,
                    preview,
                    imageCapture
                )
            } catch (exc: Exception) {
                Toast.makeText(
                    requireContext(),
                    "Gagal memunculkan kamera.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }, ContextCompat.getMainExecutor(requireContext()))
    }

    private fun startPhotoCaptureSchedule() {
        if (isCapturingPhotos) {
            return
        }
//        binding.btnCamera.text = "stop bang"
        isCapturingPhotos = true

        captureRunnable = Runnable {
            // Capture photo
            capturePhoto()

            // Schedule the next capture after 1 minute
            handler.postDelayed(captureRunnable, 60000)
        }

        handler = Handler()
        handler.postDelayed(captureRunnable, 60000) // Initial capture after 1 minute
    }

    private fun stopPhotoCaptureSchedule() {
        if (!isCapturingPhotos) {
            return
        }
//        binding.btnCamera.text = "lagi bang"
        isCapturingPhotos = false
        handler.removeCallbacks(captureRunnable)
    }

    private fun capturePhoto() {
        val imageCapture = imageCapture ?: return

        val outputDirectory = File(requireContext().filesDir, "photos")
        if (!outputDirectory.exists()) {
            outputDirectory.mkdirs()
        }

        val outputFile = File(outputDirectory, "photo.jpg")

        val outputFileOptions = ImageCapture.OutputFileOptions.Builder(outputFile)
            .build()

        imageCapture.takePicture(
            outputFileOptions,
            cameraExecutor,
            object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    // Photo captured and saved successfully
                }

                override fun onError(exception: ImageCaptureException) {
                    // Error occurred while capturing or saving the photo
                }
            }
        )
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }
}



package id.my.nutrikita.ui.checkfoodnutrition

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import id.my.nutrikita.R
import id.my.nutrikita.databinding.ActivityCheckFoodNutritionBinding
import id.my.nutrikita.ui.camera.CameraActivity
import id.my.nutrikita.ui.camera.CameraActivity.Companion.EXTRA_URI_IMAGE
import id.my.nutrikita.ui.checkfoodresult.CheckFoodResultActivity
import id.my.nutrikita.ui.login.LoginActivity
import id.my.nutrikita.ui.main.MainActivity

class CheckFoodNutritionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCheckFoodNutritionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckFoodNutritionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setImage()
        binding.btnCamera.setOnClickListener {
            moveToCamera()
        }
        binding.btnGallery.setOnClickListener {
            startGallery()
        }
        binding.btnArrowBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }

    private fun startGallery() {
        launcherGallery.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    private val launcherGallery = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        if (uri != null) {
            val intent = Intent(this, CheckFoodNutritionActivity::class.java)
            intent.putExtra(EXTRA_URI_IMAGE, uri.toString())
            startActivity(intent)
        } else {
            Log.d("Photo Picker", "No media selected")
        }
    }

    private fun setImage() {
        val imgUri = intent.getStringExtra(EXTRA_URI_IMAGE)
        imgUri?.toUri().let {
            Log.d("Image URI", "showImage: $it")
            binding.ivFoodCheck.setImageURI(it)
        }
        if (imgUri != null ) {
            binding.btnGetResult.alpha = 1f
            binding.btnGetResult.setOnClickListener {
                startActivity(Intent(this, CheckFoodResultActivity::class.java))
            }
        } else {
            binding.btnGetResult.alpha = 0f
        }
    }

    private fun allPermissionsGranted() =
        ContextCompat.checkSelfPermission(
            this,
            REQUIRED_PERMISSION
        ) == PackageManager.PERMISSION_GRANTED

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                Toast.makeText(
                    this,
                    getString(R.string.permission_request_granted), Toast.LENGTH_LONG
                ).show()
                moveToCamera()
            } else {
                Toast.makeText(this, getString(R.string.rejected_permission), Toast.LENGTH_LONG)
                    .show()
            }
        }

    private fun moveToCamera() {
        if (!allPermissionsGranted()) {
            requestPermissionLauncher.launch(REQUIRED_PERMISSION)
        }
        if (allPermissionsGranted()) {
            val intent = Intent(this, CameraActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    companion object {
        private const val REQUIRED_PERMISSION = Manifest.permission.CAMERA
    }
}
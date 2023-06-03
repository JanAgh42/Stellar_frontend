package com.example.stellar

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentResolver
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.location.Geocoder
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.*
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModelProvider
import androidx.work.WorkManager
import com.example.stellar.enums.ActivityTypes
import com.example.stellar.functionalities.GeneralFunctionality
import com.example.stellar.interfaces.IGeneralFunctionality
import com.example.stellar.interfaces.IMandatoryOverrides
import com.example.stellar.api.models.Message
import com.example.stellar.data.DataPipeline
import com.example.stellar.data.LocalData
import com.example.stellar.data.viewmodels.AuthViewModel
import com.example.stellar.data.viewmodels.MessageViewModel
import com.example.stellar.enums.ApiCallTypes
import com.example.stellar.interfaces.IApiOverrides
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class CreatePostActivity(
    general: IGeneralFunctionality = GeneralFunctionality()
) : AppCompatActivity(),
    IMandatoryOverrides,
    IApiOverrides,
    IGeneralFunctionality by general {

    private lateinit var messageTypeTitle: TextView
    private lateinit var recipientName: TextView
    private lateinit var recipientMessage: TextView
    private lateinit var showLocation: TextView
    private lateinit var hideLocation: TextView
    private lateinit var removePhoto: TextView
    private lateinit var photoName: TextView

    private lateinit var openCamera: Button
    private lateinit var openGallery: Button

    private lateinit var messageContent: EditText

    private lateinit var notifyAll: CheckBox

    private lateinit var topBar: LinearLayout

    private lateinit var topGoBack: ImageButton
    private lateinit var topTitle: TextView
    private lateinit var topSettings: ImageButton
    private lateinit var topButton: TextView
    private lateinit var topMessage: ImageButton

    private lateinit var menuBar: LinearLayout
    private lateinit var photoInfo: LinearLayout

    private lateinit var lManager: FusedLocationProviderClient

    private lateinit var pictureAction: ActivityResultLauncher<Intent>
    private lateinit var data: Message
    private var replyToId: String? = null
    private var imageUri: Uri? = null
    private var uploadChecker = false

    private val LOCATION_REQUEST_CODE = 10101
    private val CAMERA_REQUEST_CODE = 10100
    private val GALLERY_REQUEST_CODE = 10111

    private val viewModel: MessageViewModel by lazy {
        ViewModelProvider(this)[MessageViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_post)

        this.loadViews()
        this.setDefaultValues()
    }

    override fun onStart() {
        super.onStart()

        this.attachListeners()
    }

    override fun onStop() {
        super.onStop()

        this.detachListeners()
    }

    override fun onPause() {
        super.onPause()
        overridePendingTransition(0,0)
    }

    override fun loadViews() {
        this.messageTypeTitle = findViewById(R.id.cpost_message_type)
        this.recipientName = findViewById(R.id.cpost_recipient_name)
        this.recipientMessage = findViewById(R.id.cpost_recipient_message)
        this.showLocation = findViewById(R.id.cpost_location)
        this.hideLocation = findViewById(R.id.cpost_hide_location)
        this.messageContent = findViewById(R.id.cpost_message)
        this.notifyAll = findViewById(R.id.cpost_notify_all)
        this.topBar = findViewById(R.id.cpost_top_bar)
        this.topGoBack = this.topBar.findViewById(R.id.top_back)
        this.topTitle = this.topBar.findViewById(R.id.top_title)
        this.topSettings = this.topBar.findViewById(R.id.top_settings)
        this.topButton = this.topBar.findViewById(R.id.top_button)
        this.topMessage = this.topBar.findViewById(R.id.top_add_message)
        this.menuBar = findViewById(R.id.cpost_menu_bar)
        this.openCamera = findViewById(R.id.cpost_camera)
        this.openGallery = findViewById(R.id.cpost_gallery)
        this.removePhoto = findViewById(R.id.cpost_remove_photo)
        this.photoName = findViewById(R.id.cpost_photo_name)
        this.photoInfo = findViewById(R.id.cpost_photo_info)
    }

    override fun setDefaultValues() {
        this.topTitle.visibility = View.INVISIBLE
        this.topSettings.visibility = View.INVISIBLE
        this.topButton.visibility = View.VISIBLE
        this.topMessage.visibility = View.INVISIBLE
        this.hideLocation.visibility = View.INVISIBLE

        this.removePhoto.visibility = View.INVISIBLE
        this.photoInfo.visibility = View.INVISIBLE

        this.topButton.setText(R.string.cpost_create_button)

        this.lManager = LocationServices.getFusedLocationProviderClient(this)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun attachListeners() {
        messageContent.movementMethod = ScrollingMovementMethod()

        messageContent.setOnTouchListener { v, _ ->
            v.parent.requestDisallowInterceptTouchEvent(true)
            false
        }

        this.menuBarListeners(this.menuBar, this, ActivityTypes.CREATE_POST_ACTIVITY)

        this.topGoBack.setOnClickListener {
            finish()
        }

        this.topButton.setOnClickListener {
            if (this.messageContent.text.toString().isBlank()) {
                Toast.makeText(this, R.string.cpost_empty_message, Toast.LENGTH_SHORT).show()
            }
            else {
                this.constructObject()
            }
        }

        this.showLocation.setOnClickListener {
            this.checkForPermissions(
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                LOCATION_REQUEST_CODE,
                applicationContext,
                this,
                this::getUserLocation)
        }

        this.hideLocation.setOnClickListener {
            this.showLocation.setText(R.string.cpost_clear_location)
            this.hideLocation.visibility = View.INVISIBLE
        }

        this.removePhoto.setOnClickListener {
            this.photoName.setText(R.string.cpost_photo_name)
            this.photoInfo.visibility = View.INVISIBLE
            this.removePhoto.visibility = View.INVISIBLE
            this.imageUri = null
        }

        this.openGallery.setOnClickListener {
            this.checkForPermissions(
                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                GALLERY_REQUEST_CODE,
                applicationContext,
                this,
                this::getImageFromGallery)
        }

        this.openCamera.setOnClickListener {
            this.checkForPermissions(
                android.Manifest.permission.CAMERA,
                CAMERA_REQUEST_CODE,
                applicationContext,
                this,
                this::getImageFromCamera)
        }

        this.pictureAction = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                if (it.data != null) {
                    this.imageUri = it.data?.data
                }
                this.handleReceivedImage()
            }
        }
    }

    override fun detachListeners() {
        this.topGoBack.setOnClickListener(null)
        this.topButton.setOnClickListener(null)
        this.showLocation.setOnClickListener(null)
        this.hideLocation.setOnClickListener(null)
        this.openCamera.setOnClickListener(null)
        this.openGallery.setOnClickListener(null)
    }

    override fun onRequestPermissionsResult (
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
            return
        }

        when (requestCode) {
            this.LOCATION_REQUEST_CODE -> {
                this.getUserLocation()
            }
            this.CAMERA_REQUEST_CODE -> {
                this.getImageFromCamera()
            }
            this.GALLERY_REQUEST_CODE -> {
                this.getImageFromGallery()
            }
        }
    }

    override fun constructObject() {
        val location = this.showLocation.text.toString()

        this.data = Message(
            id = null,
            userId = LocalData.identity,
            groupId = LocalData.identity,
            replyToId = this.replyToId,
            message = this.messageContent.text.toString(),
            location = if (location != getString(R.string.cpost_clear_location)
                && location != getString(R.string.cpost_no_location)) location else null,
            imageUrl = null
        )
        this.callApi(ApiCallTypes.POST)
    }

    override fun callApi(type: ApiCallTypes) {
        when (type) {
            ApiCallTypes.POST -> {
                if (this.uploadChecker) {
                    return
                }
                WorkManager.getInstance(applicationContext).cancelAllWorkByTag("postNewMessage")
                this.uploadChecker = true

                val filetype = if (this.imageUri != null)
                    this.photoName.text.toString().substringAfterLast('.')
                    else null

                DataPipeline.messages.add(this.data.copy())

                this.viewModel.createMessage(applicationContext, this.imageUri, filetype)

                LocalData.boolValues.observe(this) { message ->
                    if (message!!) {
                        Toast.makeText(this, "Post succesful", Toast.LENGTH_SHORT).show()
                    }
                    else {
                        Toast.makeText(this, "Post unsuccesful", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            else -> {}
        }
    }

    @SuppressLint("MissingPermission")
    @Suppress("DEPRECATION")
    private fun getUserLocation() {
        this.showLocation.setText(R.string.cpost_loading_loc)

        this.lManager.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, null).addOnSuccessListener {
            try {
                val coords = Geocoder(this, Locale.ENGLISH)
                    .getFromLocation(it.latitude, it.longitude, 1)!!

                if(coords[0].locality == null) {
                    this.showLocation.text = coords[0].countryName
                    Toast.makeText(this, getString(R.string.cpost_country_only), Toast.LENGTH_SHORT).show()
                }
                else {
                    this.showLocation.text = "${coords[0].locality}, ${coords[0].countryCode}"
                }

                this.hideLocation.visibility = View.VISIBLE
            } catch (e: Exception) {
                this.showLocation.setText(R.string.cpost_no_location)
                Toast.makeText(this, getString(R.string.cpost_no_location_t), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getImageFromGallery() {
        val toGallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        this.pictureAction.launch(toGallery)
    }

    private fun getImageFromCamera() {
        val toCamera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        val imageFile: File? = try {
            File(
                getExternalFilesDir(Environment.DIRECTORY_PICTURES),
                "camera_image.jpg"
            )
        } catch (ex: IOException) { null }

        imageFile?.also { image ->
            val uri: Uri = FileProvider.getUriForFile(this, "com.example.android.provider", image)
            this.imageUri = uri
            toCamera.putExtra(MediaStore.EXTRA_OUTPUT, uri)
        }

        toCamera.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
        this.pictureAction.launch(toCamera)
    }

    private fun handleReceivedImage() {
        this.photoInfo.visibility = View.VISIBLE
        this.removePhoto.visibility = View.VISIBLE

        this.photoName.text = this.imageUri?.lastPathSegment.toString().substringAfterLast('/')
    }
}
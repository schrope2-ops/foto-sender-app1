package com.example.fotosender

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import java.io.ByteArrayOutputStream

class MainActivity : AppCompatActivity() {

    private var selectedEmail: String = "adresse1@example.com"
    private var compressFactor: Int = 50 // percent

    private val takePicture = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            val bitmap = data?.extras?.get("data") as? Bitmap
            if (bitmap != null) {
                // compress
                val baos = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.JPEG, compressFactor, baos)
                val bytes = baos.toByteArray()

                // insert into MediaStore so we can get a URI to attach
                val path = MediaStore.Images.Media.insertImage(contentResolver, BitmapFactory.decodeByteArray(bytes,0,bytes.size), "Foto", "Foto automatisch gesendet")
                val uri = Uri.parse(path)

                // send email via ACTION_SEND (opens email client)
                val emailIntent = Intent(Intent.ACTION_SEND).apply {
                    type = "image/jpeg"
                    putExtra(Intent.EXTRA_EMAIL, arrayOf(selectedEmail))
                    putExtra(Intent.EXTRA_SUBJECT, "Automatisch gesendetes Foto")
                    putExtra(Intent.EXTRA_TEXT, "Hier ist das Foto (komprimiert: \$compressFactor%).")
                    putExtra(Intent.EXTRA_STREAM, uri)
                }
                startActivity(Intent.createChooser(emailIntent, "Sende Foto..."))
            } else {
                Toast.makeText(this, "Fehler: Kein Bild erhalten", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnCapture = findViewById<Button>(R.id.btnCapture)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroupCompression)
        val emailSpinner = findViewById<Spinner>(R.id.emailSpinner)

        val emails = listOf("adresse1@example.com", "adresse2@example.com")
        emailSpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, emails)
        emailSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: android.view.View?, position: Int, id: Long) {
                selectedEmail = emails[position]
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            compressFactor = if (checkedId == R.id.radio25) 25 else 50
        }

        btnCapture.setOnClickListener {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if (takePictureIntent.resolveActivity(packageManager) != null) {
                takePicture.launch(takePictureIntent)
            } else {
                Toast.makeText(this, "Keine Kamera-App gefunden", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

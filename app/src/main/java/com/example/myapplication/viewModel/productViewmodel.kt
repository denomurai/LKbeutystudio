package com.example.myapplication.viewModel

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.myapplication.model.EyebrowsModel
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.asRequestBody
import org.json.JSONObject
import java.io.File

class productViewmodel: ViewModel() {
    private val dbRef = FirebaseDatabase.getInstance().getReference("eyebrowServices")

    // 🧠 Save Eyebrow Service
    fun saveEyebrowService(
        context: Context,
        name: String,
        amount: String,
        description: String,
        imageUri: Uri?,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        if (name.isBlank() || amount.isBlank() || description.isBlank()) {
            onError("Please fill in all fields.")
            return
        }

        viewModelScope.launch {
            try {
                var imageUrl = ""

                if (imageUri != null) {
                    imageUrl = uploadImageToCloudinary(context, imageUri)
                }

                val id = dbRef.push().key ?: return@launch
                val eyebrow = EyebrowsModel(
                    id = id,
                    name = name,
                    amount = amount.toDoubleOrNull() ?: 0.0,
                    description = description,
                    imageUrl = imageUrl
                )

                dbRef.child(id).setValue(eyebrow)
                    .addOnSuccessListener { onSuccess() }
                    .addOnFailureListener { onError(it.localizedMessage ?: "Failed to save") }

            } catch (e: Exception) {
                onError(e.localizedMessage ?: "Error saving data")
            }
        }
    }

    // ☁️ Upload to Cloudinary and return URL
    private suspend fun uploadImageToCloudinary(context: Context, imageUri: Uri): String {
        return withContext(Dispatchers.IO) {
            val cloudName = "dzw65te0s" // 👈 change this
            val preset = "product_Img" // 👈 change this
            val url = "https://api.cloudinary.com/v1_1/$cloudName/image/upload"

            // Get actual file path from content Uri
            val inputStream = context.contentResolver.openInputStream(imageUri)
            val tempFile = File.createTempFile("upload", ".jpg", context.cacheDir)
            inputStream?.use { input ->
                tempFile.outputStream().use { output -> input.copyTo(output) }
            }

            val requestBody = MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", tempFile.name, tempFile.asRequestBody("image/*".toMediaType()))
                .addFormDataPart("upload_preset", preset)
                .build()

            val request = Request.Builder()
                .url(url)
                .post(requestBody)
                .build()

            val client = OkHttpClient()
            val response = client.newCall(request).execute()

            if (response.isSuccessful) {
                val json = JSONObject(response.body?.string() ?: "")
                json.getString("secure_url")
            } else {
                throw Exception("Cloudinary upload failed: ${response.message}")
            }
        }
    }
    // Addd here




// fetch data
private val _eyebrows = mutableStateListOf<EyebrowsModel>()
val eyebrows:List<EyebrowsModel> = _eyebrows
//private val dbRef = FirebaseDatabase.getInstance().getReference("eyebrowServices")


fun fetchEyebrows(context: Context){
    dbRef.get()
        .addOnSuccessListener { snapshot ->
            _eyebrows.clear()
            for (child in snapshot.children) {
                val eyebrow = child.getValue(EyebrowsModel::class.java)
                eyebrow?.let {
                    //EyebrowsModel is a data class with val id: String, so id is immutable.

                   // You cannot assign to it. Instead, create a copy:
                    // create a copy with the key as id
                    val eyebrowWithId = it.copy(id = child.key ?: "")
                    _eyebrows.add(eyebrowWithId)
                }
            }
        }
        .addOnFailureListener {
            Toast.makeText(context, "Failed to load services", Toast.LENGTH_LONG).show()
        }
}


// delete function

fun deleteEyebrows(id: String, context: Context) {
    val ref = FirebaseDatabase.getInstance().getReference("eyebrowServices").child(id)
    ref.removeValue()
        .addOnSuccessListener {
            //Remove from local list
            _eyebrows.removeAll { it.id == id }
            Toast.makeText(context, "Service deleted successfully", Toast.LENGTH_LONG).show()
        }
        .addOnFailureListener {
            Toast.makeText(context, "Service not deleted", Toast.LENGTH_LONG).show()
        }
}




    fun updateEyebrows( id: String,
                        imageUri: Uri?,
                        name: String,
                        amount: Double,
                        description: String,
                        context: Context){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val uploadedImageUrl = imageUri?.let { uploadImageToCloudinary(context, it) } ?: ""

                val updateMap = mapOf(
                    "name" to name,
                    "amount" to amount,
                    "description" to description,
                    "imageUrl" to uploadedImageUrl
                )

                val ref = dbRef.child(id)
                ref.updateChildren(updateMap).await()

                fetchEyebrows(context)

                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "Service updated successfully", Toast.LENGTH_LONG).show()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "Update failed: ${e.localizedMessage}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}






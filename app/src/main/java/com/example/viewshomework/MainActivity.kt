package com.example.viewshomework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import coil.Coil
import coil.request.ImageRequest
import com.example.viewshomework.databinding.ActivityMainBinding
import android.view.inputmethod.EditorInfo

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val editText = binding.editText

        editText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val imageUrl = editText.text.toString()
                loadImage(imageUrl)
                true
            } else {
                false
            }
        }
    }

    private fun loadImage(imageUrl: String) {
        val request = ImageRequest.Builder(this)
            .data(imageUrl)
            .target(
                onSuccess = { result ->
                    binding.imageView.setImageDrawable(result)
                },
                onError = { error ->
                    binding.imageView.setImageResource(R.drawable.placeholder)
                    val errorMessage = "Ошибка при загрузке изображения"
                    showToast(errorMessage)
                }
            )
            .build()

        Coil.imageLoader(this).enqueue(request)
    }

    private fun showToast(message: String) {
        Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
    }
}
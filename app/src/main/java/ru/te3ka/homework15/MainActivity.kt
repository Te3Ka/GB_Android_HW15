package ru.te3ka.homework15

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import ru.te3ka.homework15.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val wordViewModel: WordViewModel by viewModels {
        WordViewModelFactory((application as WordApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonAdd.setOnClickListener {
            val wordText = binding.editTextWord.text.toString()
            if (isValidWord(wordText)) {
                val word = Word(wordText, 1)
                wordViewModel.insertOrUpdate(word)
                binding.editTextWord.text.clear()
            } else {
                Toast.makeText(this, "Неверный формат", Toast.LENGTH_SHORT).show()
            }
        }

        binding.buttonClear.setOnClickListener {
            wordViewModel.deleteAll()
        }

        lifecycleScope.launch {
            wordViewModel.topWords.observe(this@MainActivity) { words ->
                binding.textViewTopWords.text = words.joinToString("\n") {
                    "${it.word}: ${it.count}"
                }
            }
        }

    }

    private fun isValidWord(word: String): Boolean {
        val regex = "^[a-zA-ZА-яЁё-]+$".toRegex()
        return word.matches(regex)
    }
}
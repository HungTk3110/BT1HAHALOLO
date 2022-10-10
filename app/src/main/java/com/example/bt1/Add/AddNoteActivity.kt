package com.example.bt1.Add

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.bt1.databinding.ActivityAddNoteBinding
import com.example.bt1.main.MainActivity
import com.example.note_application.model.Note
import com.example.note_application.viewmodel.NoteViewModel

class AddNoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddNoteBinding
    private lateinit var viewModel: AddNoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(AddNoteViewModel::class.java)


        binding.btnAdd.setOnClickListener {
            val note = Note(null, binding.edtTitle.text.toString(), binding.edtDes.text.toString())
            viewModel.addNote(note)
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}
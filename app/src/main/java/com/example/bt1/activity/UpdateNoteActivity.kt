package com.example.bt1.activity

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.example.bt1.R
import com.example.bt1.databinding.ActivityUpdateNoteBinding
import com.example.note_application.model.Note
import com.example.note_application.viewmodel.NoteViewModel

class UpdateNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateNoteBinding
    private lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUpdateNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)


        val note = intent.getSerializableExtra("update") as Note
        binding.edtTitle.setText(note.title)
        binding.edtDes.setText(note.description)

        binding.btnSave.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            with(builder) {
                setTitle("Save Note")
                setMessage("do you want to save?")
                setPositiveButton("Ok", DialogInterface.OnClickListener { builder, which ->
                    note.title = binding.edtTitle.text.toString()
                    note.description = binding.edtDes.text.toString()
                    noteViewModel.updateNote(note)
                    finish()
                })
                setNegativeButton("No", null)
                    .show()
            }

        }

        binding.btnDelete.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            with(builder) {
                setTitle("Delete Note")
                setMessage("do you want to delete?")
                setPositiveButton("Ok", DialogInterface.OnClickListener { builder, which ->
                    noteViewModel.deleteNote(note)
                    finish()
                })
                setNegativeButton("No", null)
                    .show()
            }

        }
    }}
package com.example.bt1.update

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.example.bt1.databinding.ActivityUpdateNoteBinding
import com.example.bt1.main.MainActivity
import com.example.note_application.model.Note

class UpdateNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateNoteBinding
    private lateinit var viewModel: UpdateNoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUpdateNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(UpdateNoteViewModel::class.java)


        val intent2 = Intent(this , MainActivity::class.java)
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
                    viewModel.updateNote(note)
                    startActivity(intent2)
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
                    viewModel.deleteNote(note)
                    startActivity(intent2)
                })
                setNegativeButton("No", null)
                    .show()
            }

        }
    }}
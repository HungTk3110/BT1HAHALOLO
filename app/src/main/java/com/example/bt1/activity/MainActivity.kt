package com.example.bt1.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bt1.R
import com.example.bt1.databinding.ActivityMainBinding
import com.example.note_application.adapter.NoteAdapter
import com.example.note_application.model.Note
import com.example.note_application.viewmodel.NoteViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var noteViewModel: NoteViewModel
    private var list: MutableList<Note> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        initControls()
        initEvents()
    }

    private fun initEvents() {
        binding.btnOpenAddActivity.setOnClickListener {
            val intent = Intent(this, AddNoteActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initControls() {
        val adapter = NoteAdapter(this@MainActivity, onItemClick)
        binding.rcvNote.setHasFixedSize(true)
        binding.rcvNote.layoutManager = LinearLayoutManager(this)
        binding.rcvNote.adapter = adapter
        noteViewModel.getAllNote().observe(this, Observer {
            list = it
            makeList()
            adapter.setNotes(list)
            Toast.makeText(this, lengthList(), Toast.LENGTH_SHORT).show()
        })
    }

    private val onItemClick: (Note) -> Unit = {
        val intent = Intent(this, UpdateNoteActivity::class.java)
        intent.putExtra("update", it)
        startActivity(intent)
    }

    private fun makeList() {
        list.apply {
            add(Note(null, "Trinh khac Hung1", " Hahalolo"))
            add(Note(null, "Trinh khac Hung2", " Hahalolo"))
            add(Note(null, "Trinh khac Hung3", " Hahalolo"))
            add(Note(null, "Trinh khac Hung4", " Hahalolo"))
            add(Note(null, "Trinh khac Hung5", " Hahalolo"))
        }
    }

    private fun lengthList(): String = list.let { it.size.toString() }
}
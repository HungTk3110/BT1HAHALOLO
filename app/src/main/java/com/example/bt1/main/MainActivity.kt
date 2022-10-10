package com.example.bt1.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bt1.Add.AddNoteActivity
import com.example.bt1.update.UpdateNoteActivity
import com.example.bt1.databinding.ActivityMainBinding
import com.example.note_application.adapter.NoteAdapter
import com.example.note_application.model.Note

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

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
        viewModel.getAllNotesObservers().observe(this , Observer {
            adapter.setNotes(it)
        })
    }

    private val onItemClick: (Note) -> Unit = {
        val intent = Intent(this, UpdateNoteActivity::class.java)
        intent.putExtra("update", it)
        startActivity(intent)
    }
}
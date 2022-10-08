package com.example.note_application.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.note_application.database.dao.NoteDataBase
import com.example.note_application.model.Note
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private var noteDataBase: NoteDataBase

    init {
        noteDataBase = NoteDataBase.getDatabase(application)
    }

    fun insertNote(note: Note) = viewModelScope.launch {
        noteDataBase.getNoteDao().insertNote(note)
    }

    fun updateNote(note: Note) = viewModelScope.launch {
        noteDataBase.getNoteDao().updateNote(note)
    }

    fun deleteNote(note: Note) = viewModelScope.launch {
        noteDataBase.getNoteDao().deleteNote(note)
    }

    fun getAllNote(): LiveData<MutableList<Note>> = noteDataBase.getNoteDao().getAllNote()

}
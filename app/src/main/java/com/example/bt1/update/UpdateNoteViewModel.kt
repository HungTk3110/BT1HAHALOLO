package com.example.bt1.update

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.note_application.database.dao.NoteDataBase
import com.example.note_application.model.Note
import kotlinx.coroutines.launch

class UpdateNoteViewModel(application: Application) : AndroidViewModel(application) {

    private var noteDataBase: NoteDataBase

    init {
        noteDataBase = NoteDataBase.getDatabase(application)
    }

    fun updateNote(note: Note){
        viewModelScope.launch {
            noteDataBase.getNoteDao().updateNote(note)
        }
    }

    fun deleteNote(note: Note){
        viewModelScope.launch {
            noteDataBase.getNoteDao().deleteNote(note)
        }
    }
}
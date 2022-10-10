package com.example.bt1.Add

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.note_application.database.dao.NoteDataBase
import com.example.note_application.model.Note
import kotlinx.coroutines.launch

class AddNoteViewModel(application: Application)  : AndroidViewModel(application){

    private var noteDataBase: NoteDataBase

    init {
        noteDataBase = NoteDataBase.getDatabase(application)
    }

    fun addNote(note: Note){
        viewModelScope.launch {
            noteDataBase.getNoteDao().insertNote(note)
        }
    }

}
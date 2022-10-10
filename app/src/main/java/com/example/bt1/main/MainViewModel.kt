package com.example.bt1.main

import android.app.Application
import androidx.core.graphics.Insets.add
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.note_application.database.dao.NoteDao
import com.example.note_application.database.dao.NoteDataBase
import com.example.note_application.model.Note
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private var allNotes: MutableLiveData<MutableList<Note>> = MutableLiveData()
    private var noteDataBase: NoteDataBase

    init {
        noteDataBase = NoteDataBase.getDatabase(application)
        getAllNote()
    }

    fun getAllNotesObservers(): MutableLiveData<MutableList<Note>> {
        return allNotes
    }

    private fun getAllNote() {
        viewModelScope.launch {
            val list = noteDataBase.getNoteDao().getAllNote()
            allNotes.postValue(list)
        }
    }
}
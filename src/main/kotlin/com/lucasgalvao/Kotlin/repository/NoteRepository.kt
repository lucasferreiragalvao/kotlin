package com.lucasgalvao.Kotlin.repository

import com.lucasgalvao.Kotlin.model.Note
import org.springframework.data.repository.CrudRepository

interface NoteRepository: CrudRepository<Note,Long> {
}
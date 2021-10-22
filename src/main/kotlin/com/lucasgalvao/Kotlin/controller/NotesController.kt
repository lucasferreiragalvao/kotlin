package com.lucasgalvao.Kotlin.controller

import com.lucasgalvao.Kotlin.model.Note
import com.lucasgalvao.Kotlin.repository.NoteRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/notes")
class NotesController ( val noteRepository: NoteRepository){

    @GetMapping
    fun findAll() = noteRepository.findAll()

    @PostMapping
    fun create(@RequestBody note: Note) = noteRepository.save(note)

    @GetMapping("/{id}")
    fun findById(@PathVariable(value = "id") id: Long) : ResponseEntity<Note> {
        return noteRepository.findById(id).map { note -> ResponseEntity.ok(note) }.orElse(ResponseEntity.notFound().build())
    }

    @PutMapping("/{id}")
    fun update(@PathVariable(value = "id") id: Long, @RequestBody note: Note): ResponseEntity<Note> {
        return noteRepository.findById(id).map { notes ->
            val updated : Note = notes.copy(title = note.title,body = note.body)
            ResponseEntity.ok().body(noteRepository.save(updated))
        }.orElse(ResponseEntity.notFound().build())
    }

    @DeleteMapping("/{id}")
    fun remove(@PathVariable(value = "id") id: Long) = noteRepository.deleteById(id)
}
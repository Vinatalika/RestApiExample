package com.example.restapitest.controller;

import com.example.restapitest.createnote.NoteRequest;
import com.example.restapitest.createnote.NoteResponse;
import com.example.restapitest.entity.Note;
import com.example.restapitest.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @GetMapping
    public List<NoteResponse> getAllNotes() {
        List<Note> notes = noteService.getAllNotes();
        return notes.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoteResponse> getNoteById(@PathVariable Long id) {
        Note note = noteService.getNoteById(id).orElseThrow(() -> new RuntimeException("Note not found"));
        return ResponseEntity.ok(convertToResponse(note));
    }

    @PostMapping
    public NoteResponse createNote(@RequestBody NoteRequest noteRequest) {
        Note note = convertToEntity(noteRequest);
        Note createdNote = noteService.createNote(note);
        return convertToResponse(createdNote);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NoteResponse> updateNote(@PathVariable Long id, @RequestBody NoteRequest noteRequest) {
        Note note = convertToEntity(noteRequest);
        Note updatedNote = noteService.updateNote(id, note);
        return ResponseEntity.ok(convertToResponse(updatedNote));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long id) {
        noteService.deleteNote(id);
        return ResponseEntity.noContent().build();
    }

    private Note convertToEntity(NoteRequest noteRequest) {
        return Note.builder()
                .title(noteRequest.getTitle())
                .content(noteRequest.getContent())
                .build();
    }

    private NoteResponse convertToResponse(Note note) {
        return NoteResponse.builder()
                .id(note.getId())
                .title(note.getTitle())
                .content(note.getContent())
                .createdAt(note.getCreatedAt())
                .build();
    }
}

//
//@RestController
//@RequestMapping("/notes")
//public class NoteController {
//
//    @Autowired
//    private NoteService noteService;
//
//    @GetMapping
//    public List<Note> getAllNotes() {
//        return noteService.getAllNotes();
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Note> getNoteById(@PathVariable Long id) {
//        Note note = noteService.getNoteById(id).orElseThrow(() -> new RuntimeException("Note not found"));
//        return ResponseEntity.ok(note);
//    }
//
//    @PostMapping
//    public Note createNote(Note note) {
//        return noteService.createNote(note);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Note> updateNote(@PathVariable Long id, @RequestBody Note noteDetails) {
//        Note updatedNote = noteService.updateNote(id, noteDetails);
//        return ResponseEntity.ok(updatedNote);
//    }
//
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteNote(Long id) {
//        noteService.deleteNote(id);
//        return ResponseEntity.noContent().build();
//    }
//}

package com.example.restapitest.service;

import com.example.restapitest.entity.Note;
import com.example.restapitest.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    public Optional<Note> getNoteById(Long id) {
        return noteRepository.findById(id);
    }

    public Note createNote(Note note) {
        return noteRepository.save(note);
    }

    public Note updateNote(Long id, Note noteDetails) {
        Note note = noteRepository.findById(id).orElseThrow(() -> new RuntimeException("Note not found"));

        note.setTitle(noteDetails.getTitle());
        note.setContent(noteDetails.getContent());

        return noteRepository.save(note);
    }

    public void deleteNote(Long id) {
        Note note = noteRepository.findById(id).orElseThrow(() -> new RuntimeException("Note not found"));
        noteRepository.delete(note);
    }
}

package com.ocr.axa.jlp.notes.service;

import com.ocr.axa.jlp.notes.DAO.NoteDAO;
import com.ocr.axa.jlp.notes.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {


    @Autowired
    protected NoteDAO noteDAO;

    public List<Note> getAllNotes() {
        return noteDAO.findAll();
    }

    public Note addNote(Note note) {
        return  noteDAO.save(note);
    }

    public List<Note> getNotes(Long patientId) {
        return noteDAO.findByPatientId(patientId);
    }

    public void updateNote(Note note) {
        noteDAO.save(note);
    }

    public void deleteNotes(String noteId) {
            noteDAO.deleteById(noteId);
    }

    public Optional<Note> findNote(String id) {
        return noteDAO.findById(id);
    }
}

package com.ocr.axa.jlp.notes.DAO;

import com.ocr.axa.jlp.notes.model.Note;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NoteDAO extends MongoRepository <Note, String> {
    List<Note> findByPatientId(Long id);
}

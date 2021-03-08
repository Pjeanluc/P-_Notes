package com.ocr.axa.jlp.notes.web.controller;

import com.ocr.axa.jlp.notes.model.Note;
import com.ocr.axa.jlp.notes.service.NoteService;
import com.ocr.axa.jlp.notes.web.exception.ControllerException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/patHistory")
public class NotesController {
    private static final Logger logger = LogManager.getLogger("generalController");

    @Autowired
    NoteService noteService;

    @RequestMapping("/all")
    public List<Note> getAllNotes(@RequestParam(required = false) String title) {
        return noteService.getAllNotes();
    }

    @RequestMapping("/")
    public List<Note> getAllNotes(@RequestParam(required = true) Long patientId) {
        return noteService.getNotes(patientId);
    }

    @RequestMapping("/id")
    public  Note getNotesById(@RequestParam(required = true) String id) {

        Optional<Note> noteFound = noteService.findNote(id);
        if (!noteFound.isPresent()){
            logger.error("Note not found");
            throw new ControllerException(("Note not found"));
        }
        else {
            logger.info(" get note by idNote : OK");
            return noteFound.get();
        }

    }

    @PostMapping("/add")
    public ResponseEntity<Note> addNote(@RequestBody Note note) {
        Note noteAdded = noteService.addNote(note);

        return new ResponseEntity(noteAdded, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Note> updateNote(@RequestBody Note note) {
        Optional<Note> noteFound = noteService.findNote(note.getId());
        if (!noteFound.isPresent()){
            logger.error("Note not found");
            throw new ControllerException(("Note not found"));
        }
        else {
            logger.info(" update note : OK");
            noteService.updateNote(note);
        }
        return new ResponseEntity(note, HttpStatus.OK);
    }

    @PostMapping("/delete/id")
    public Boolean deleteNotes(@RequestParam(required = true) String noteId) {

        Optional<Note> noteFound = noteService.findNote(noteId);
        if (!noteFound.isPresent()){
            logger.error("Note not found");
            throw new ControllerException(("Note not found"));
        }
        else {
            logger.info(" delete note : OK");
            noteService.deleteNotes(noteId);
        }

    return true;
    }
}

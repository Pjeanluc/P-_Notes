package com.ocr.axa.jlp.notes.TU;

import com.ocr.axa.jlp.notes.DAO.NoteDAO;
import com.ocr.axa.jlp.notes.model.Note;
import com.ocr.axa.jlp.notes.service.NoteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class NoteServiceTest {

    @Autowired
    NoteService noteService;

    @MockBean
    NoteDAO noteDAO;

    private Note note;

    String idConst = "1A";
    Long patientIdConst = 1l;
    String noteConst = "NOTE TEST";
    String idNotExistConst = "ZZ";
    Long patientIdNotExistConst = 9l;

    @BeforeEach
    public void setUpEach() {
        note = new Note();
        note.setId(idConst);
        note.setPatientId(patientIdConst);
        note.setNote(noteConst);
    }

    @Test
    public void getAllNotes(){
        //GIVEN
        List<Note> noteList = new ArrayList<>();
        noteList.add(note);
        Mockito.when(noteDAO.findAll()).thenReturn(noteList);

        //WHEN
        List<Note> noteResultList =  noteService.getAllNotes();
        //THEN
        assertThat(noteResultList).isNotEmpty();
    }

    @Test
    public void addNote(){
        //GIVEN
        Mockito.when(noteDAO.save(any(Note.class))).thenReturn(note);

        //WHEN
        Note noteResult =  noteService.addNote(note);

        //THEN
        assertThat(noteResult.getNote()).isEqualTo(note.getNote());
    }

    @Test
    public void findNoteByPatientId(){
        //GIVEN
        List<Note> noteList = new ArrayList<>();
        noteList.add(note);
        Mockito.when(noteDAO.findByPatientId(anyLong())).thenReturn(noteList);

        //WHEN
        List<Note> noteListResult =  noteService.getNotes(patientIdConst);
        //THEN
        assertThat(noteListResult).isNotNull();
        assertThat(noteListResult.size()).isEqualTo(noteList.size());
    }

    @Test
    public void findNoteByInexistingPatientId(){
        //GIVEN
        Mockito.when(noteDAO.findByPatientId(anyLong())).thenReturn(null);

        //WHEN
        List<Note> noteListResult =  noteService.getNotes(patientIdNotExistConst);
        //THEN
        assertThat(noteListResult).isNull();
    }

    @Test
    public void findNoteByExistingId(){
        //GIVEN
        Optional<Note> noteOptional = Optional.of(note);

        Mockito.when(noteDAO.findById(anyString())).thenReturn(noteOptional);

        //WHEN
        Optional<Note> noteResult =  noteService.findNote(idConst);

        //THEN
        assertThat(noteResult).isNotNull();
        assertThat(noteResult.get().getNote()).isEqualTo(noteConst);
    }
    @Test
    public void findNoteByInexistingId(){
        //GIVEN
        Mockito.when(noteDAO.findById(anyString())).thenReturn(null);

        //WHEN
        Optional<Note> noteResult =  noteService.findNote(idNotExistConst);

        //THEN
        assertThat(noteResult).isNull();
    }


}

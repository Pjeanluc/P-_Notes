package com.ocr.axa.jlp.notes.IT;

import com.ocr.axa.jlp.notes.model.Note;
import com.ocr.axa.jlp.notes.service.NoteService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WithMockUser(roles = "USER")
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class NoteControllerTestIT {

    private static final Logger logger = LogManager.getLogger(NoteControllerTestIT.class);
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private NoteService noteService;

    private Note note;
    private Note noteToDelete;
    private List<Note> noteList;// = new ArrayList<>();

    String idConst = "123ABC";
    String idConstToDelete = "todelete";
    Long patientIdConst = 1l;
    String noteConst = "NOTE TEST";
    LocalDate noteDate;


    @BeforeEach
    public void setup() throws Exception {
        mongoTemplate.dropCollection(Note.class);
        noteList = new ArrayList<>();
        noteDate = LocalDate.now();
        note = new Note(idConst,noteConst,patientIdConst,noteDate);
        noteService.addNote(note);
        noteToDelete = new Note(idConstToDelete,noteConst,patientIdConst,noteDate);
        noteService.addNote(noteToDelete);
           }
    @Test
    public void listNotes() throws Exception {
        //Given

        //WHEN THEN


        mockMvc.perform(get("/patHistory/all"))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void getNoteById() throws Exception {
        //Given
        List<Note> notes = noteService.getNotes(note.getPatientId());
        Note noteFound = notes.get(0);
        //WHEN THEN
        mockMvc.perform(get("/patHistory/id?id="+noteFound.getId()))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getNoteByIdPatient() throws Exception {
        //Given
        List<Note> notes = noteService.getNotes(note.getPatientId());
        Note noteFound = notes.get(0);
        //WHEN THEN
        mockMvc.perform(get("/patHistory/?patientId="+noteFound.getPatientId()))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void deleteNote() throws Exception {
        //Given

        //WHEN THEN
        mockMvc.perform(post("/patHistory/delete/id?noteId="+idConstToDelete))
                .andDo(print())
                .andExpect(status().isOk());
    }

}

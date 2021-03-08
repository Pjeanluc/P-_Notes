package com.ocr.axa.jlp.notes.TU;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ocr.axa.jlp.notes.model.Note;
import com.ocr.axa.jlp.notes.service.NoteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WithMockUser(roles = "USER")
@SpringBootTest
@AutoConfigureMockMvc
public class NoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NoteService noteService;

    private Note note;

    String idConst = "1A";
    Long patientIdConst = 1l;
    String noteConst = "NOTE TEST";


    @BeforeEach
    public void setUpEach() {
        note = new Note();
        note.setPatientId(patientIdConst);
        note.setNote(noteConst);
    }

    @Test
    public void getAllNote() throws Exception {
        //Given
        List<Note> noteList = new ArrayList<>();
        noteList.add(note);

        Mockito.when(noteService.getAllNotes()).thenReturn(noteList);

        //WHEN THEN
        mockMvc.perform(post("/patHistory/all")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void getNoteForOneId() throws Exception {
        //Given
        Optional<Note> noteOptional = Optional.of(note);
        Mockito.when(noteService.findNote(any(String.class))).thenReturn(noteOptional);

        //WHEN THEN
        mockMvc.perform(post("/patHistory/id?id=1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void addNote() throws Exception {
        //Given

        Mockito.when(noteService.addNote(any(Note.class))).thenReturn(note);

        //WHEN THEN
        mockMvc.perform(post("/patHistory/add")
                .content(asJsonString(note))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void UpdateNote() throws Exception {
        //Given
        note.setId(idConst);
        doNothing().when(noteService).updateNote(note);

        Optional<Note> noteOptional = Optional.of(note);
        Mockito.when(noteService.findNote(any(String.class))).thenReturn(noteOptional);

        //WHEN THEN
        mockMvc.perform(put("/patHistory/update")
                .content(asJsonString(note))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void DeleteNote() throws Exception {
        //Given
        note.setId(idConst);
        doNothing().when(noteService).deleteNotes(note.getId());

        Optional<Note> noteOptional = Optional.of(note);
        Mockito.when(noteService.findNote(any(String.class))).thenReturn(noteOptional);

        //WHEN THEN
        mockMvc.perform(post("/patHistory/delete/id?noteId=1A")
                .content(asJsonString(note))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

    }

    /*------------------------ utility ---------------------------------*/
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

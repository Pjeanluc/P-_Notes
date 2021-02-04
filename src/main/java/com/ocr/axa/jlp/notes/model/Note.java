package com.ocr.axa.jlp.notes.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "notes")
public class Note {
    @Id
    private String id;
    private String note;
    private Long patientId;

    private LocalDate dateNote;

    public Note() {
    }

    public Note(String note, Long patientId, LocalDate dateNote) {
        this.note = note;
        this.patientId = patientId;
        this.dateNote = dateNote;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public LocalDate getDateNote() {
        return dateNote;
    }

    public void setDateNote(LocalDate dateNote) {
        this.dateNote = dateNote;
    }
}

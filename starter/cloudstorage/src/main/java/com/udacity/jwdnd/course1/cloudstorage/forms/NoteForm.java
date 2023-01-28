package com.udacity.jwdnd.course1.cloudstorage.forms;

public class NoteForm {
    private String noteTitle;
    private String noteDescription;

    private Integer noteid;

    public NoteForm(String noteTitle, String noteDescription, Integer noteid) {
        this.noteTitle = noteTitle;
        this.noteDescription = noteDescription;
        this.noteid = noteid;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteDescription() {
        return noteDescription;
    }

    public void setNoteDescription(String noteDescription) {
        this.noteDescription = noteDescription;
    }

    public Integer getNoteid() {
        return noteid;
    }

    public void setNoteid(Integer noteid) {
        this.noteid = noteid;
    }

    public void clearForm() {
        this.noteTitle = "";
        this.noteDescription = "";
    }
}
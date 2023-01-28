package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.forms.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.mappers.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteService {
    private final NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }


    public List<Note> getNoteList(Integer userId) {
        return noteMapper.getNotesFromUser(userId);
    }

    public int addNote(Note note) {
       return noteMapper.addNote(note);
    }

    public int removeNote(Integer noteId) {
        return noteMapper.removeNote(noteId);
    }

    public int updateNote(Note note) {
        return noteMapper.updateNote(note.getNoteTitle(), note.getNoteDescription(), note.getNoteid());
    }
}

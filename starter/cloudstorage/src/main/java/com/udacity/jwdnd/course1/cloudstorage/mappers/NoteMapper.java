package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface NoteMapper {

    @Select("SELECT * FROM NOTES WHERE userid = #{userid}")
    List<Note> getNotesFromUser(Integer userId);

    @Select("SELECT * FROM NOTES WHERE noteid = #{noteid}")
    Note getNote(Integer noteid);

    @Insert("INSERT INTO NOTES (noteTitle, noteDescription, userid) VALUES(#{noteTitle}, #{noteDescription}, #{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "noteid")
    int addNote(Note note);

    @Update("UPDATE NOTES SET noteTitle = #{noteTitle}, noteDescription = #{noteDescription} WHERE noteid = #{noteid}")
    int updateNote(String noteTitle, String noteDescription, Integer noteid);

    @Delete("DELETE FROM NOTES WHERE noteid = #{noteid}")
    int removeNote (Integer noteid);

}

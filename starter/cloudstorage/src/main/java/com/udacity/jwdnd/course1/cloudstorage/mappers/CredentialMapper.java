package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {

    @Select("SELECT * FROM CREDENTIALS WHERE  userid = #{userid}")
    List<Credential> getCredentialsFromUser(Integer userid);

    @Select("SELECT * FROM CREDENTIALS WHERE  userid = #{userid} AND credentialid = #{credentialid}")
    Credential getCredential (Integer credentialid, Integer userid);

    @Insert("INSERT INTO CREDENTIALS (url, username, key, password, userid) VALUES(#{url}, #{username}, #{key}, #{password}, #{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialid")
    int addCredential(Credential credential);

    @Update("UPDATE CREDENTIALS SET url = #{url}, username = #{username}, password = #{password}, key = #{key} WHERE credentialid = #{credentialid}")
    int updateCredential(String url, String username, String password, String key, Integer credentialid);

    @Delete("DELETE FROM CREDENTIALS WHERE credentialid = #{credentialid}")
    int removeCredential (Integer credentialid);
}

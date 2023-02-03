package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialService {

    private final CredentialMapper credentialMapper;

    public CredentialService(CredentialMapper credentialMapper) {
        this.credentialMapper = credentialMapper;
    }

    public int addCredential(Credential credential) {
        return credentialMapper.addCredential(credential);
    }

    public List<Credential> getCredentials(Integer userId) {
        return credentialMapper.getCredentialsFromUser(userId);
    }

    public Credential getCredential(Integer credentialId, Integer userId) {
        return credentialMapper.getCredential(credentialId, userId);
    }

    public int updateCredential(Credential credential) {
        return credentialMapper.updateCredential(credential.getUrl(), credential.getUsername(), credential.getPassword(), credential.getKey() ,credential.getCredentialid());
    }

    public int deleteCredential(Integer credentialid) {
        return  credentialMapper.removeCredential(credentialid);
    }

}

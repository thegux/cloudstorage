package com.udacity.jwdnd.course1.cloudstorage.forms;

public class CredentialForm {
    private String url;
    private String password;
    private String username;
    private Integer credentialid;

    public CredentialForm(String url, String password, String username, Integer credentialid) {
        this.url = url;
        this.password = password;
        this.username = username;
        this.credentialid = credentialid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getCredentialid() {
        return credentialid;
    }

    public void setCredentialid(Integer credentialid) {
        this.credentialid = credentialid;
    }
}

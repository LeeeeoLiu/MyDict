package com.leeeeo.mydict.models;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "EASY_DICT_WORDS".
 */
public class EasyDictWords {

    private Long id;
    /** Not-null value. */
    private String name_lib;
    /** Not-null value. */
    private String name_words;
    private String phonetic;
    private String explains;
    private String verify_status;
    private Boolean islearn;
    private java.util.Date data_learn;
    private java.util.Date date_verify;

    public EasyDictWords() {
    }

    public EasyDictWords(Long id) {
        this.id = id;
    }

    public EasyDictWords(Long id, String name_lib, String name_words, String phonetic, String explains, String verify_status, Boolean islearn, java.util.Date data_learn, java.util.Date date_verify) {
        this.id = id;
        this.name_lib = name_lib;
        this.name_words = name_words;
        this.phonetic = phonetic;
        this.explains = explains;
        this.verify_status = verify_status;
        this.islearn = islearn;
        this.data_learn = data_learn;
        this.date_verify = date_verify;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /** Not-null value. */
    public String getName_lib() {
        return name_lib;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setName_lib(String name_lib) {
        this.name_lib = name_lib;
    }

    /** Not-null value. */
    public String getName_words() {
        return name_words;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setName_words(String name_words) {
        this.name_words = name_words;
    }

    public String getPhonetic() {
        return phonetic;
    }

    public void setPhonetic(String phonetic) {
        this.phonetic = phonetic;
    }

    public String getExplains() {
        return explains;
    }

    public void setExplains(String explains) {
        this.explains = explains;
    }

    public String getVerify_status() {
        return verify_status;
    }

    public void setVerify_status(String verify_status) {
        this.verify_status = verify_status;
    }

    public Boolean getIslearn() {
        return islearn;
    }

    public void setIslearn(Boolean islearn) {
        this.islearn = islearn;
    }

    public java.util.Date getData_learn() {
        return data_learn;
    }

    public void setData_learn(java.util.Date data_learn) {
        this.data_learn = data_learn;
    }

    public java.util.Date getDate_verify() {
        return date_verify;
    }

    public void setDate_verify(java.util.Date date_verify) {
        this.date_verify = date_verify;
    }

}

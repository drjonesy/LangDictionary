package com.rjones.languagedictionary;

public class Word {
    private int mId;
    private String mForeignLang;
    private String mNativeLang;
    private String mCategory;

    //Constructors

    public Word() {
    }

    public Word(String foreignLang, String nativeLang, String category) {
        mForeignLang = foreignLang;
        mNativeLang = nativeLang;
        mCategory = category;
    }

    public Word(int id, String foreignLang, String nativeLang, String category) {
        mId = id;
        mForeignLang = foreignLang;
        mNativeLang = nativeLang;
        mCategory = category;
    }

    //setters
    public void setId(int id) {
        mId = id;
    }

    public void setNativeLang(String nativeLang) {
        mNativeLang = nativeLang;
    }

    public void setForeignLang(String foreignLang) {
        mForeignLang = foreignLang;
    }

    public void setCategory(String category) {
        mCategory = category;
    }

    //getters
    public int getId() {
        return mId;
    }

    public String getNativeLang() {
        return mNativeLang;
    }

    public String getForeignLang() {
        return mForeignLang;
    }

    public String getCategory() {
        return mCategory;
    }
}
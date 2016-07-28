package com.rjones.languagedictionary;

public class Word {
    private int mId;
    private String mNativeLang;
    private String mForeignLang;
    private String mCategory;

    //Constructors

    public Word() {
    }

    public Word(String nativeLang, String foreignLang, String category) {
        mNativeLang = nativeLang;
        mForeignLang = foreignLang;
        mCategory = category;
    }

    public Word(int id, String nativeLang, String foreignLang, String category) {
        mId = id;
        mNativeLang = nativeLang;
        mForeignLang = foreignLang;
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
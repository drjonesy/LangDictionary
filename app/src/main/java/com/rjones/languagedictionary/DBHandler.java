package com.rjones.languagedictionary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    //Database Version
    private static final int DATABASE_VERSION = 1;
    //Database Name
    private static final String DATABASE_NAME = "lang.db";
    //Table Name
    private static final String TABLE_WORDS = "words";
    //Table Column Names
    private static final String WORD_ID = "id";
    private static final String WORD_FOREIGN = "foreign_lang";
    private static final String WORD_NATIVE = "native_lang";
    private static final String WORD_CATEGORY = "category";

    //Custom Constructor
    public DBHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create Table Query for Database
        String QUERY_CREATE_TABLE_WORDS = "CREATE TABLE " + TABLE_WORDS + " ("
                + WORD_ID           + " "   + "INTEGER PRIMARY KEY AUTOINCREMENT"   + ", "
                + WORD_FOREIGN      + " "   + "TEXT"                                + ", "
                + WORD_NATIVE       + " "   + "TEXT"                                + ", "
                + WORD_CATEGORY     + " "   + "TEXT"                                + ");";
        //Run Query
        db.execSQL(QUERY_CREATE_TABLE_WORDS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop older table if exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORDS);
        // Create table again
        onCreate(db);
    }

    //CRUD methods CREATE, READ, UPDATE, DELETE
    // Adding a new word
    public void addWord(Word word){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(WORD_FOREIGN, word.getForeignLang()); //Foreign Translation
        values.put(WORD_NATIVE, word.getNativeLang()); //Native Translation
        values.put(WORD_CATEGORY, word.getCategory()); //Word category
        //Inserting Row
        db.insert(TABLE_WORDS, null, values);
        db.close(); // Closing database connection
    }

    // Getting All Words
    public ArrayList<Word> getAllWords(){
        ArrayList<Word> wordList = new ArrayList<Word>();
        //Select All Query
        String selectAllQuery = "SELECT * FROM " + TABLE_WORDS;
        SQLiteDatabase db = this.getWritableDatabase();// why use getWritable ?
        Cursor cursor = db.rawQuery(selectAllQuery, null);
        //looping through all rows and adding to list
        if(cursor.moveToFirst()){
            do {
                Word word = new Word();
                word.setId(Integer.parseInt(cursor.getString(0)));
                word.setForeignLang(cursor.getString(1));
                word.setNativeLang(cursor.getString(2));
                word.setCategory(cursor.getString(3));
                //adding word to list
                wordList.add(word);
            }while(cursor.moveToNext());
        }
        //return word list
        return wordList;
    }

    // Getting All Words
    public ArrayList<Word> getAllWordsWhere(String setCategory){
        ArrayList<Word> wordList = new ArrayList<Word>();
        //Select All Query
        String selectAllQuery = "SELECT * FROM " + TABLE_WORDS + " WHERE " + WORD_CATEGORY +"=\""+ setCategory + "\";";
        SQLiteDatabase db = this.getWritableDatabase();// why use getWritable ?
        Cursor cursor = db.rawQuery(selectAllQuery, null);
        //looping through all rows and adding to list
        if(cursor.moveToFirst()){
            do {
                Word word = new Word();
                word.setId(Integer.parseInt(cursor.getString(0)));
                word.setForeignLang(cursor.getString(1));
                word.setNativeLang(cursor.getString(2));
                word.setCategory(cursor.getString(3));
                //adding word to list
                wordList.add(word);
            }while(cursor.moveToNext());
        }
        //return word list
        return wordList;
    }
}

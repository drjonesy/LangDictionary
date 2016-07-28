package com.rjones.languagedictionary;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {


    private static String mCategoryWord;
    private static int mCategoryColor;

    //setters
    public static void setCategoryWord(String categoryWord){mCategoryWord = categoryWord;}
    public static void setCategoryColor(int categoryColor){mCategoryColor = categoryColor;}
    //getters
    public static String getCategoryWord(){return mCategoryWord;}
    public static int getCategoryColor(){return mCategoryColor;}


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHandler db = new DBHandler(this);
        //Inserting Shop/Rows
        Log.d("Insert: ", "Inserting ..");
        readInFileToDatabase(db, R.raw.spanish,"^");

        //Display data that is imported to database // issue: only imports 13 rows
        Log.d("Reading: ", "Reading all words..");
        ArrayList<Word> words = db.getAllWords();
        // foreach loop
        for (Word word: words){
            String log =  " [id] "       + word.getId()
                    + " [foreign] "  + word.getForeignLang()
                    + " [native] "   + word.getNativeLang()
                    + " [category] " + word.getCategory();
            //Writing shops to log
            Log.d("Shop: ", log);
        }

        //=======click events
        //greetings
        TextView greetings = (TextView) findViewById(R.id.greetingsTextView);
        greetings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //set category word
                setCategoryWord("greeting");
                //set category color
                setCategoryColor(R.color.category_greeting);
                Intent intent = new Intent(MainActivity.this, CategoriesActivity.class);
                startActivity(intent);
            }
        });

        //questions
        TextView questions = (TextView) findViewById(R.id.questionsTextView);
        questions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //set category word
                setCategoryWord("question");
                //set category color
                setCategoryColor(R.color.category_question);
                Intent intent = new Intent(MainActivity.this, CategoriesActivity.class);
                startActivity(intent);
            }
        });

        //requests
        TextView requests = (TextView) findViewById(R.id.requestsTextView);
        requests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //set category word
                setCategoryWord("request");
                //set category color
                setCategoryColor(R.color.category_request);
                Intent intent = new Intent(MainActivity.this, CategoriesActivity.class);
                startActivity(intent);
            }
        });

        //declaration
        TextView declarations = (TextView) findViewById(R.id.declarationsTextView);
        declarations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //set category word
                setCategoryWord("declaration");
                //set category color
                setCategoryColor(R.color.category_declaration);
                Intent intent = new Intent(MainActivity.this, CategoriesActivity.class);
                startActivity(intent);
            }
        });

    }//END onCreate


    /**
     * Uses a class called DBHandler which extends SQLiteOpenHelper
     * To read a file from the raw directory. Replace rawFile with R.raw.your_file_name. Does not need file extension
     * The default value for splitChar is a comma ,
     * The default value for splitNumber is 2
     * @param db DBHandler
     * @param rawFile R.raw.file_name
     * @param splitChar character that splits string into array
     * */
    public void readInFileToDatabase(DBHandler db, int rawFile, String splitChar){
        Scanner inFile;
        String foreignLang;
        String nativeLang;
        String cat;
        try{
            inFile = new Scanner(getResources().openRawResource(rawFile));
            //continue while file has nextLine
            while(inFile.hasNextLine()){
                //read line
                String currentLine = inFile.nextLine();
                //split current line: look for splitChar String character
                //double escape required: just accept it
                String[] value = currentLine.split("\\" + splitChar);
                //assign values from value array to name and address
                foreignLang = value[0];
                nativeLang = value[1];
                cat = value[2];

                //add external file row to new database row
                db.addWord(new Word(foreignLang, nativeLang, cat));
            }

        } catch (Exception e){
            Log.v("Message: ",e.getMessage());
            Log.wtf("File not found: ", String.valueOf(rawFile));
        }

    } //END readInFileToDatabase


    //=============================== MENU OPTIONS AND SETUP

    // add the items to menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // adds functionality to the menu

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       /*   Handle action bar item clicks here.
            The action bar will automatically handle clicks on the Home/Up button,
            so long as you specify a parent activity in the AndroidManifest.xml
        */
        //Reference to Categories
        TextView greetingsTextView = (TextView) findViewById(R.id.greetingsTextView);
        TextView questionsTextView = (TextView) findViewById(R.id.questionsTextView);
        TextView requestsTextView = (TextView) findViewById(R.id.requestsTextView);
        TextView declarationsTextView = (TextView) findViewById(R.id.declarationsTextView);


        switch (item.getItemId()){
            //==== HIDE/SHOW Greetings Category
            case R.id.menu_greetings:
                if(item.isChecked()){
                    item.setChecked(false);
                    greetingsTextView.setVisibility(View.GONE);
                }else{
                    item.setChecked(true);
                    greetingsTextView.setVisibility(View.VISIBLE);
                }
                return true;

            //==== HIDE/SHOW Questions Category
            case R.id.menu_questions:
                if(item.isChecked()){
                    item.setChecked(false);
                    questionsTextView.setVisibility(View.GONE);
                }else{
                    item.setChecked(true);
                    questionsTextView.setVisibility(View.VISIBLE);
                }
                return true;

            //==== HIDE/SHOW Requests Category
            case R.id.menu_requests:
                if(item.isChecked()){
                    item.setChecked(false);
                    requestsTextView.setVisibility(View.GONE);
                }else{
                    item.setChecked(true);
                    requestsTextView.setVisibility(View.VISIBLE);
                }
                return true;

            //==== HIDE/SHOW Declarations Category
            case R.id.menu_declarations:
                if(item.isChecked()){
                    item.setChecked(false);
                    declarationsTextView.setVisibility(View.GONE);
                }else{
                    item.setChecked(true);
                    declarationsTextView.setVisibility(View.VISIBLE);
                }
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

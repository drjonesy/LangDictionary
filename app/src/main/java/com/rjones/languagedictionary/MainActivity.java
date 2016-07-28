package com.rjones.languagedictionary;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
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

    //Reference to Categories
    private TextView greetingsTextView;
    private TextView questionsTextView;
    private TextView requestsTextView;
    private TextView declarationsTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Reference to Categories
        greetingsTextView = (TextView) findViewById(R.id.greetingsTextView);
        questionsTextView = (TextView) findViewById(R.id.questionsTextView);
        requestsTextView = (TextView) findViewById(R.id.requestsTextView);
        declarationsTextView = (TextView) findViewById(R.id.declarationsTextView);

        DBHandler db = new DBHandler(this);
        //Inserting Shop/Rows
        Log.d("Insert: ", "Inserting ..");
        readInFileToDatabase(db, R.raw.spanish,"^");

        //Display data that is imported to database // issue: only imports 13 rows
//        Log.d("Reading: ", "Reading all words..");
//        ArrayList<Word> words = db.getAllWords();
//        // foreach loop
//        for (Word word: words){
//            String log =  " [id] "       + word.getId()
//                    + " [foreign] "  + word.getForeignLang()
//                    + " [native] "   + word.getNativeLang()
//                    + " [category] " + word.getCategory();
//            //Writing shops to log
//            Log.d("Shop: ", log);
//        }



        //-------------------------------------------------
            //build the new notification
            notification = new NotificationCompat.Builder(this);
            //remove notification once it has been visited
            notification.setAutoCancel(true);
        //-------------------------------------------------

        //=======click events
        //greetings
        greetingsTextView.setOnClickListener(new View.OnClickListener() {
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
        questionsTextView.setOnClickListener(new View.OnClickListener() {
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
        requestsTextView.setOnClickListener(new View.OnClickListener() {
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
        declarationsTextView.setOnClickListener(new View.OnClickListener() {
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

        switch (item.getItemId()) {
            //==== HIDE/SHOW Greetings Category
            case R.id.menu_greetings:
                if (item.isChecked()) {
                    item.setChecked(false);
                    greetingsTextView.setVisibility(View.GONE);
                    notifyCategoryHidden("Greetings disabled","Greetings Category","Re-enable category by checking menu option!");
                } else {
                    item.setChecked(true);
                    greetingsTextView.setVisibility(View.VISIBLE);
                }
                return true;

            //==== HIDE/SHOW Questions Category
            case R.id.menu_questions:
                if (item.isChecked()) {
                    item.setChecked(false);
                    notifyCategoryHidden("Questions disabled","Questions Category","Re-enable category by checking menu option!");
                    questionsTextView.setVisibility(View.GONE);
                } else {
                    item.setChecked(true);
                    questionsTextView.setVisibility(View.VISIBLE);
                }
                return true;

            //==== HIDE/SHOW Requests Category
            case R.id.menu_requests:
                if (item.isChecked()) {
                    item.setChecked(false);
                    notifyCategoryHidden("Requests disabled","Requests Category","Re-enable category by checking menu option!");
                    requestsTextView.setVisibility(View.GONE);
                } else {
                    item.setChecked(true);
                    requestsTextView.setVisibility(View.VISIBLE);
                }
                return true;

            //==== HIDE/SHOW Declarations Category
            case R.id.menu_declarations:
                if (item.isChecked()) {
                    item.setChecked(false);
                    notifyCategoryHidden("Declarations disabled","Declarations Category","Re-enable category by checking menu option!");
                    declarationsTextView.setVisibility(View.GONE);
                } else {
                    item.setChecked(true);
                    declarationsTextView.setVisibility(View.VISIBLE);
                }
                return true;

        }//END switch
        return super.onOptionsItemSelected(item);

    }//onOptionsItemSelected


    //=============================== NOTIFICATION VARIABLES AND METHODS
    // build the object that is going to be the notification itself
    NotificationCompat.Builder notification;
    private static final int UNIQUE_ID = 543701; // the notification has to be assigned


    public void notifyCategoryHidden(String notification_msg, String title, String description) {
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        notification.setSmallIcon(R.mipmap.ic_launcher);
        notification.setTicker(notification_msg);
        notification.setWhen(System.currentTimeMillis());
        notification.setContentTitle(title);
        notification.setContentText(description);
        notification.setSound(alarmSound);

        //send out the notification - Intent
        Intent i = new Intent(this, MainActivity.class);
        //give the device access to perform this intent by calling the PendingIntent
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,i, PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setContentIntent(pendingIntent);

        //send out the notification
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(UNIQUE_ID, notification.build());
    }
}

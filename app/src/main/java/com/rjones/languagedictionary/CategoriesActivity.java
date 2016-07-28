package com.rjones.languagedictionary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

public class CategoriesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        DBHandler db = new DBHandler(this);

        //Create new list with all words
        ArrayList<Word> words = db.getAllWordsWhere(MainActivity.getCategoryWord());

        //Looking for ArrayList, but to use methods needs List
        //fixed: DBHandler references ArrayList only now
        WordAdapter adapter = new WordAdapter(this, words, MainActivity.getCategoryColor());
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

    }
}

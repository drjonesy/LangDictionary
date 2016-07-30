/*
 * Copyright (c) 2016.
 * Created by Ryan Jones
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public
 * License for more details. You should have received a copy of the GNU General Public License along with this
 * program.  If not, see <http://www.gnu.org/licenses/>.
 */

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

        //set label
        String label = getIntent().getStringExtra("key").toString();
        setTitle(label);

    }
}

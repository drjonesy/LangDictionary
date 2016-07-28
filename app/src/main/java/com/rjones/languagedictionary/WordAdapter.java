package com.rjones.languagedictionary;


import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {

    private int mColorResourceId; // this will change the new intent color

    public WordAdapter(Activity context, ArrayList<Word> words, int colorResourceId){
        super(context, 0, words);
        mColorResourceId = colorResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        //Get the {@link WordAdapter} object located at this position in the list
        Word currentWord = getItem(position);

        //Find the TextView in the list_item.xml layout the ID foreign_text_view
        TextView foreignTextView = (TextView) listItemView.findViewById(R.id.foreign_text_view);
        //Get the Foreign translation from the current Word object and set this text on the foreignLang
        foreignTextView.setText(currentWord.getForeignLang());

        //Find the TextView in the list_item.xml layout the ID foreign_text_view
        TextView nativeTextView = (TextView) listItemView.findViewById(R.id.native_text_view);
        //Get the Foreign translation from the current Word object and set this text on the nativeLang
        nativeTextView.setText(currentWord.getNativeLang());

        //Set the theme color for the list item
        View textContainer = listItemView.findViewById(R.id.text_container);
        //Find the color that resource ID maps too
        int color = ContextCompat.getColor(getContext(), mColorResourceId);
        textContainer.setBackgroundColor(color);

        return listItemView;
    }
}

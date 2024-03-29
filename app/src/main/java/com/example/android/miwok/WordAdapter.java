package com.example.android.miwok;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {
    /** Resource ID for the background color for this list of words */
    private int mColorResourceId;

    public WordAdapter(Activity context, ArrayList<Word> words, int colorResourceId)

    {
        super(context, 0, words);
        mColorResourceId = colorResourceId;
    }

     @Override
     public View getView(int position, View convertView, ViewGroup parent) {
         // Check if the existing view is being reused, otherwise inflate the view
         View listItemView = convertView;
         if(listItemView == null) {
             listItemView = LayoutInflater.from(getContext()).inflate(
                     R.layout.list_item, parent, false);
         }

         // Get the {@link AndroidFlavor} object located at this position in the list
         Word currentWord = getItem(position);

         // Find the TextView in the list_item.xml layout with the ID version_name
         TextView miwokTextView = (TextView) listItemView.findViewById(R.id.MiwokWord);
         // Get the version name from the current AndroidFlavor object and
         // set this text on the name TextView
         miwokTextView.setText(currentWord.getMiwokTranslation());

         // Find the TextView in the list_item.xml layout with the ID version_number
         TextView defaultTextview = (TextView) listItemView.findViewById(R.id.DefaultWord);
         // Get the version number from the current AndroidFlavor object and
         // set this text on the number TextView
         defaultTextview.setText(currentWord.getDefaultTranslation());

         // Find the ImageView in the list_item.xml layout with the ID version_number
         ImageView defaultImageview = (ImageView) listItemView.findViewById(R.id.wordlogo);
         // Get the version number from the current image for this line and
         // set this text on the number ImageView
     //    defaultImageview.setImageResource(currentWord.getmImageResourceID());

         if (currentWord.hasImage()) {
             defaultImageview.setImageResource(currentWord.getmImageResourceID());
             defaultImageview.setVisibility((View.VISIBLE));
         }

         else {
             defaultImageview.setVisibility(View.GONE);
         }

         // Set the theme color for the list item
         View textContainer = listItemView.findViewById(R.id.text_container);
         // Find the color that the resource ID maps to
         int color = ContextCompat.getColor(getContext(), mColorResourceId);
         // Set the background color of the text container View
         textContainer.setBackgroundColor(color);

         // Return the whole list item layout (containing 2 TextViews and an ImageView)
         // so that it can be shown in the ListView
         return listItemView;
     }

 }



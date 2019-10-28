package com.example.android.miwok;

import android.app.Activity;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {

    /**
     * Handles playback of all the sound files
     */
    private MediaPlayer mMediaPlayer;

    /**
     * This listener gets triggered when the {@link MediaPlayer} has completed
     * playing the audio file.
     */
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family3);


        final ArrayList<Word> words = new ArrayList<>();
        words.add(new Word ("Mother", "Mutter", R.drawable.family_mother, R.raw.family_mother));
        words.add(new Word ("Father", "Vater",R.drawable.family_father, R.raw.family_father));
        words.add(new Word ("Daughter", "Tochter", R.drawable.family_daughter, R.raw.family_daughter));
        words.add(new Word ("Son", "Sohn", R.drawable.family_son, R.raw.family_son));
        words.add(new Word ("Aunt", "Tante", R.drawable.family_older_sister, R.raw.family_older_sister));
        words.add(new Word ("Uncle", "Onkel", R.drawable.family_older_brother, R.raw.family_older_brother));
        words.add(new Word ("Grandfather", "Grossvater", R.drawable.family_grandfather, R.raw.family_grandfather));
        words.add(new Word ("Grandmother", "Grossmutter",R.drawable.family_grandmother, R.raw.family_grandmother));
        words.add(new Word ("Granddaughter", "Enkelin", R.drawable.family_younger_sister, R.raw.family_younger_sister));
        words.add(new Word ("Grandson", "Enkel",R.drawable.family_younger_brother, R.raw.family_younger_brother));

        //  ArrayAdapter<Word> itemsAdapter = new ArrayAdapter<Word>(this, R.layout.list_item, words);

        WordAdapter adapter = new WordAdapter(this, words, R.color.category_family);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(adapter);


        // Set a click listener to play the audio when the list item is clicked on
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Release the media player if it currently exists because we are about to
                // play a different sound file
                releaseMediaPlayer();

                // Get the {@link Word} object at the given position the user clicked on
                Word word = words.get(position);

                // Create and setup the {@link MediaPlayer} for the audio resource associated
                // with the current word
                mMediaPlayer = MediaPlayer.create(FamilyActivity.this, word.getmAudioResourceID());

                // Start the audio file
                mMediaPlayer.start();

                // Setup a listener on the media player, so that we can stop and release the
                // media player once the sound has finished playing.
                mMediaPlayer.setOnCompletionListener(mCompletionListener);
            }
        });

    }
    @Override
    protected void onStop() {
        super.onStop();
        // When the activity is stopped, release the media player resources because we won't
        // be playing any more sounds.
        releaseMediaPlayer();
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
        }

    }

}

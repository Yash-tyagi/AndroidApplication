package com.example.multiscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer=null;

    private MediaPlayer.OnCompletionListener mCompletionListener = mediaPlayer -> {
        // Now that the sound file has finished playing, release the media player resources.
        releaseMediaPlayer();
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.words_list);

        ArrayList<WordClass> words = new ArrayList<>();


        words.add(new WordClass("red", "weṭeṭṭi",R.drawable.color_red,R.raw.color_red));
        words.add(new WordClass("green", "chokokki",R.drawable.color_green,R.raw.color_green));
        words.add(new WordClass("brown", "ṭakaakki",R.drawable.color_brown,R.raw.color_brown));
        words.add(new WordClass("gray", "ṭopoppi",R.drawable.color_gray,R.raw.color_gray));
        words.add(new WordClass("black", "kululli",R.drawable.color_black,R.raw.color_black));
        words.add(new WordClass("white", "kelelli",R.drawable.color_white,R.raw.color_white));
        words.add(new WordClass("dusty yellow", "ṭopiisә",R.drawable.color_dusty_yellow,R.raw.color_dusty_yellow));
        words.add(new WordClass("mustard yellow", "chiwiita",R.drawable.color_mustard_yellow,R.raw.color_mustard_yellow));


//        LinearLayout rootView= (LinearLayout) findViewById(R.id.rootView);
//
//        for(int i=0;i<words.size();i++){
//            TextView wordView =new TextView(this);
//            wordView.setText(words.get(i));
//            rootView.addView(wordView);
//        }

        WordAdapter itemsAdapter = new WordAdapter(this, words,R.color.grey);

        ListView listView = findViewById(R.id.list);

        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                WordClass word = words.get(position);
                mMediaPlayer = MediaPlayer.create(ColorsActivity.this,word.getAudioResourceId());
                mMediaPlayer.start();

                // Setup a listener on the media player, so that we can stop and release the
                // media player once the sound has finished playing.
                mMediaPlayer.setOnCompletionListener(mCompletionListener);
            }
        });
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
    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
}
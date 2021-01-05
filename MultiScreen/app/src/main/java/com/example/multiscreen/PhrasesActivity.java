package com.example.multiscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

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

        words.add(new WordClass("Where are you going?", "tinnә oyaase'nә",R.raw.phrase_where_are_you_going));
        words.add(new WordClass("What is your name?", "tinnә oyaase'nә",R.raw.phrase_what_is_your_name));
        words.add(new WordClass("My name is...", "oyaaset...",R.raw.phrase_my_name_is));
        words.add(new WordClass("How are you feeling?", "michәksәs?",R.raw.phrase_how_are_you_feeling));
        words.add(new WordClass("I’m feeling good.", "kuchi achit",R.raw.phrase_im_feeling_good));
        words.add(new WordClass("Are you coming?", "hәә’ әәnәm",R.raw.phrase_are_you_coming));
        words.add(new WordClass("Yes, I’m coming.", "hәә’ әәnәm",R.raw.phrase_yes_im_coming));
        words.add(new WordClass("I’m coming.", "әәnәm",R.raw.phrase_im_coming));
        words.add(new WordClass("Let’s go.", "yoowutis",R.raw.phrase_lets_go));
        words.add(new WordClass("Come here.", "әnni'nem",R.raw.phrase_come_here));


//        LinearLayout rootView= (LinearLayout) findViewById(R.id.rootView);
//
//        for(int i=0;i<words.size();i++){
//            TextView wordView =new TextView(this);
//            wordView.setText(words.get(i));
//            rootView.addView(wordView);
//        }

        WordAdapter itemsAdapter = new WordAdapter(this, words,R.color.purple_200);

        ListView listView = findViewById(R.id.list);

        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                WordClass word = words.get(position);
                mMediaPlayer = MediaPlayer.create(PhrasesActivity.this,word.getAudioResourceId());
                mMediaPlayer.start();
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
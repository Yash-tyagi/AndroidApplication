package com.example.multiscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer=null;
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
        setContentView(R.layout.words_list);

        ArrayList<WordClass> words = new ArrayList<>();

        words.add(new WordClass("father", "әpә",R.drawable.family_father,R.raw.family_father));
        words.add(new WordClass("mother", "әṭa",R.drawable.family_mother,R.raw.family_mother));
        words.add(new WordClass("son", "angsi",R.drawable.family_son,R.raw.family_son));
        words.add(new WordClass("daughter", "tune",R.drawable.family_daughter,R.raw.family_daughter));
        words.add(new WordClass("older brother", "taachi",R.drawable.family_older_brother,R.raw.family_older_brother));
        words.add(new WordClass("younger brother", "chalitti",R.drawable.family_younger_brother,R.raw.family_younger_brother));
        words.add(new WordClass("older sister", "teṭe",R.drawable.family_older_sister,R.raw.family_older_sister));
        words.add(new WordClass("younger sister", "kolliti",R.drawable.family_younger_sister,R.raw.family_younger_sister));
        words.add(new WordClass("grandmother", "ama",R.drawable.family_grandmother,R.raw.family_grandmother));
        words.add(new WordClass("grandfather", "na'paapa",R.drawable.family_grandfather,R.raw.family_grandfather));


//        LinearLayout rootView= (LinearLayout) findViewById(R.id.rootView);
//
//        for(int i=0;i<words.size();i++){
//            TextView wordView =new TextView(this);
//            wordView.setText(words.get(i));
//            rootView.addView(wordView);
//        }

        WordAdapter itemsAdapter = new WordAdapter(this, words,R.color.green);

        ListView listView = findViewById(R.id.list);

        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                WordClass word = words.get(position);
                mMediaPlayer = MediaPlayer.create(FamilyActivity.this,word.getAudioResourceId());
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
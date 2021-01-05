    package com.example.multiscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {
    private MediaPlayer mMediaPlayer = null;
    private AudioManager mAudioManager = null;

    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = focusChange -> {
        if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
            // The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a
            // short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
            // our app is allowed to continue playing sound but at a lower volume. We'll treat
            // both cases the same way because our app is playing short sound files.

            // Pause playback and reset player to the start of the file. That way, we can
            // play the word from the beginning when we resume playback.
            mMediaPlayer.pause();
            mMediaPlayer.seekTo(0);
        } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
            // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
            mMediaPlayer.start();
        } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
            // The AUDIOFOCUS_LOSS case means we've lost audio focus and
            // Stop playback and clean up resources
            releaseMediaPlayer();
        }
    };

    /**
     * This listener gets triggered when the {@link MediaPlayer} has completed
     * playing the audio file.
     */

    // Release Media Player on Completion
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // request audio service
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.words_list);
        ArrayList<WordClass> words = new ArrayList<>();

        words.add(new WordClass("one", "lutti", R.drawable.number_one, R.raw.number_one));
        words.add(new WordClass("two", "otikko", R.drawable.number_two, R.raw.number_two));
        words.add(new WordClass("three", "tolokosu", R.drawable.number_three, R.raw.number_three));
        words.add(new WordClass("four", "oyyisa", R.drawable.number_four, R.raw.number_four));
        words.add(new WordClass("five", "massokka", R.drawable.number_five, R.raw.number_five));
        words.add(new WordClass("six", "temmokka", R.drawable.number_six, R.raw.number_six));
        words.add(new WordClass("seven", "kenekaku", R.drawable.number_seven, R.raw.number_seven));
        words.add(new WordClass("eight", "kawinta", R.drawable.number_eight, R.raw.number_eight));
        words.add(new WordClass("nine", "wo'e", R.drawable.number_nine, R.raw.number_nine));
        words.add(new WordClass("ten", "na'aacha", R.drawable.number_ten, R.raw.number_ten));


//        LinearLayout rootView= (LinearLayout) findViewById(R.id.rootView);
//
//        for(int i=0;i<words.size();i++){
//            TextView wordView =new TextView(this);
//            wordView.setText(words.get(i));
//            rootView.addView(wordView);
//        }

        WordAdapter itemsAdapter = new WordAdapter(this, words, R.color.pink);

        ListView listView = findViewById(R.id.list);

        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                WordClass word = words.get(position);
                releaseMediaPlayer();
//                Log.v("NumbersAActivity","Current Word: "+word);

                // request audio focus to playback
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mMediaPlayer = MediaPlayer.create(NumbersActivity.this, word.getAudioResourceId());
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }
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
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
}
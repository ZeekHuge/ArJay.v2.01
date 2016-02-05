package com.wordpress.zubeentolani;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;


public class MusicPlayer implements MediaPlayer.OnPreparedListener{

    Context mContext;
    TextView mSmall_SongName;
    Button mSmall_PlayButton;
    Button mSmall_StopButton;
    MediaPlayer mMediaPlayer;

    public MusicPlayer(Context context){
        mContext = context;
    }

    public void getMediaPalyer(){
        if (mMediaPlayer == null) {
            mMediaPlayer = new MediaPlayer();
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mMediaPlayer.setOnPreparedListener(this);
        }
    }

    public void releaseMediaPlayer(){
        if (mMediaPlayer != null){
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    void playSong (Uri musicUri){

        /**********/

        if (mMediaPlayer == null) getMediaPalyer();

        /**********/

        try {
            mMediaPlayer.setDataSource(mContext,musicUri);
        } catch (IOException e) {
            e.printStackTrace();
        }

        mMediaPlayer.prepareAsync();
    }



    public View getSmallView (){

        View rootView = ((LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.layout_music_player_small,null,false);

        mSmall_SongName = (TextView) rootView.findViewById(R.id.music_player_small_song_name);
        mSmall_PlayButton = (Button) rootView.findViewById(R.id.music_player_small_play_button);
        mSmall_StopButton = (Button) rootView.findViewById(R.id.music_player_small_stop_button);

        return rootView;
    }


    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
    }
}



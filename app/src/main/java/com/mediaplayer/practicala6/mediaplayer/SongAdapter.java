package com.mediaplayer.practicala6.mediaplayer;

import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class SongAdapter extends BaseAdapter {

    private ArrayList<Song> songs;
    private LayoutInflater songInf;
    private MediaPlayer mp;
    private View oldSelection = null;

    public SongAdapter(Context c, ArrayList<Song> theSongs) {
        songs = theSongs;
        songInf = LayoutInflater.from(c);
        mp = new MediaPlayer();
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return songs.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int arg0, View arg1, ViewGroup arg2) {
        // TODO Auto-generated method stub
        //map to song layout
        LinearLayout songLay = (LinearLayout) songInf.inflate(R.layout.song_item, arg2, false);
        //get title and artist views
        ImageView albumLogo = (ImageView) songLay.findViewById(R.id.imageAlbum);
        TextView songView = (TextView) songLay.findViewById(R.id.song_title);
        TextView artistView = (TextView) songLay.findViewById(R.id.song_artist);
        //get song using position
        final Song currSong = songs.get(arg0);
        //get title and artist strings
        songView.setText(currSong.getTitle());
        artistView.setText(currSong.getArtist());

        songLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearSelection();
                oldSelection = v;

                v.setBackgroundColor(Color.CYAN);

                Log.e("Path", currSong.getPath());
                try {
                    //you can change the path, here path is external directory(e.g. sdcard) /Music/maine.mp3
                    if (mp.isPlaying()) {
                        mp.reset();
                    }
                    mp.setDataSource(currSong.getPath());
                    mp.prepare();
                    mp.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        //set position as tag
        songLay.setTag(arg0);
        return songLay;
    }

    public void clearSelection() {
        if (oldSelection != null) {
            oldSelection.setBackgroundColor(Color.WHITE);
        }
    }
}
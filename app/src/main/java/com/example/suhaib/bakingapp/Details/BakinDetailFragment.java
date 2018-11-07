package com.example.suhaib.bakingapp.Details;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.util.Util;
import com.example.suhaib.bakingapp.JsonFiles.Step;
import com.example.suhaib.bakingapp.R;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;

import java.util.ArrayList;


public class BakinDetailFragment extends Fragment {

    public static final String ARG_ITEM_ID = "step";
    private ArrayList<Step> steps;
    private int index;
    private SimpleExoPlayer mExoPlayer;
    private SimpleExoPlayerView mPlayerView;
    Context context;


    public BakinDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.bakin_detail, container, false);
        mPlayerView = (SimpleExoPlayerView)rootView.findViewById(R.id.playerView);
        //mPlayerView.setDefaultArtwork(BitmapFactory.decodeResource(getResources(),R.drawable.load));
        context = container.getContext();

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            steps = getArguments().getParcelableArrayList("step");
            index = getArguments().getInt("id");
            Log.d(steps.size()+"","Check step");
            Log.d(index+"","Check step");
            Uri uri = Uri.parse(steps.get(index).getVideoURL());
            Log.d(uri+"","Check step");
            initializePlayer(uri , context);
        }


        return rootView;
    }//end creatView

    public void initializePlayer(Uri mediaUri,Context context){
        if(mExoPlayer==null){
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            mExoPlayer = ExoPlayerFactory.newSimpleInstance(context,trackSelector,loadControl);
            mPlayerView.setPlayer(mExoPlayer);
            String userAgent = com.google.android.exoplayer2.util.Util.getUserAgent(context,"BakingApp");
            MediaSource mediaSource = new ExtractorMediaSource(mediaUri,new DefaultDataSourceFactory(context,userAgent),
                    new DefaultExtractorsFactory(),null,null);
            mExoPlayer.prepare(mediaSource);
            mExoPlayer.setPlayWhenReady(true);

        } // end if
    }//end initializer

    private void releasePlayer(){
        mExoPlayer.stop();
        mExoPlayer.release();
        mExoPlayer=null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releasePlayer();
    }
}

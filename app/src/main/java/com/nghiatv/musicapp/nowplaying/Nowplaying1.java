package com.nghiatv.musicapp.nowplaying;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nghiatv.musicapp.MusicPlayer;
import com.nghiatv.musicapp.MusicService;
import com.nghiatv.musicapp.R;
import com.nghiatv.musicapp.utils.MusicUtils;

import net.steamcrafted.materialiconlib.MaterialDrawableBuilder;

public class Nowplaying1 extends BaseNowplayingFragment {
    private static final String TAG = "Nowplaying1";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.fragment_music1, container, false);

        setMusicStateListener();
        setSongDetails(rootView);
        initGestures(rootView.findViewById(R.id.album_art));

        return rootView;
    }

    @Override
    public void updateShuffleState() {
        if (shuffle != null && getActivity() != null) {
            MaterialDrawableBuilder builder = MaterialDrawableBuilder.with(getActivity())
                    .setIcon(MaterialDrawableBuilder.IconValue.SHUFFLE)
                    .setSizeDp(30);

            builder.setColor(MusicUtils.getBlackWhiteColor(accentColor));

            shuffle.setImageDrawable(builder.build());
            shuffle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            MusicPlayer.setShuffleMode(MusicService.SHUFFLE_NORMAL);
                            MusicPlayer.next();
                            recyclerView.scrollToPosition(MusicPlayer.getQueuePosition());
                        }
                    }, 150);

                }
            });
        }
    }

}

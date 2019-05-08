package com.nghiatv.musicapp.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.cast.framework.CastSession;
import com.nghiatv.musicapp.MusicPlayer;
import com.nghiatv.musicapp.activities.BaseActivity;
import com.nghiatv.musicapp.models.Song;
import com.nghiatv.musicapp.utils.NavigationUtils;
import com.nghiatv.musicapp.utils.MusicUtils;

import java.util.List;

public class BaseSongAdapter<V extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<V> {

    @Override
    public V onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(V holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public class ItemHolder extends RecyclerView.ViewHolder {

        public ItemHolder(View view) {
            super(view);
        }

    }

    public void playAll(final Activity context, final long[] list, int position,
                        final long sourceId, final MusicUtils.IdType sourceType,
                        final boolean forceShuffle, final Song currentSong, boolean navigateNowPlaying) {

        if (context instanceof BaseActivity) {
            MusicPlayer.playAll(context, list, position, -1, MusicUtils.IdType.NA, false);
        } else {
            MusicPlayer.playAll(context, list, position, -1, MusicUtils.IdType.NA, false);
        }

        if (navigateNowPlaying) {
            NavigationUtils.navigateToNowplaying(context, true);
        }


    }

    public void removeSongAt(int i) {
    }

    public void updateDataSet(List<Song> arraylist) {
    }

}

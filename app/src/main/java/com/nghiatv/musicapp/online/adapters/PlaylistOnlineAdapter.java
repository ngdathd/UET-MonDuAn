package com.nghiatv.musicapp.online.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nghiatv.musicapp.R;
import com.nghiatv.musicapp.dto.PlaylistDto;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class PlaylistOnlineAdapter extends ArrayAdapter<PlaylistDto> {
    private static final String TAG = "PlaylistOnlineAdapter";

    public PlaylistOnlineAdapter(@NonNull Context context, int resource, @NonNull List<PlaylistDto> objects) {
        super(context, resource, objects);

    }

    public class ViewHolder {
        TextView txtTitlePlaylist;
        ImageView imgBackgroudPlaylist, imgIconPlaylist;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder mViewHolder = null;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_online_playlist, null);
            mViewHolder = new ViewHolder();
            mViewHolder.txtTitlePlaylist = convertView.findViewById(R.id.txtTitlePlaylist);
            mViewHolder.imgBackgroudPlaylist = convertView.findViewById(R.id.imgBckgroundPlaylist);
            mViewHolder.imgIconPlaylist = convertView.findViewById(R.id.imgIconPlaylist);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();

        }
        PlaylistDto playlistDto = getItem(position);

        ImageLoader.getInstance().displayImage(playlistDto.getHinhAnhPlaylist(), mViewHolder.imgBackgroudPlaylist,
                new DisplayImageOptions.Builder()
                        .cacheInMemory(true)
                        .showImageOnLoading(R.drawable.icon_splash_screen)
                        .resetViewBeforeLoading(true).build());
        ImageLoader.getInstance().displayImage(playlistDto.getIcon(), mViewHolder.imgIconPlaylist,
                new DisplayImageOptions.Builder()
                        .cacheInMemory(true)
                        .showImageOnLoading(R.drawable.icon_splash_screen)
                        .resetViewBeforeLoading(true).build());
        mViewHolder.txtTitlePlaylist.setText(playlistDto.getTen());

        return convertView;
    }
}

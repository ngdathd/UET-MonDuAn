package com.nghiatv.musicapp.online.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.nghiatv.musicapp.R;
import com.nghiatv.musicapp.online.activities.SongsListActivity;
import com.nghiatv.musicapp.dto.AlbumDto;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class AlbumOnlineAdapter extends RecyclerView.Adapter<AlbumOnlineAdapter.ViewHolder> {
    private static final String TAG = "AlbumOnlineAdapter";

    private Context mContext;
    private List<AlbumDto> albumList;

    public AlbumOnlineAdapter(Context mContext, List<AlbumDto> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View mView = inflater.inflate(R.layout.item_online_album, parent, false);
        ViewHolder mViewHolder = new ViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AlbumDto album = albumList.get(position);
        holder.txtTitleAlbum.setText(album.getTenAlbum());
        holder.txtArtistAlbum.setText(album.getTenCaSiAlbum());
        ImageLoader.getInstance().displayImage(album.getHinhAlbum(), holder.imgAlbum,
                new DisplayImageOptions.Builder()
                        .cacheInMemory(true)
                        .showImageOnLoading(R.drawable.icon_splash_screen)
                        .resetViewBeforeLoading(true).build());
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgAlbum;
        private TextView txtTitleAlbum, txtArtistAlbum;

        public ViewHolder(final View itemView) {
            super(itemView);
            imgAlbum = itemView.findViewById(R.id.imgAlbum);
            txtTitleAlbum = itemView.findViewById(R.id.txtTitleAlbum);
            txtArtistAlbum = itemView.findViewById(R.id.txtArtistAlbum);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, SongsListActivity.class);
                    intent.putExtra("album", albumList.get(getPosition()));
                    mContext.startActivity(intent);
                }
            });
        }
    }
}

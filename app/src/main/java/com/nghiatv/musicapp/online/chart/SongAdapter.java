package com.nghiatv.musicapp.online.chart;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.nghiatv.musicapp.R;

import java.util.List;

public class SongAdapter extends RecyclerView.Adapter {
    private List<Song> songs;

    public SongAdapter(List<Song> songs) {
        this.songs = songs;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_online_topsong, parent, false);
        return new SongHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        SongHolder songHolder = (SongHolder) holder;
        songHolder.bindView(position);
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    private class SongHolder extends RecyclerView.ViewHolder {
        private TextView tvRank;
        private ImageView img_luotthich;
        private ImageView img_baihatyeuthich;
        private TextView tv_tenBaiHat;
        private TextView tv_tenCaSi;

        public SongHolder(View itemView) {
            super(itemView);
            tvRank = itemView.findViewById(R.id.tvRank);
            img_luotthich = itemView.findViewById(R.id.img_luotthich);
            img_baihatyeuthich = itemView.findViewById(R.id.img_baihatyeuthich);
            tv_tenBaiHat = itemView.findViewById(R.id.tv_tenBaiHat);
            tv_tenCaSi = itemView.findViewById(R.id.tv_tenCaSi);
            img_luotthich.setVisibility(View.INVISIBLE);
            tvRank.setVisibility(View.VISIBLE);
        }

        void bindView(int position) {
            tvRank.setText(songs.get(position).getNo()+"");
            tvRank.setTextColor(songs.get(position).getColor());
            img_baihatyeuthich.setImageResource(songs.get(position).getAvatar());
            tv_tenBaiHat.setText(songs.get(position).getSong());
            tv_tenCaSi.setText(songs.get(position).getSonger());
        }
    }
}

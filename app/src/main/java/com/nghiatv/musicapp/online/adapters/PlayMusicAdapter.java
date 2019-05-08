package com.nghiatv.musicapp.online.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nghiatv.musicapp.R;
import com.nghiatv.musicapp.dto.SongDto;

import java.util.List;

public class PlayMusicAdapter extends RecyclerView.Adapter<PlayMusicAdapter.ViewHolder> {
    Context mContext;
    List<SongDto> listBH;

    public PlayMusicAdapter(Context mContext, List<SongDto> listBH) {
        this.mContext = mContext;
        this.listBH = listBH;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.item_play_music, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SongDto baihat = listBH.get(position);
        holder.txtTenCaSi.setText(baihat.getArtistName());
        holder.txtTenBaiHat.setText(baihat.getTitle());
        holder.txtIndex.setText(position + 1 + "");
    }

    @Override
    public int getItemCount() {
        return listBH.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenCaSi, txtTenBaiHat, txtIndex;

        public ViewHolder(View itemView) {
            super(itemView);
            txtTenBaiHat = itemView.findViewById(R.id.tv_playnhacTenbaihat);
            txtIndex = itemView.findViewById(R.id.tv_playnhacindex);
            txtTenCaSi = itemView.findViewById(R.id.tv_tenCaSiPlayMusic);
        }
    }
}

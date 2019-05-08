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
import android.widget.Toast;

import com.nghiatv.musicapp.R;
import com.nghiatv.musicapp.online.activities.PlayMusicActivity;
import com.nghiatv.musicapp.dto.SongDto;
import com.nghiatv.musicapp.webservices.APIService;
import com.nghiatv.musicapp.webservices.DataService;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SongListOnlineAdapter extends RecyclerView.Adapter<SongListOnlineAdapter.ViewHolder> {
    private static final String TAG = "SongListOnlineAdapter";

    private Context mContext;
    private List<SongDto> songDtoList;

    public SongListOnlineAdapter(Context mContext, List<SongDto> songDtoList) {
        this.mContext = mContext;
        this.songDtoList = songDtoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.item_online_songlist, parent, false);
        ViewHolder mViewHolder = new ViewHolder(v);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SongDto songDto = songDtoList.get(position);
        holder.txtArtistSong.setText(songDto.getArtistName());
        holder.txtTitleSong.setText(songDto.getTitle());
        ImageLoader.getInstance().displayImage(songDto.getImage(), holder.imgSong,
                new DisplayImageOptions.Builder()
                        .cacheInMemory(true)
                        .showImageOnLoading(R.drawable.icon_splash_screen)
                        .resetViewBeforeLoading(true).build());
    }

    @Override
    public int getItemCount() {
        return songDtoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgLike, imgSong;
        private TextView txtTitleSong, txtArtistSong;

        public ViewHolder(View itemView) {
            super(itemView);
            imgSong = itemView.findViewById(R.id.imgSong);
            imgLike = itemView.findViewById(R.id.imgLike);
            txtTitleSong = itemView.findViewById(R.id.txtTitleSong);
            txtArtistSong = itemView.findViewById(R.id.txtArtistSong);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, PlayMusicActivity.class);
                    intent.putExtra("cakhuc", songDtoList.get(getPosition()));
                    mContext.startActivity(intent);
                }
            });
            imgLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imgLike.setImageResource(R.drawable.iconloved);
                    DataService dataService = APIService.getService();
                    Call<String> mCall = dataService.getDataLuotLikeBaiHat("1", songDtoList.get(getPosition()).getId());
                    mCall.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String resuilt = response.body();
                            if (resuilt.equals("OK")) {
                                Toast.makeText(mContext, "Đã Thích Cám Ơn", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(mContext, "Please Check Again !", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                    imgLike.setEnabled(false);
                }
            });

        }
    }
}

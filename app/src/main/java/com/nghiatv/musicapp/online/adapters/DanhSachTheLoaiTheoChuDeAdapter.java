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
import com.nghiatv.musicapp.dto.TheLoai;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DanhSachTheLoaiTheoChuDeAdapter extends RecyclerView.Adapter<DanhSachTheLoaiTheoChuDeAdapter.ViewHolder> {
    Context mContext;
    List<TheLoai> mTheLoais;

    public DanhSachTheLoaiTheoChuDeAdapter(Context mContext, List<TheLoai> mTheLoais) {
        this.mContext = mContext;
        this.mTheLoais = mTheLoais;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.item_the_loai_theo_chu_de, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TheLoai theLoai = mTheLoais.get(position);
        Picasso.with(mContext).load(theLoai.getHinhTheLoai()).into(holder.imgTheoChuDe);
        holder.txtTheochude.setText(theLoai.getTenTheLoai());
    }

    @Override
    public int getItemCount() {
        return mTheLoais.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgTheoChuDe;
        TextView txtTheochude;

        public ViewHolder(View itemView) {
            super(itemView);
            imgTheoChuDe = itemView.findViewById(R.id.img_theloaitheochude);
            txtTheochude = itemView.findViewById(R.id.tv_theloaitheochude);
              itemView.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Intent intent = new Intent(mContext, SongsListActivity.class);
                      intent.putExtra("idtheloai",mTheLoais.get(getPosition()));
                      mContext.startActivity(intent);
                  }
              });
        }
    }
}

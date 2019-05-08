package com.nghiatv.musicapp.online.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nghiatv.musicapp.R;
import com.nghiatv.musicapp.online.activities.SongsListActivity;
import com.nghiatv.musicapp.dto.BannerDto;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

public class BannerAdapter extends PagerAdapter {
    private static final String TAG = "BannerAdapter";

    private Context mContext;
    private ArrayList<BannerDto> mListBannerDto;

    public BannerAdapter(Context mContext, ArrayList<BannerDto> mListBannerDto) {
        this.mContext = mContext;
        this.mListBannerDto = mListBannerDto;
    }

    @Override
    public int getCount() {
        return mListBannerDto.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {

        return view == object;
    }

    @NonNull
    // định hình object và gán dữ liệu cho mỗi object tượng trưng cho mỗi cái page
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        final LayoutInflater inflater = LayoutInflater.from(mContext);
        View rootView = inflater.inflate(R.layout.item_banner, null);

        ImageView imgBackgroundBanner = rootView.findViewById(R.id.imgBackgroundBanner);
        ImageView imgIconBanner = rootView.findViewById(R.id.imgIconBanner);
        TextView txtTitleBanner = rootView.findViewById(R.id.txtTitleBanner);
        TextView txtSongBanner = rootView.findViewById(R.id.txtSongBanner);

        ImageLoader.getInstance().displayImage(mListBannerDto.get(position).getHinhanh(), imgBackgroundBanner,
                new DisplayImageOptions.Builder()
                        .cacheInMemory(true)
                        .showImageOnLoading(R.drawable.icon_splash_screen)
                        .resetViewBeforeLoading(true).build());
        ImageLoader.getInstance().displayImage(mListBannerDto.get(position).getHinhbaihat(), imgIconBanner,
                new DisplayImageOptions.Builder()
                        .cacheInMemory(true)
                        .showImageOnLoading(R.drawable.icon_splash_screen)
                        .resetViewBeforeLoading(true).build());
        txtTitleBanner.setText(mListBannerDto.get(position).getTenbaihat());
        txtSongBanner.setText(mListBannerDto.get(position).getNoidung());

        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, SongsListActivity.class);
                intent.putExtra("quangcao", mListBannerDto.get(position));
                mContext.startActivity(intent);
            }
        });
        container.addView(rootView);
        return rootView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        // sau khi thuc hien xong thì xóa view
        container.removeView((View) object);
    }
}

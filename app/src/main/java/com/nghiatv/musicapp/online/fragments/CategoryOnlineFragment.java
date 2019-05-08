package com.nghiatv.musicapp.online.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nghiatv.musicapp.R;
import com.nghiatv.musicapp.online.activities.DanhSachAllChuDeActivity;
import com.nghiatv.musicapp.online.activities.DanhSachTheLoaiTheoChuDeActivity;
import com.nghiatv.musicapp.online.activities.SongsListActivity;
import com.nghiatv.musicapp.dto.ChuDeAndTheLoai;
import com.nghiatv.musicapp.dto.ChuDe;
import com.nghiatv.musicapp.dto.TheLoai;
import com.nghiatv.musicapp.webservices.APIService;
import com.nghiatv.musicapp.webservices.DataService;
import com.nghiatv.musicapp.utils.PreferencesUtility;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryOnlineFragment extends Fragment {
    private static final String TAG = "CategoryOnlineFragment";

    private HorizontalScrollView mHorizontalScrollView;
    private TextView txtMoreCategory;

    private PreferencesUtility mPreferences;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPreferences = PreferencesUtility.getInstance(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_online_category, container, false);

        mHorizontalScrollView = rootView.findViewById(R.id.myScollCategory);
        txtMoreCategory = rootView.findViewById(R.id.txtMoreCategory);
        txtMoreCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DanhSachAllChuDeActivity.class);
                startActivity(intent);
            }
        });

        getData();

        return rootView;
    }

    private void getData() {
        DataService mDataService = APIService.getService();
        Call<ChuDeAndTheLoai> mCall = mDataService.getDataChuDeTheLoai();
        mCall.enqueue(new Callback<ChuDeAndTheLoai>() {
            @Override
            public void onResponse(Call<ChuDeAndTheLoai> call, Response<ChuDeAndTheLoai> response) {
                ChuDeAndTheLoai mChuDeAndTheLoai = response.body();
                final ArrayList<ChuDe> chuDeArrayList = new ArrayList<ChuDe>();
                // addAll() là add thêm 1 mảng cùng kiểu dữ liệu vào mảng chủ đề
                chuDeArrayList.addAll(mChuDeAndTheLoai.getChuDe());
                final ArrayList<TheLoai> theLoaiArrayList = new ArrayList<TheLoai>();
                // addAll() là add thêm 1 mảng cùng kiểu dữ liệu vào mảng thể loại
                theLoaiArrayList.addAll(mChuDeAndTheLoai.getTheLoai());

                LinearLayout mLinearLayout = new LinearLayout(getActivity());
                mLinearLayout.setOrientation(LinearLayout.HORIZONTAL);

                // set lại kích thước cho layout
                LinearLayout.LayoutParams mLayoutParams = new LinearLayout.LayoutParams(580, 250);
                mLayoutParams.setMargins(10, 20, 10, 30);

                for (int i = 0; i < chuDeArrayList.size(); i++) {
                    CardView mCardView = new CardView(getActivity());
                    mCardView.setRadius(10); // bo xung quanh 10dp
                    ImageView mImageView = new ImageView(getActivity());
                    mImageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if (chuDeArrayList.get(i).getHinhChuDe() != null) {
                        ImageLoader.getInstance().displayImage(chuDeArrayList.get(i).getHinhChuDe(), mImageView,
                                new DisplayImageOptions.Builder()
                                        .cacheInMemory(true)
                                        .showImageOnLoading(R.drawable.icon_splash_screen)
                                        .resetViewBeforeLoading(true).build());
                    }
                    mCardView.setLayoutParams(mLayoutParams);
                    mCardView.addView(mImageView);
                    mLinearLayout.addView(mCardView);
                    final int finalI = i;
                    mImageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), DanhSachTheLoaiTheoChuDeActivity.class);
                            intent.putExtra("chude", chuDeArrayList.get(finalI));
//                            Log.d("BBB", chuDeArrayList.get(0).getIDChuDe());
                            startActivity(intent);
                        }
                    });
                }
                for (int j = 0; j < theLoaiArrayList.size(); j++) {
                    CardView mCardView = new CardView(getActivity());
                    mCardView.setRadius(10); // bo xung quanh 10dp
                    ImageView mImageView = new ImageView(getActivity());
                    mImageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if (theLoaiArrayList.get(j).getHinhTheLoai() != null) {
                        ImageLoader.getInstance().displayImage(theLoaiArrayList.get(j).getHinhTheLoai(), mImageView,
                                new DisplayImageOptions.Builder()
                                        .cacheInMemory(true)
                                        .showImageOnLoading(R.drawable.icon_splash_screen)
                                        .resetViewBeforeLoading(true).build());
                    }
                    mCardView.setLayoutParams(mLayoutParams);
                    mCardView.addView(mImageView);
                    mLinearLayout.addView(mCardView);

                    final int finalJ = j;
                    mImageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), SongsListActivity.class);
                            intent.putExtra("idtheloai", theLoaiArrayList.get(finalJ));
                            startActivity(intent);
                        }
                    });
                }
                mHorizontalScrollView.addView(mLinearLayout);

            }

            @Override
            public void onFailure(Call<ChuDeAndTheLoai> call, Throwable t) {

            }
        });
    }
}

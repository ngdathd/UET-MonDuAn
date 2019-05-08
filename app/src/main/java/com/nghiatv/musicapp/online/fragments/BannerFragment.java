package com.nghiatv.musicapp.online.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nghiatv.musicapp.R;
import com.nghiatv.musicapp.online.adapters.BannerAdapter;
import com.nghiatv.musicapp.dto.BannerDto;
import com.nghiatv.musicapp.webservices.APIService;
import com.nghiatv.musicapp.webservices.DataService;
import com.nghiatv.musicapp.utils.PreferencesUtility;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BannerFragment extends Fragment {
    private static final String TAG = "BannerFragment";

    private ViewPager mViewPager;
    private BannerAdapter mAdapter;
    private CircleIndicator mCircleIndicator;
    private Runnable mRunnable;
    private Handler mHandler;
    int item;

    private PreferencesUtility mPreferences;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPreferences = PreferencesUtility.getInstance(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_banner, container, false);
        mViewPager = rootView.findViewById(R.id.viewPager);
        mCircleIndicator = rootView.findViewById(R.id.myIndicator);
        getBanner();
        return rootView;
    }


    public void getBanner() {
        DataService mDataService = APIService.getService(); // khởi tạo retrofit để đẩy lên
        Call<List<BannerDto>> listCall = mDataService.getDataBanner();
        // enqueue() sự kiện lắng nghe
        listCall.enqueue(new Callback<List<BannerDto>>() {
            @Override
            public void onResponse(Call<List<BannerDto>> call, Response<List<BannerDto>> response) {
                // lấy dữ liệu ra
                final ArrayList<BannerDto> arrayListBanner = (ArrayList<BannerDto>) response.body();
                mAdapter = new BannerAdapter(getActivity(), arrayListBanner);
                mViewPager.setAdapter(mAdapter);
                // hiện ra số lượng indicator tùy theo số lượng pager
                mCircleIndicator.setViewPager(mViewPager);
                mHandler = new Handler();
                // thực hiện hành động khi handler gọi
                mRunnable = new Runnable() {
                    @Override
                    public void run() {
                        // item hiện tại đang đứng ở đâu
                        item = mViewPager.getCurrentItem();
                        item++;
//                        // nếu  vượt quá kích thức page thì trở lại pager đầu
                        assert arrayListBanner != null;
                        if (item >= arrayListBanner.size()) {
                            item = 0;
                        }
                        // chạy xong set dữ liệu lên
                        mViewPager.setCurrentItem(item, true);
                        //mHandler.postDelayed(mRunnable, 4000);
                    }

                };
                mHandler.postDelayed(mRunnable, 4000);
            }

            @Override
            public void onFailure(Call<List<BannerDto>> call, Throwable t) {

            }
        });
    }
}

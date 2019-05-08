package com.nghiatv.musicapp.online.fragments;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

import com.nghiatv.musicapp.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class FragmentCDMusic extends Fragment {
    View view;
    private CircleImageView mCircleImageView;
    // khi click nó tạo ra các hình ảnh
    private ObjectAnimator mObjectAnimator;

    private boolean aLive = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cd_music, container, false);

        mCircleImageView = view.findViewById(R.id.img_circle);

        mObjectAnimator = ObjectAnimator.ofFloat(mCircleImageView, "rotation", 0f, 360f);
        mObjectAnimator.setDuration(10000);

        mObjectAnimator.setRepeatCount(ValueAnimator.INFINITE);

        mObjectAnimator.setRepeatMode(ValueAnimator.RESTART);

        mObjectAnimator.setInterpolator(new LinearInterpolator());

        mObjectAnimator.start();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        aLive = true;
        super.onActivityCreated(savedInstanceState);
    }

    public void Playnhac(String hinhAnh) {
        Log.d("TAG", hinhAnh);
        Picasso.with(getActivity()).load(hinhAnh).into(mCircleImageView);
    }

    public void stopAnimation() {
//        mObjectAnimator = ObjectAnimator.ofFloat(mCircleImageView, "rotation", 0f, 360f);
//        mObjectAnimator.setDuration(10000);
//        mObjectAnimator.setRepeatCount(ValueAnimator.INFINITE);
//        mObjectAnimator.setRepeatMode(ValueAnimator.RESTART);
//        mObjectAnimator.setInterpolator(new LinearInterpolator());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mObjectAnimator.pause();
        }

    }
}

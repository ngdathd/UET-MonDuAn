package com.nghiatv.musicapp.online.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nghiatv.musicapp.R;
import com.nghiatv.musicapp.online.activities.PlayMusicActivity;
import com.nghiatv.musicapp.online.adapters.PlayMusicAdapter;


public class FragmentPlayDanhSachBaiHat extends Fragment {
    View view;
    RecyclerView mRecyclerView;
    PlayMusicAdapter musicAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_play_danh_sach_bai_hat, container, false);
        mRecyclerView = view.findViewById(R.id.recyclePlayDanhSachBH);
        if (PlayMusicActivity.baiHatList.size() > 0 ){
            musicAdapter = new PlayMusicAdapter(getActivity(), PlayMusicActivity.baiHatList);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            mRecyclerView.setAdapter(musicAdapter);
        }
        return view;
    }
}

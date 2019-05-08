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
import android.widget.Toast;

import com.nghiatv.musicapp.R;
import com.nghiatv.musicapp.online.adapters.SongListOnlineAdapter;
import com.nghiatv.musicapp.dto.SongDto;
import com.nghiatv.musicapp.webservices.APIService;
import com.nghiatv.musicapp.webservices.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SongListOnlineFragment extends Fragment {
    private static final String TAG = "SongListOnlineFragment";

    private RecyclerView mRecyclerView;
    private SongListOnlineAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_online_songlist, container, false);

        mRecyclerView = rootView.findViewById(R.id.myRecycleBaiHatYeuThich);

        getData();

        return rootView;
    }

    private void getData() {
        DataService mDataService = APIService.getService();
        Call<List<SongDto>> mCall = mDataService.getDataSongDto();
        mCall.enqueue(new Callback<List<SongDto>>() {
            @Override
            public void onResponse(Call<List<SongDto>> call, Response<List<SongDto>> response) {
                ArrayList<SongDto> songDtoArrayList = (ArrayList<SongDto>) response.body();
                mAdapter = new SongListOnlineAdapter(getActivity(), songDtoArrayList);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                mRecyclerView.setLayoutManager(layoutManager);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<List<SongDto>> call, Throwable t) {
                Toast.makeText(getActivity(), " Please Check Your Internet Again !", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

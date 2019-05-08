package com.nghiatv.musicapp.online.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nghiatv.musicapp.R;
import com.nghiatv.musicapp.dto.AlbumDto;
import com.nghiatv.musicapp.online.activities.DanhSachAllAlbumActivity;
import com.nghiatv.musicapp.online.adapters.AlbumOnlineAdapter;
import com.nghiatv.musicapp.webservices.APIService;
import com.nghiatv.musicapp.webservices.DataService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumOnlineFragment extends Fragment {
    private static final String TAG = "AlbumOnlineFragment";

    private RecyclerView mRecyclerView;
    private TextView txtMoreAlbum;
    private AlbumOnlineAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_online_album, container, false);

        mRecyclerView = rootView.findViewById(R.id.myRecycleAlbum);
        txtMoreAlbum = rootView.findViewById(R.id.txtMoreAlbum);
        txtMoreAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DanhSachAllAlbumActivity.class);
                startActivity(intent);
            }
        });

        getData();

        return rootView;
    }

    private void getData() {
        DataService mDataService = APIService.getService();
        Call<List<AlbumDto>> mCall = mDataService.getDataAlbum();
        mCall.enqueue(new Callback<List<AlbumDto>>() {
            @Override
            public void onResponse(Call<List<AlbumDto>> call, Response<List<AlbumDto>> response) {
                List<AlbumDto> albumList = response.body();
                mAdapter = new AlbumOnlineAdapter(getActivity(), albumList);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                mRecyclerView.setLayoutManager(linearLayoutManager);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<List<AlbumDto>> call, Throwable t) {

            }
        });
    }
}

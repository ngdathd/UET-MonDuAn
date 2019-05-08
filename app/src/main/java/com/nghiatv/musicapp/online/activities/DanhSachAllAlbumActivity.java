package com.nghiatv.musicapp.online.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.nghiatv.musicapp.R;
import com.nghiatv.musicapp.dto.AlbumDto;
import com.nghiatv.musicapp.online.adapters.DanhSachAllAlbumAdapter;
import com.nghiatv.musicapp.webservices.APIService;
import com.nghiatv.musicapp.webservices.DataService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachAllAlbumActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    Toolbar toolbar;
    DanhSachAllAlbumAdapter mDanhSachAllAlbumAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_all_album);
        initView();
        GetData();
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.recycleAllAlbum);
        toolbar = findViewById(R.id.toolbarAllAlbum);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Tất Cả AlBum Bài Hát");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<List<AlbumDto>> mCall = dataService.getAllAlbum();
        mCall.enqueue(new Callback<List<AlbumDto>>() {
            @Override
            public void onResponse(Call<List<AlbumDto>> call, Response<List<AlbumDto>> response) {
                List<AlbumDto> albumList = response.body();
                mDanhSachAllAlbumAdapter = new DanhSachAllAlbumAdapter(DanhSachAllAlbumActivity.this, albumList);
                mRecyclerView.setLayoutManager(new GridLayoutManager(DanhSachAllAlbumActivity.this, 2));
                mRecyclerView.setAdapter(mDanhSachAllAlbumAdapter);
            }

            @Override
            public void onFailure(Call<List<AlbumDto>> call, Throwable t) {

            }
        });
    }
}

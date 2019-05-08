package com.nghiatv.musicapp.online.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.StrictMode;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.nghiatv.musicapp.R;
import com.nghiatv.musicapp.dto.AlbumDto;
import com.nghiatv.musicapp.dto.PlaylistDto;
import com.nghiatv.musicapp.online.adapters.DanhSachBaiHatAdapter;
import com.nghiatv.musicapp.dto.SongDto;
import com.nghiatv.musicapp.dto.PlaylistAll;
import com.nghiatv.musicapp.dto.BannerDto;
import com.nghiatv.musicapp.dto.TheLoai;
import com.nghiatv.musicapp.webservices.APIService;
import com.nghiatv.musicapp.webservices.DataService;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SongsListActivity extends AppCompatActivity {
    CoordinatorLayout mCoordinatorLayout;
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    Toolbar mToolbar;
    RecyclerView mRecyclerView;
    Button mButtonNgheTatCa;
    ImageView mImageView;
    BannerDto mQuangcaoDto;
    List<SongDto> listBaiHat ;
    DanhSachBaiHatAdapter mAdapter;
    PlaylistDto mPlaylistDto;
    PlaylistAll mPlaylistAll;
    TheLoai mTheLoai;
    AlbumDto mAlbum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs_list);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        DataItent();
        initView();
        init();

        if (mQuangcaoDto != null && !mQuangcaoDto.getTenbaihat().equals("")) {
            setValuesInView(mQuangcaoDto.getTenbaihat(), mQuangcaoDto.getHinhbaihat());
            getDataQuangCao(mQuangcaoDto.getId());
        }
        if (mPlaylistDto != null && !mPlaylistDto.getTen().equals("")) {
            setValuesInView(mPlaylistDto.getTen(), mPlaylistDto.getHinhAnhPlaylist());
            getDataPlaylist(mPlaylistDto.getIdPlaylist());
        }
        if (mPlaylistAll != null && !mPlaylistAll.getTen().equals("")) {
            setValuesInView(mPlaylistAll.getTen(), mPlaylistAll.getHinhNen());
            getDataPlaylist(mPlaylistAll.getIdPlaylist());
        }
        if (mTheLoai != null && !mTheLoai.getTenTheLoai().equals("")) {
            setValuesInView(mTheLoai.getTenTheLoai(), mTheLoai.getHinhTheLoai());
            getDataTheLoai(mTheLoai.getIDTheLoai());
        }
        if (mAlbum != null && !mAlbum.getTenAlbum().equals("")) {
            setValuesInView(mAlbum.getTenAlbum(), mAlbum.getHinhAlbum());
            getDataAlbum(mAlbum.getIdAlbum());
        }
    }

    private void getDataAlbum(String idAlbum) {
        DataService dataService = APIService.getService();
        Call<List<SongDto>> mCall = dataService.getDataBaiHatTheoAlbum(idAlbum);
        mCall.enqueue(new Callback<List<SongDto>>() {
            @Override
            public void onResponse(Call<List<SongDto>> call, Response<List<SongDto>> response) {
                listBaiHat = response.body();
                mAdapter = new DanhSachBaiHatAdapter(SongsListActivity.this, listBaiHat);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(SongsListActivity.this));
                mRecyclerView.setAdapter(mAdapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<SongDto>> call, Throwable t) {

            }
        });
    }

    private void getDataTheLoai(String idtheloai) {
        DataService mDataService = APIService.getService();
        Call<List<SongDto>> mCall = mDataService.getDataBaiHatTheoTheLoai(idtheloai);
        mCall.enqueue(new Callback<List<SongDto>>() {
            @Override
            public void onResponse(Call<List<SongDto>> call, Response<List<SongDto>> response) {
                listBaiHat = response.body();
                mAdapter = new DanhSachBaiHatAdapter(SongsListActivity.this, listBaiHat);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(SongsListActivity.this));
                mRecyclerView.setAdapter(mAdapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<SongDto>> call, Throwable t) {

            }
        });
    }

    private void getDataPlaylist(String idplaylist) {
        DataService mDataService = APIService.getService();
        Call<List<SongDto>> call = mDataService.getDataBaiHatTheoPlaylist(idplaylist);
        call.enqueue(new Callback<List<SongDto>>() {
            @Override
            public void onResponse(Call<List<SongDto>> call, Response<List<SongDto>> response) {
                listBaiHat = response.body();
                mAdapter = new DanhSachBaiHatAdapter(SongsListActivity.this, listBaiHat);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(SongsListActivity.this));
                mRecyclerView.setAdapter(mAdapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<SongDto>> call, Throwable t) {

            }
        });
    }

    // lấy data tên bài hát để gắn lên toolbar
    private void setValuesInView(String name, String image) {
        mCollapsingToolbarLayout.setTitle(name);
        try {
            URL mUrl = new URL(image);
            Bitmap mBitmap = BitmapFactory.decodeStream(mUrl.openConnection().getInputStream());
            BitmapDrawable mBitmapDrawable = new BitmapDrawable(getResources(), mBitmap);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                mCollapsingToolbarLayout.setBackground(mBitmapDrawable);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Picasso.with(this).load(image).into(mImageView);
    }

    private void getDataQuangCao(String idquangcao) {
        DataService mDataService = APIService.getService();
        Call<List<SongDto>> mCall = mDataService.getDataBaiHatTheoQuangCao(idquangcao);
        mCall.enqueue(new Callback<List<SongDto>>() {
            @Override
            public void onResponse(Call<List<SongDto>> call, Response<List<SongDto>> response) {
                listBaiHat = response.body();
                mAdapter = new DanhSachBaiHatAdapter(SongsListActivity.this, listBaiHat);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(SongsListActivity.this));
                mRecyclerView.setAdapter(mAdapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<SongDto>> call, Throwable t) {

            }
        });
    }


    private void init() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//        mCollapsingToolbarLayout.setExpandedTitleColor(Color.BLUE);
//        mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.BLACK);
//        mCollapsingToolbarLayout.setExpandedTitleMarginStart(25);
//        mCollapsingToolbarLayout.setExpandedTitleTextAppearance(18);
//        mCollapsingToolbarLayout.setCollapsedTitleTextAppearance(18);
        mButtonNgheTatCa.setEnabled(false);
    }

    private void initView() {
        mCollapsingToolbarLayout = findViewById(R.id.myCollapsingToolLayout);
        mCoordinatorLayout = findViewById(R.id.myCooridinerLayout);
        mToolbar = findViewById(R.id.my_toolbarList);
        mRecyclerView = findViewById(R.id.recycleDanhSachBH);
        mButtonNgheTatCa = findViewById(R.id.btn_nghetatca);
        mImageView = findViewById(R.id.img_danhSachbaihat);
    }

    private void DataItent() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("quangcao")) {
                mQuangcaoDto = (BannerDto) intent.getSerializableExtra("quangcao");
            }
            if (intent.hasExtra("itemPlaylist")) {
                mPlaylistDto = (PlaylistDto) intent.getSerializableExtra("itemPlaylist");
            }
            if (intent.hasExtra("itemPlaylistAll")) {
                mPlaylistAll = (PlaylistAll) intent.getSerializableExtra("itemPlaylistAll");
            }
            if (intent.hasExtra("idtheloai")) {
                mTheLoai = (TheLoai) intent.getSerializableExtra("idtheloai");
            }
            if (intent.hasExtra("album")) {
                mAlbum = (AlbumDto) intent.getSerializableExtra("album");
            }

        }
    }

    private void eventClick() {
        mButtonNgheTatCa.setEnabled(true);
        mButtonNgheTatCa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SongsListActivity.this, PlayMusicActivity.class);
                intent.putParcelableArrayListExtra("allbaihat", (ArrayList<? extends Parcelable>) listBaiHat);
                startActivity(intent);
            }
        });
    }
}

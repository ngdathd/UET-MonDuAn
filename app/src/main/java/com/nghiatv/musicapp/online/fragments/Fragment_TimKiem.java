package com.nghiatv.musicapp.online.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nghiatv.musicapp.R;
import com.nghiatv.musicapp.online.adapters.SearchBaiHatAdapter;
import com.nghiatv.musicapp.dto.SongDto;
import com.nghiatv.musicapp.webservices.APIService;
import com.nghiatv.musicapp.webservices.DataService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_TimKiem extends Fragment {
    View view;
    Toolbar toolbar;
    RecyclerView mRecyclerView;
    TextView mTextViewKoCoData;
    SearchBaiHatAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tim_kiem, container, false);
        toolbar = view.findViewById(R.id.toolbartimkiembaihat);
        mTextViewKoCoData = view.findViewById(R.id.tv_DataNull);
        mRecyclerView = view.findViewById(R.id.recycleviewTimKiem);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        toolbar.setTitle("");
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_view, menu);
        MenuItem menuItem = menu.findItem(R.id.mySearchBH);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                SearchBaiHat(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    public void SearchBaiHat(String keyword) {
        DataService dataService = APIService.getService();
        Call<List<SongDto>> call = dataService.getSearchBaiHat(keyword);
        call.enqueue(new Callback<List<SongDto>>() {
            @Override
            public void onResponse(Call<List<SongDto>> call, Response<List<SongDto>> response) {
                List<SongDto> listBH = response.body();
                if (listBH.size() > 0) {
                    mAdapter = new SearchBaiHatAdapter(getActivity(), listBH);
                    mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    mRecyclerView.setAdapter(mAdapter);
                    mTextViewKoCoData.setVisibility(View.GONE);
                    mRecyclerView.setVisibility(View.VISIBLE);
                } else {
                    mRecyclerView.setVisibility(View.GONE);
                    mTextViewKoCoData.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<SongDto>> call, Throwable t) {

            }
        });
    }
}

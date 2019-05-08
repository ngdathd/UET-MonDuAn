
package com.nghiatv.musicapp.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.nghiatv.musicapp.R;
import com.nghiatv.musicapp.activities.BaseActivity;
import com.nghiatv.musicapp.adapters.SongsListAdapter;
import com.nghiatv.musicapp.dataloaders.SongLoader;
import com.nghiatv.musicapp.dto.SongDto;
import com.nghiatv.musicapp.listeners.MusicStateListener;
import com.nghiatv.musicapp.models.Song;
import com.nghiatv.musicapp.webservices.APIService;
import com.nghiatv.musicapp.webservices.DataService;
import com.nghiatv.musicapp.utils.PreferencesUtility;
import com.nghiatv.musicapp.utils.SortOrder;
import com.nghiatv.musicapp.widgets.BaseRecyclerView;
import com.nghiatv.musicapp.widgets.DividerItemDecoration;
import com.nghiatv.musicapp.widgets.FastScroller;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SongsFragment extends Fragment implements MusicStateListener {
    private static final String TAG = "SongsFragment";

    private SongsListAdapter mAdapter;
    private BaseRecyclerView recyclerView;
    private PreferencesUtility mPreferences;


    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPreferences = PreferencesUtility.getInstance(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.fragment_recyclerview, container, false);

        recyclerView = rootView.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setEmptyView(getActivity(), rootView.findViewById(R.id.list_empty), "No media found");
        FastScroller fastScroller = rootView.findViewById(R.id.fastscroller);
        fastScroller.setRecyclerView(recyclerView);

        new loadSongs().execute("");
        ((BaseActivity) getActivity()).setMusicStateListenerListener(this);

        return rootView;
    }

    public void restartLoader() {

    }

    public void onPlaylistChanged() {

    }

    public void onMetaChanged() {
        if (mAdapter != null)
            mAdapter.notifyDataSetChanged();
    }

    private void reloadAdapter() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(final Void... unused) {
                List<Song> songList = SongLoader.getAllSongs(getActivity());
                mAdapter.updateDataSet(songList);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                mAdapter.notifyDataSetChanged();
            }
        }.execute();
    }

    @Override
    public void onActivityCreated(final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.song_sort_by, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_sort_by_az:
                mPreferences.setSongSortOrder(SortOrder.SongSortOrder.SONG_A_Z);
                reloadAdapter();
                return true;
            case R.id.menu_sort_by_za:
                mPreferences.setSongSortOrder(SortOrder.SongSortOrder.SONG_Z_A);
                reloadAdapter();
                return true;
            case R.id.menu_sort_by_artist:
                mPreferences.setSongSortOrder(SortOrder.SongSortOrder.SONG_ARTIST);
                reloadAdapter();
                return true;
            case R.id.menu_sort_by_album:
                mPreferences.setSongSortOrder(SortOrder.SongSortOrder.SONG_ALBUM);
                reloadAdapter();
                return true;
            case R.id.menu_sort_by_year:
                mPreferences.setSongSortOrder(SortOrder.SongSortOrder.SONG_YEAR);
                reloadAdapter();
                return true;
            case R.id.menu_sort_by_duration:
                mPreferences.setSongSortOrder(SortOrder.SongSortOrder.SONG_DURATION);
                reloadAdapter();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class loadSongs extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            if (getActivity() != null) {
                /**android.app.AlertDialog waitingDialog = new SpotsDialog.Builder()
                 .setContext(getActivity())
                 .setMessage("Hãy chờ trong giây lát")
                 .setCancelable(false)
                 .build();
                 waitingDialog.show();*/
                mAdapter = new SongsListAdapter((AppCompatActivity) getActivity(), SongLoader.getAllSongs(getActivity()), false, false);
                //mAdapter = new SongsListAdapter((AppCompatActivity) getActivity(), getData(), false, false);
            }
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            recyclerView.setAdapter(mAdapter);
            if (getActivity() != null)
                recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));

        }

        @Override
        protected void onPreExecute() {
        }
    }

    private ArrayList<Song> getData() {
        final ArrayList<Song> songArrayList = new ArrayList<>();
        DataService mDataService = APIService.getService();
        Call<List<SongDto>> mCall = mDataService.getDataSongDto();
        mCall.enqueue(new Callback<List<SongDto>>() {
            @Override
            public void onResponse(Call<List<SongDto>> call, Response<List<SongDto>> response) {
                ArrayList<SongDto> songDtoArrayList = (ArrayList<SongDto>) response.body();
                /**for (int i = 0; i < songDtoArrayList.size(); i++) {
                 SongDto songDto = songDtoArrayList.get(i);
                 Song song = new Song(Long.parseLong(songDto.getId()), 2, 2,
                 songDto.getTitle(), songDto.getArtistName(), "Lalala", 80, 2);
                 songArrayList.add(song);
                 }*/
                songArrayList.add(new Song(Long.parseLong(songDtoArrayList.get(0).getId()), 2, 2,
                        songDtoArrayList.get(0).getTitle(), songDtoArrayList.get(0).getArtistName(), "Lalala", 80, 2));
            }

            @Override
            public void onFailure(Call<List<SongDto>> call, Throwable t) {
                Toast.makeText(getActivity(), " Please Check Your Internet Again !", Toast.LENGTH_SHORT).show();
            }
        });

        return songArrayList;
    }
}

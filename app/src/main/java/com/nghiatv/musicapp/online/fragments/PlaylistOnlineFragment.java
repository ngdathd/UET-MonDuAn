package com.nghiatv.musicapp.online.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nghiatv.musicapp.R;
import com.nghiatv.musicapp.online.activities.PlaylistActivity;
import com.nghiatv.musicapp.online.activities.SongsListActivity;
import com.nghiatv.musicapp.online.adapters.PlaylistOnlineAdapter;
import com.nghiatv.musicapp.dto.PlaylistDto;
import com.nghiatv.musicapp.webservices.APIService;
import com.nghiatv.musicapp.webservices.DataService;
import com.nghiatv.musicapp.utils.PreferencesUtility;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlaylistOnlineFragment extends Fragment {
    private static final String TAG = "PlaylistOnlineFragment";
    
    private ListView mListViewPlaylist;
    private TextView txtTitlePlaylist, txtMorePlaylist;
    private PlaylistOnlineAdapter mPlaylistOnlineAdapter;
    private List<PlaylistDto> mList;

    private PreferencesUtility mPreferences;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPreferences = PreferencesUtility.getInstance(getActivity());
    }
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_online_playlist, container, false);
        mListViewPlaylist = rootView.findViewById(R.id.lvPlaylist);
        txtTitlePlaylist = rootView.findViewById(R.id.txtTitlePlaylist);
        txtMorePlaylist = rootView.findViewById(R.id.txtMorePlaylist);

        txtMorePlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PlaylistActivity.class);
                startActivity(intent);
            }
        });

        getData();

        return rootView;
    }

    private void getData() {
        DataService mDataService = APIService.getService();
        Call<List<PlaylistDto>> mCall = mDataService.getDataPlaylist();
        mCall.enqueue(new Callback<List<PlaylistDto>>() {
            @Override
            public void onResponse(Call<List<PlaylistDto>> call, Response<List<PlaylistDto>> response) {
                mList = response.body();
                mPlaylistOnlineAdapter = new PlaylistOnlineAdapter(getActivity(), android.R.layout.simple_list_item_1, mList);
                mListViewPlaylist.setAdapter(mPlaylistOnlineAdapter);
                setListViewHeightBasedOnChildren(mListViewPlaylist);
                mListViewPlaylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getActivity(), SongsListActivity.class);
                        intent.putExtra("itemPlaylist", mList.get(position));
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<PlaylistDto>> call, Throwable t) {

            }
        });


    }

    public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = listView.getPaddingTop() + listView.getPaddingBottom();
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);

            if (listItem != null) {
                // This next line is needed before you call measure or else you won't get measured height at all. The listitem needs to be drawn first to know the height.
                listItem.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                totalHeight += listItem.getMeasuredHeight();

            }
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}

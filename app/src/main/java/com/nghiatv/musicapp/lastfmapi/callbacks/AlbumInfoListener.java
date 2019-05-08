package com.nghiatv.musicapp.lastfmapi.callbacks;

import com.nghiatv.musicapp.lastfmapi.models.LastfmAlbum;

public interface AlbumInfoListener {

    void albumInfoSuccess(LastfmAlbum album);

    void albumInfoFailed();

}

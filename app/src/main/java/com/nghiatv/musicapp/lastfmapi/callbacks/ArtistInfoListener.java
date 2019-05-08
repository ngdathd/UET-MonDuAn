package com.nghiatv.musicapp.lastfmapi.callbacks;

import com.nghiatv.musicapp.lastfmapi.models.LastfmArtist;

public interface ArtistInfoListener {

    void artistInfoSucess(LastfmArtist artist);

    void artistInfoFailed();

}

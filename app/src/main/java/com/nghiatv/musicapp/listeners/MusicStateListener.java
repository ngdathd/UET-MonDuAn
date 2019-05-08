package com.nghiatv.musicapp.listeners;

/**
 * Listens for playback changes to send the the fragments bound to this activity
 */
public interface MusicStateListener {

    /**
     * Called when {@link com.nghiatv.musicapp.MusicService#REFRESH} is invoked
     */
    void restartLoader();

    /**
     * Called when {@link com.nghiatv.musicapp.MusicService#PLAYLIST_CHANGED} is invoked
     */
    void onPlaylistChanged();

    /**
     * Called when {@link com.nghiatv.musicapp.MusicService#META_CHANGED} is invoked
     */
    void onMetaChanged();

}

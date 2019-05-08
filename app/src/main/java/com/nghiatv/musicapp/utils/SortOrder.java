package com.nghiatv.musicapp.utils;

import android.provider.MediaStore;

/**
 * Holds all of the sort orders for each list type.
 */
public final class SortOrder {
    private static final String TAG = "SortOrder";
    /**
     * This class is never instantiated
     */
    public SortOrder() {
    }

    /**
     * Artist sort order entries.
     */
    public interface ArtistSortOrder {
        /* Artist sort order A-Z */
        String ARTIST_A_Z = MediaStore.Audio.Artists.DEFAULT_SORT_ORDER;

        /* Artist sort order Z-A */
        String ARTIST_Z_A = ARTIST_A_Z + " DESC";

        /* Artist sort order number of songs */
        String ARTIST_NUMBER_OF_SONGS = MediaStore.Audio.Artists.NUMBER_OF_TRACKS
                + " DESC";

        /* Artist sort order number of albums */
        String ARTIST_NUMBER_OF_ALBUMS = MediaStore.Audio.Artists.NUMBER_OF_ALBUMS
                + " DESC";
    }

    /**
     * AlbumDto sort order entries.
     */
    public interface AlbumSortOrder {
        /* AlbumDto sort order A-Z */
        String ALBUM_A_Z = MediaStore.Audio.Albums.DEFAULT_SORT_ORDER;

        /* AlbumDto sort order Z-A */
        String ALBUM_Z_A = ALBUM_A_Z + " DESC";

        /* AlbumDto sort order songs */
        String ALBUM_NUMBER_OF_SONGS = MediaStore.Audio.Albums.NUMBER_OF_SONGS
                + " DESC";

        /* AlbumDto sort order artist */
        String ALBUM_ARTIST = MediaStore.Audio.Albums.ARTIST;

        /* AlbumDto sort order year */
        String ALBUM_YEAR = MediaStore.Audio.Albums.FIRST_YEAR + " DESC";

    }

    /**
     * SongDto sort order entries.
     */
    public interface SongSortOrder {
        /* SongDto sort order A-Z */
        String SONG_A_Z = MediaStore.Audio.Media.DEFAULT_SORT_ORDER;

        /* SongDto sort order Z-A */
        String SONG_Z_A = SONG_A_Z + " DESC";

        /* SongDto sort order artist */
        String SONG_ARTIST = MediaStore.Audio.Media.ARTIST;

        /* SongDto sort order album */
        String SONG_ALBUM = MediaStore.Audio.Media.ALBUM;

        /* SongDto sort order year */
        String SONG_YEAR = MediaStore.Audio.Media.YEAR + " DESC";

        /* SongDto sort order duration */
        String SONG_DURATION = MediaStore.Audio.Media.DURATION + " DESC";

        /* SongDto sort order date */
        String SONG_DATE = MediaStore.Audio.Media.DATE_ADDED + " DESC";

        /* SongDto sort order filename */
        String SONG_FILENAME = MediaStore.Audio.Media.DATA;
    }

    /**
     * AlbumDto song sort order entries.
     */
    public interface AlbumSongSortOrder {
        /* AlbumDto song sort order A-Z */
        String SONG_A_Z = MediaStore.Audio.Media.DEFAULT_SORT_ORDER;

        /* AlbumDto song sort order Z-A */
        String SONG_Z_A = SONG_A_Z + " DESC";

        /* AlbumDto song sort order track list */
        String SONG_TRACK_LIST = MediaStore.Audio.Media.TRACK + ", "
                + MediaStore.Audio.Media.DEFAULT_SORT_ORDER;

        /* AlbumDto song sort order duration */
        String SONG_DURATION = SongSortOrder.SONG_DURATION;

        /* AlbumDto SongDto sort order year */
        String SONG_YEAR = MediaStore.Audio.Media.YEAR + " DESC";

        /* AlbumDto song sort order filename */
        String SONG_FILENAME = SongSortOrder.SONG_FILENAME;
    }

    /**
     * Artist song sort order entries.
     */
    public interface ArtistSongSortOrder {
        /* Artist song sort order A-Z */
        String SONG_A_Z = MediaStore.Audio.Media.DEFAULT_SORT_ORDER;

        /* Artist song sort order Z-A */
        String SONG_Z_A = SONG_A_Z + " DESC";

        /* Artist song sort order album */
        String SONG_ALBUM = MediaStore.Audio.Media.ALBUM;

        /* Artist song sort order year */
        String SONG_YEAR = MediaStore.Audio.Media.YEAR + " DESC";

        /* Artist song sort order duration */
        String SONG_DURATION = MediaStore.Audio.Media.DURATION + " DESC";

        /* Artist song sort order date */
        String SONG_DATE = MediaStore.Audio.Media.DATE_ADDED + " DESC";

        /* Artist song sort order filename */
        String SONG_FILENAME = SongSortOrder.SONG_FILENAME;
    }

    /**
     * Artist album sort order entries.
     */
    public interface ArtistAlbumSortOrder {
        /* Artist album sort order A-Z */
        String ALBUM_A_Z = MediaStore.Audio.Albums.DEFAULT_SORT_ORDER;

        /* Artist album sort order Z-A */
        String ALBUM_Z_A = ALBUM_A_Z + " DESC";

        /* Artist album sort order songs */
        String ALBUM_NUMBER_OF_SONGS = MediaStore.Audio.Artists.Albums.NUMBER_OF_SONGS
                + " DESC";

        /* Artist album sort order year */
        String ALBUM_YEAR = MediaStore.Audio.Artists.Albums.FIRST_YEAR
                + " DESC";
    }

}

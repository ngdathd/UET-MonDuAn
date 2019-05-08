package com.nghiatv.musicapp.dto;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SongDto implements Parcelable {

    public static final Creator<SongDto> CREATOR = new Creator<SongDto>() {
        @Override
        public SongDto createFromParcel(Parcel in) {
            return new SongDto(in);
        }

        @Override
        public SongDto[] newArray(int size) {
            return new SongDto[size];
        }
    };

    @SerializedName("IdBaiHat")
    @Expose
    private String id;

    @SerializedName("TenBaiHat")
    @Expose
    private String title;

    @SerializedName("HinhBaiHat")
    @Expose
    private String image;

    @SerializedName("CaSi")
    @Expose
    private String artistName;

    @SerializedName("LinkBaiHat")
    @Expose
    private String link;

    @SerializedName("LuotThich")
    @Expose
    private String trackNumber;

    public SongDto(Parcel in) {
        id = in.readString();
        title = in.readString();
        image = in.readString();
        artistName = in.readString();
        link = in.readString();
        trackNumber = in.readString();
    }

    public SongDto(String id, String title, String image, String artistName, String link, String trackNumber) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.artistName = artistName;
        this.link = link;
        this.trackNumber = trackNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(String trackNumber) {
        this.trackNumber = trackNumber;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(image);
        dest.writeString(artistName);
        dest.writeString(link);
        dest.writeString(trackNumber);
    }
}
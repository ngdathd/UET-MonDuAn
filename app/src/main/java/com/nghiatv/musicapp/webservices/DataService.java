package com.nghiatv.musicapp.webservices;

import com.nghiatv.musicapp.dto.AlbumDto;
import com.nghiatv.musicapp.dto.SongDto;
import com.nghiatv.musicapp.dto.ChuDeAndTheLoai;
import com.nghiatv.musicapp.dto.ChuDe;
import com.nghiatv.musicapp.dto.PlaylistAll;
import com.nghiatv.musicapp.dto.PlaylistDto;
import com.nghiatv.musicapp.dto.BannerDto;
import com.nghiatv.musicapp.dto.TheLoai;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

// khi tương tác phía server,n~ yêu cầu thì phải gửi lên đúng cấu trúc thì server mới thực hiện
// dùng để gửi lên những phương thức để chúng ta tương tác phía server và sau khi server kết nối đc rồi
// nó sẽ trả dữ liệu về cho thằng này ==> thằng này dùng để gửi phướng thức và dữ liệu từ phía server về.
public interface DataService {

    @GET("songbanner.php")

        // nhận dữ liệu
    Call<List<BannerDto>> getDataBanner();

    @GET("PlaylistSong.php")
    Call<List<PlaylistDto>> getDataPlaylist();

    @GET("chudeandTheLoai.php")
    Call<ChuDeAndTheLoai> getDataChuDeTheLoai();

    @GET("albumSong.php")
    Call<List<AlbumDto>> getDataAlbum();

    @GET("BaiHatDuocYeuThich.php")
    Call<List<SongDto>> getDataSongDto();

    @FormUrlEncoded
    @POST("DanhSachBaiHat.php")
        // tương tác và gửi data lên và nhận về
    Call<List<SongDto>> getDataBaiHatTheoQuangCao(@Field("idquangcao") String idquangcao);

    @FormUrlEncoded
    @POST("DanhSachBaiHatPlaylist.php")
        // tương tác và gửi data lên và nhận về
    Call<List<SongDto>> getDataBaiHatTheoPlaylist(@Field("idplaylist") String idplaylist);

    @GET("DanhSachAllPlaylist.php")
    Call<List<PlaylistAll>> getAllPlaylist();

    @FormUrlEncoded
    @POST("DanhSachBaiHatPlaylist.php")
        // tương tác và gửi data lên và nhận về
    Call<List<SongDto>> getDataBaiHatTheoTheLoai(@Field("idtheloai") String idtheloai);

    @GET("chuDeAll.php")
    Call<List<ChuDe>> getAllChuDe();

    @FormUrlEncoded
    @POST("TheLoaiTheoChuDe.php")
        // tương tác và gửi data lên và nhận về
    Call<List<TheLoai>> getTheLoaiTheoChuDe(@Field("idchude") String idchude);

    @GET("AlbumAll.php")
    Call<List<AlbumDto>> getAllAlbum();

    @FormUrlEncoded
    @POST("DanhSachBaiHatPlaylist.php")
        // tương tác và gửi data lên và nhận về
    Call<List<SongDto>> getDataBaiHatTheoAlbum(@Field("idalbum") String idalbum);

    @FormUrlEncoded
    @POST("UpdateLuotLike.php")
        // tương tác và gửi data lên và nhận về
    Call<String> getDataLuotLikeBaiHat(@Field("luotthich") String luotlike, @Field("idbaihat") String idbaihat);

    @FormUrlEncoded
    @POST("SearchBH.php")
        // tương tác và gửi data lên và nhận về
    Call<List<SongDto>> getSearchBaiHat(@Field("keyword") String keyword);
}

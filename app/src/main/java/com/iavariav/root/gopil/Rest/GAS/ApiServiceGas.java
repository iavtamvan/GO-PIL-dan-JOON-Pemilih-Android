package com.iavariav.root.gopil.Rest.GAS;

import com.iavariav.root.gopil.BuildConfig;
import com.iavariav.root.gopil.Model.AnggaranModel;
import com.iavariav.root.gopil.Model.AnggaranPartaiModel;
import com.iavariav.root.gopil.Model.AnggaranPribadiModel;
import com.iavariav.root.gopil.Model.DaftarAnggaranModel;
import com.iavariav.root.gopil.Model.DaftarNewsModel;
import com.iavariav.root.gopil.Model.DapilModels;
import com.iavariav.root.gopil.Model.FaqModel;
import com.iavariav.root.gopil.Model.HistoriOnlineModel;
import com.iavariav.root.gopil.Model.IklanMasyarkatModel;
import com.iavariav.root.gopil.Model.LoginModel;
import com.iavariav.root.gopil.Model.NewsModel;
import com.iavariav.root.gopil.Model.ResultVoteModel;
import com.iavariav.root.gopil.Model.SliderModel;
import com.iavariav.root.gopil.Model.TahapanPemiluModel;
import com.iavariav.root.gopil.Model.TataCaraModel;
import com.iavariav.root.gopil.Model.TataTertibModel;
import com.iavariav.root.gopil.Model.TpsModel;
import com.iavariav.root.gopil.Model.WilayahModel;
import com.iavariav.root.gopil.Rest.UploadImage.Result;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiServiceGas {
//    @GET("AKfycbzSX0VVb_KNsORvGBc3q6dPrelDQAFGpc4JyvR-79tuKuKppYEK/exec?action=read&sheetName=register")
    @GET(BuildConfig.BASE_END_URL_Register)
    Call<ArrayList<LoginModel>> getLogin();
    @GET(BuildConfig.BASE_END_URL_News)
    Call<ArrayList<NewsModel>>getNews();
    @GET(BuildConfig.BASE_END_URL_DataWilayah)
    Call<ArrayList<WilayahModel>> getDataWilayah();
    @GET(BuildConfig.BASE_END_URL_Dapil)
    Call<ArrayList<DapilModels>> getDataDapil();
    @GET(BuildConfig.BASE_END_URL_ResultVote)
    Call<ArrayList<ResultVoteModel>> getDataResultVotel();
    @GET(BuildConfig.BASE_END_URL_Histori)
    Call<ArrayList<HistoriOnlineModel>> getDataHistoriOnline();
    @GET(BuildConfig.BASE_END_URL_Slider)
    Call<ArrayList<SliderModel>> getDataSliderOnline();
    @GET(BuildConfig.BASE_END_URL_Partai)
    Call<ResponseBody> getDataPartai();
    @GET(BuildConfig.BASE_END_URL_Aggaran)
    Call<ArrayList<AnggaranModel>> getDataAnggaran();
    @GET(BuildConfig.BASE_END_URL_TahapanPemilu)
    Call<ArrayList<TahapanPemiluModel>> getDataTahapanPemilu();
    @GET(BuildConfig.BASE_END_URL_TataTertib)
    Call<ArrayList<TataTertibModel>> getDataTataTertib();
    @GET(BuildConfig.BASE_END_URL_DaftarAnggaran)
    Call<ArrayList<DaftarAnggaranModel>> getDataDaftarAnggaran();
    @GET(BuildConfig.BASE_END_URL_AnggaranPribadi)
    Call<ArrayList<AnggaranPribadiModel>> getDataAnggaranPribadi();
    @GET(BuildConfig.BASE_END_URL_AnggaranPartai)
    Call<ArrayList<AnggaranPartaiModel>> getDataAnggaranPartai();
    @GET(BuildConfig.BASE_END_URL_TataCara)
    Call<ArrayList<TataCaraModel>> getDataTataCara();
    @GET(BuildConfig.BASE_END_URL_faq)
    Call<ArrayList<FaqModel>> getDataFaq();
    @GET(BuildConfig.BASE_END_URL_iklan)
    Call<ArrayList<IklanMasyarkatModel>> getDataIklan();
    @GET(BuildConfig.BASE_END_URL_tps)
    Call<ArrayList<TpsModel>> getDataMarkerTps();
    @GET(BuildConfig.BASE_END_URL_DaftarNews)
    Call<ArrayList<DaftarNewsModel>> getDataDaftarNews();


    @FormUrlEncoded
    @POST(BuildConfig.BASE_END_URL_Post)
    Call<ResponseBody> updateDataStatusVote(@Field("action") String action,
                                    @Field("sheetName") String sheetName,
                                    @Field("nik") String nik,
                                    @Field("idhp") String idhp,
                                    @Field("status_voted") String status_voted,
                                    @Field("digital_signature") String digital_signature,
                                    @Field("foto_muka") String foto_muka);

    @FormUrlEncoded
    @POST(BuildConfig.BASE_END_URL_Post)
    Call<ResponseBody> updateProfil(@Field("action") String action,
                                    @Field("sheetName") String sheetName,
                                    @Field("nik") String nik,
                                    @Field("email") String email,
                                    @Field("tgl_lahir") String tgl_lahir,
                                    @Field("tempat_lahir") String tempat_lahir,
                                    @Field("alamat") String alamat,
                                    @Field("nohp") String nohp);
    @FormUrlEncoded
    @POST(BuildConfig.BASE_END_URL_Post)
    Call<ResponseBody> insertDataHistori(@Field("action") String action,
                                         @Field("sheetName") String sheetName,
                                         @Field("nik") String nik,
                                         @Field("nama_user") String nama_user,
                                         @Field("status_vote") String status_vote,
                                         @Field("petugas") String petugas,
                                         @Field("foto") String foto);


//    @GET("AKfycby32StuPPxG8FCUeDk1tIpRVDdYgf23RUt-utHdA8uGN_SF_zk/exec")
//    Call<ArrayList<LoginModels>> getLogin();

    @FormUrlEncoded
    @POST(BuildConfig.BASE_END_URL_Post)
    Call<ResponseBody> postRegister(@Field("nik") String nik,
                                    @Field("nama_lengkap") String nama_elngkap,
                                    @Field("email") String email,
                                    @Field("tgl_lahir") String tgl_lahir,
                                    @Field("tempat_lahir") String tempat_lahir,
                                    @Field("alamat") String alamat,
                                    @Field("wilayah") String wilayah,
                                    @Field("nohp") String nohp,
                                    @Field("jenis_kelamin") String jenis_kelamin,
                                    @Field("agama") String agama,
                                    @Field("username") String username,
                                    @Field("password") String password,
                                    @Field("foto") String foto,
                                    @Field("rule") String rule,
                                    @Field("status") String status,
                                    @Field("sheetName") String sheetName,
                                    @Field("action") String action,
                                    @Field("token") String token,
                                    @Field("petugas") String petugas
    );
    @FormUrlEncoded
    @POST(BuildConfig.BASE_END_URL_Post)
    Call<ResponseBody> postVote(@Field("name_user") String name_user,
                                @Field("nama_calon") String nama_calon,
                                @Field("nama_wakil_calon") String nama_wakil_calon,
                                @Field("provinsi_wilayah") String provinsi_wilayah,
                                @Field("status") String status,
                                @Field("sheetName") String sheetName,
                                @Field("action") String action
    );
    @FormUrlEncoded
    @POST(BuildConfig.BASE_END_URL_Post)
    Call<ResponseBody> updatePostVote(@Field("action") String action,
                                @Field("sheetName") String sheetName,
                                @Field("nik") String nama_user,
                                @Field("status_vote") String status_vote
    );
    @FormUrlEncoded
    @POST(BuildConfig.BASE_END_URL_Post)
    Call<ResponseBody> updatePostRegisterLatLong(@Field("action") String action,
                                @Field("sheetName") String sheetName,
                                @Field("nik") String nik,
                                @Field("lat") Double lat,
                                @Field("lng") Double lng
    );
    @FormUrlEncoded
    @POST(BuildConfig.BASE_END_URL_Post)
    Call<ResponseBody> updateStatusLogin(@Field("action") String action,
                                @Field("sheetName") String sheetName,
                                @Field("nik") String nik,
                                @Field("status_login") String status_login
    );

    @Multipart
    @POST("upload.php")
    Call<Result> postIMmage(@Part MultipartBody.Part image);


}

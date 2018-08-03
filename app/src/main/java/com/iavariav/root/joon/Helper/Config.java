package com.iavariav.root.joon.Helper;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.iavariav.root.joon.LoginActivity;

public final class Config {

    public static final String BASE_URL_IMAGE= "http://devlop.can.web.id/uploads/client_profile_images/3/";

    public static final String ERROR_NETWORK = "Periksa kembali jaringan anda";
    public static final String ERROR_FORM_REGISTER = "Periksa kembali form anda";
    public static final String ERROR_FORM_LOGIN = "Periksa kembali akun anda";
    public static final String ERROR_FORM_LOGIN_KURANG = "Cek akun anda";
    public static final String ERROR_NULL_DATA = "Data tidak ada";
    public static final String ERROR_ERROR_APPS_NOT_ACTIVED = "Akun Belum Aktif atau digunakan di perangkat lain";

    public static final int ZOOM_TO_LEVEL = 15;

//    bundle mulai

    public static final String BUNDLE_NEWS_DETAIL_GAMBAR = "GAMBAR_NEWS";
    public static final String BUNDLE_NEWS_DETAIL_JUDUL = "JUDUL_NEWS";
    public static final String BUNDLE_NEWS_DETAIL_CREATEDBY = "CREATEDBY_NEWS";
    public static final String BUNDLE_NEWS_DETAIL_TANGGAL = "TANGGAL_NEWS";
    public static final String BUNDLE_NEWS_DETAIL_ARTIKEL = "ARTIKEL_NEWS";

    public static final String BUNDLE_DAPIL_DETAIL_PROVINSI = "PROVINSI";
    public static final String BUNDLE_DAPIL_DETAIL_WILAYAH_ID = "WILAYAH_ID";
    public static final String BUNDLE_DAPIL_DETAIL_JABATAN = "JABATAN";
    public static final String BUNDLE_DAPIL_DETAIL_NAMA_CALON = "NAMA_CALON";
    public static final String BUNDLE_DAPIL_DETAIL_NAMA_WAKLI_CALON = "NAMA_WAKLI_CALON";
    public static final String BUNDLE_DAPIL_DETAIL_PARTAI_PENGUSUNG = "PARTAI_PENGUSUNG";
    public static final String BUNDLE_DAPIL_DETAIL_FOTO_BERDUA = "FOTO_BERDUA";
    public static final String BUNDLE_DAPIL_DETAIL_VISI = "VISI";
    public static final String BUNDLE_DAPIL_DETAIL_MISI = "MISI";
    public static final String BUNDLE_DAPIL_DETAIL_PROGRAM = "PROGRAM";
    public static final String BUNDLE_DAPIL_DETAIL_DETAILPROGRAM = "DETAILPROGRAM";
    public static final String BUNDLE_DAPIL_DETAIL_TEMPATTANGGALLAHIRKETUA = "TEMPATTANGGALLAHIRKETUA";
    public static final String BUNDLE_DAPIL_DETAIL_TEMPATTANGGALLAHIRWAKILKETUA = "TEMPATTANGGALLAHIRWAKILKETUA";
    public static final String BUNDLE_DAPIL_DETAIL_PEKERJAANKETUA = "PEKERJAANKETUA";
    public static final String BUNDLE_DAPIL_DETAIL_PEKERJAANWAKILKETUA = "PEKERJAANWAKILKETUA";
    public static final String BUNDLE_DAPIL_DETAIL_STATUSPETAHANA = "STATUSPETAHANA";

    public static final String BUNDLE_PARTAI_DETAIL_SINGKATAN = "SINGKATAN";
    public static final String BUNDLE_PARTAI_DETAIL_ALAMAT = "ALAMAT";
    public static final String BUNDLE_PARTAI_DETAIL_DATE = "DATE";
    public static final String BUNDLE_PARTAI_DETAIL_NOTARIS_NO = "NOTARIS_NO";
    public static final String BUNDLE_PARTAI_DETAIL_NOTARIS_NAMA = "NOTARIS_NAMA";
    public static final String BUNDLE_PARTAI_DETAIL_NOTARIS_TANGGAL_PENDIRIAN = "NOTARIS_TANGGAL_PENDIRIAN";
    public static final String BUNDLE_PARTAI_DETAIL_KEMKUMHAM_NO = "KEMKUMHAM_NO";
    public static final String BUNDLE_PARTAI_DETAIL_KEMKUMHAM_TANGGAL = "KEMKUMHAM_TANGGAL";
    public static final String BUNDLE_PARTAI_DETAIL_NOTELP = "NOTELP";
    public static final String BUNDLE_PARTAI_DETAIL_FAX = "FAX";
    public static final String BUNDLE_PARTAI_DETAIL_KETUM = "KETUM";
    public static final String BUNDLE_PARTAI_DETAIL_BENDUM = "BENDUM";
    public static final String BUNDLE_PARTAI_DETAIL_SEKJEN = "SEKJEN";
    public static final String BUNDLE_PARTAI_DETAIL_BANK_NO = "BANK_NO";
    public static final String BUNDLE_PARTAI_DETAIL_BANK_NAMA = "BANK_NAMA";
    public static final String BUNDLE_PARTAI_DETAIL_BANK_PEMILIK = "BANK_PEMILIK";
    public static final String BUNDLE_PARTAI_DETAIL_TANGGAL_CD = "TANGGAL_CD";
    public static final String BUNDLE_PARTAI_DETAIL_TANGGAL_HARDCOPY = "TANGGAL_HARDCOPY";
    public static final String BUNDLE_PARTAI_DETAIL_FOTO_PARTAI = "FOTO_PARTAI";
    public static final String BUNDLE_PARTAI_DETAIL_NAMA = "NAMA";
    public static final String BUNDLE_PARTAI_DETAIL_VISI = "VISI";
    public static final String BUNDLE_PARTAI_DETAIL_MISI = "MISI";

    public static final String BUNDLE_ANGGARAN_DETAIL_NAMASATKER = "NAMASATKER";
    public static final String BUNDLE_ANGGARAN_DETAIL_USULANANGGARAN = "USULANANGGARAN";
    public static final String BUNDLE_ANGGARAN_DETAIL_USULANANGGARANDISETUJUI = "USULANANGGARANDISETUJUI";
    public static final String BUNDLE_ANGGARAN_DETAIL_TERMIN1 = "TERMIN1";
    public static final String BUNDLE_ANGGARAN_DETAIL_TERMIN2 = "TERMIN2";
    public static final String BUNDLE_ANGGARAN_DETAIL_TERMIN3 = "TERMIN3";
    public static final String BUNDLE_ANGGARAN_DETAIL_TERMIN11 = "TERMIN11";
    public static final String BUNDLE_ANGGARAN_DETAIL_TERMIN22 = "TERMIN22";
    public static final String BUNDLE_ANGGARAN_DETAIL_TERMIN33 = "TERMIN33";
    public static final String BUNDLE_ANGGARAN_DETAIL_TOTAL = "TOTAL";
    public static final String BUNDLE_ANGGARAN_DETAIL_NOMORNHPD = "NOMORNHPD";
    public static final String BUNDLE_ANGGARAN_DETAIL_TANGGALNPHD = "TANGGALNPHD";

    public static final String BUNDLE_DATA_WILAYAH = "WILAYAH";

    // bundle iklan
    public static final String BUNDLE_IKLAN_JUDUL = "JUDUL";
    public static final String BUNDLE_IKLAN_SUMBER = "SUMBER";
    public static final String BUNDLE_IKLAN_LINK = "LINK";
    public static final String BUNDLE_IKLAN_GAMBAR = "GAMBAR";
    // bundle selesai iklan


    public static final String BUNDLE_ANGGARAN_JENIS = "JENISANGGARAN";
    public static final String BUNDLE_ANGGARAN_DETAIL = "DETAILANGGARAN";
    public static final String SHARED_ANGGARAN_JENIS = "SHAREDJENISANGGARAN";
    public static final String SHARED_ANGGARAN_DETAIL = "SHAREDDETAILANGGARAN";

    public static final String BUNDLE_VOTINGLOGINSAVED = "VOTINGLOGINSAVED";
//    bundle selesai

    // alarm mulai
    public static final String NOTIFICATION_ID = "NOTIFICATION_ID";
    public static final String NOTIFICATION = "NOTIFICATION";
    // alarm selesai


    //    shared mulai
    public static final String SHARED_PREF_NAME = "JOON";
    public static final String LOGGEDIN_SHARED_PREF = "logedin";
    public static final String SHARED_NAMALENGKAP = "NAMALENGKAP";
    public static final String SHARED_AGAMA = "AGAMA";
    public static final String SHARED_RULE = "RULE";
    public static final String SHARED_TGLLAHIR = "TGLLAHIR";
    public static final String SHARED_ALAMAT = "ALAMAT";
    public static final String SHARED_NIK = "NIK";
    public static final String SHARED_PASSWORD = "PASSWORD";
    public static final String SHARED_TEMPATLAHIR = "TEMPATLAHIR";
    public static final String SHARED_FOTO = "FOTO";
    public static final String SHARED_WAKTU = "WAKTU";
    public static final String SHARED_NOHP = "NOHP";
    public static final String SHARED_JENISKELAMIN = "JENISKELAMIN";
    public static final String SHARED_EMAIL = "EMAIL";
    public static final String SHARED_USERNAME = "USERNAME";
    public static final String SHARED_WILAYAH = "WILAYAH";
    public static final String SHARED_STATUSUSER= "STATUSUSER";
    public static final String SHARED_TOKEN= "TOKEN";
    public static final String SHARED_PETUGAS= "PETUGAS";

//    saving Vote
    public static final String SHARED_VOTESAVING = "VOTESAVING";
    public static final String SHARED_VOTELOGIN_SAVING = "VOTELOGINSAVING";
    public static final String SHARED_STATUSVOTE_SAVING = "STATUSVOTE";
//    selesai sacing Vote

    public static final String SHARED_HWID = "HWID";
    public static final String SHARED_DIGITAL_SIGNATURE = "DIGITALSIGNATURE";
    public static final String SHARED_FOTO_FRONT = "FOTOFRONT";
    public static final String SHARED_STATUS_VOTE = "STATUSVOTELAPORAN";
    public static final String SHARED_STATUS_APLIKASI = "STATUSAPPS";
//    shared selesai

    public static final String GET_LAT = "lat";
    public static final String GET_LONG = "long";
    public static final String GET_ALAMAT = "alamat";




    public static String formatDMY(int year, int month, int date) {
        String formattedDate = "";

        if(date < 10)   {
            formattedDate += Integer.toString(year);
        }
        else {
            formattedDate += Integer.toString(year);
        }
        formattedDate += "-";

        if(month < 10) {
            formattedDate += "0" + Integer.toString(month);
        }
        else {
            formattedDate += Integer.toString(month);
        }
        formattedDate += "-";

        formattedDate += Integer.toString(date);

        return formattedDate;
    }



    public static void forceLogout(Context context) {
        //Getting out shared preferences
        SharedPreferences preferences = context.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        //Getting editor
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Config.SHARED_NAMALENGKAP , "");
        editor.putString(Config.SHARED_AGAMA , "");
        editor.putString(Config.SHARED_RULE , "");
        editor.putString(Config.SHARED_TGLLAHIR , "");
        editor.putString(Config.SHARED_ALAMAT , "");
        editor.putString(Config.SHARED_NIK , "");
        editor.putString(Config.SHARED_PASSWORD , "");
        editor.putString(Config.SHARED_TEMPATLAHIR , "");
        editor.putString(Config.SHARED_FOTO , "");
        editor.putString(Config.SHARED_WAKTU , "");
        editor.putString(Config.SHARED_NOHP , "");
        editor.putString(Config.SHARED_JENISKELAMIN , "");
        editor.putString(Config.SHARED_EMAIL , "");
        editor.putString(Config.SHARED_USERNAME , "");
        editor.putString(Config.SHARED_WILAYAH , "");
        editor.putString(Config.SHARED_STATUSUSER, "");
        editor.putString(Config.SHARED_VOTESAVING, "");
        editor.putString(Config.SHARED_VOTELOGIN_SAVING, "");
        editor.putString(Config.SHARED_TOKEN, "");
        editor.putString(Config.SHARED_HWID, "");
        editor.putString(Config.SHARED_DIGITAL_SIGNATURE, "");
        editor.putString(Config.SHARED_STATUS_APLIKASI, "");
        editor.putString(Config.SHARED_STATUS_VOTE, "");
        editor.putString(Config.SHARED_FOTO_FRONT, "");
        //Saving the sharedpreferences
        editor.commit();

        //Starting login activity
        Intent intent = new Intent(context.getApplicationContext(), LoginActivity.class);
        context.startActivity(intent);

    }




}

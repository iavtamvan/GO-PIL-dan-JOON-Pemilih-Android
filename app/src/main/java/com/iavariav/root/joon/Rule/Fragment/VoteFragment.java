package com.iavariav.root.joon.Rule.Fragment;


import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.iavariav.root.joon.Helper.Config;
import com.iavariav.root.joon.Model.DapilModels;
import com.iavariav.root.joon.R;
import com.iavariav.root.joon.Rest.GAS.ApiServiceGas;
import com.iavariav.root.joon.Rest.GAS.ClientGas;
import com.iavariav.root.joon.Rest.UploadImage.Result;
import com.iavariav.root.joon.Rest.UploadImage.RetroClient;
import com.iavariav.root.joon.Rule.HomeActivity;
import com.iavariav.root.joon.Service.CameraPreview;
import com.simplify.ink.InkView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class VoteFragment extends Fragment implements
        TextToSpeech.OnInitListener {


    private LinearLayout div;
    private ImageView ivResultLaporanAkhir;
    private String nama_lengkap;
    private String wilayah;
    private String nik;
    private String imagePath,imagePathLaporan, h, k;


    private AlertDialog.Builder builder;
    private LayoutInflater inflater;
    private ArrayList<DapilModels> dapilModels;
    private int MY_PERMISSIONS_REQUEST_READ_EXTERNAL = 1;
    private final static int CAMERA_PIC_REQUEST1 = 0;
    private String hwId;

    private Camera mCamera;
    private CameraPreview mPreview;
    private FrameLayout camera_preview;
    static Context con;


    private TextToSpeech tts;


    public VoteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_vote, container, false);
        initView(view);

        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getActivity(), "Otw Permisi", Toast.LENGTH_SHORT).show();
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                Toast.makeText(getActivity(), "Permisi sukses", Toast.LENGTH_SHORT).show();
                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_READ_EXTERNAL);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
        tts = new TextToSpeech(getActivity(), this);



        hwId = Settings.Secure.getString(getContext().getContentResolver(),
                Settings.Secure.ANDROID_ID);


        SharedPreferences sp = getActivity().getSharedPreferences(Config.SHARED_PREF_NAME, MODE_PRIVATE);
        nama_lengkap = sp.getString(Config.SHARED_NAMALENGKAP, "");
        wilayah = sp.getString(Config.SHARED_WILAYAH, "");
        nik = sp.getString(Config.SHARED_NIK, "");

        dapilModels = new ArrayList<>();
        getdataVOte();


        return view;
    }


    private void getdataVOte() {
        final ProgressDialog loading = ProgressDialog.show(getActivity(), "Loading", "Mengambil data paslon...", false, false);
        ApiServiceGas apiServiceGas = ClientGas.getInstanceRetrofit();
        Call<ArrayList<DapilModels>> call = apiServiceGas.getDataDapil();
        call.enqueue(new Callback<ArrayList<DapilModels>>() {
            @Override
            public void onResponse(Call<ArrayList<DapilModels>> call, Response<ArrayList<DapilModels>> response) {
                dapilModels = response.body();

                for (final DapilModels s : dapilModels) {
                    if (s.getProvinsi() != null && s.getProvinsi().contains(wilayah)) {
                        final String fotoBersama = s.getFotoBerdua();
                        final String namaKetua = s.getNamaCalon();
                        final String namaWakilKetua = s.getNamaWakliCalon();
                        final String jabatan = s.getJabatan();
                        final String provinsi = s.getProvinsi();
                        final String partaiPengusung = s.getPartaiPengusung();
                        final String visi = s.getVisi();
                        final String misi = s.getMisi();
                        final String program = s.getProgram();

                        LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        View view1 = layoutInflater.inflate(R.layout.row_gopil_vote, null);


                        final CircleImageView cvVoteImage = view1.findViewById(R.id.cvVoteImage);
                        Glide.with(getActivity()).load(fotoBersama).error(R.drawable.logo).fitCenter().into(cvVoteImage);


                        final TextView tvVoteNamaKetuaPejabat = view1.findViewById(R.id.tvVoteNamaKetuaPejabat);
                        tvVoteNamaKetuaPejabat.setText(namaKetua);


                        final TextView tvVoteNamaWakilPejabat = view1.findViewById(R.id.tvVoteNamaWakilPejabat);
                        tvVoteNamaWakilPejabat.setText(namaWakilKetua);


                        final TextView tvVoteJabatan = view1.findViewById(R.id.tvVoteJabatan);
                        tvVoteJabatan.setText(jabatan);

                        final RadioButton rbVoteID = view1.findViewById(R.id.rbVoteID);

                        final CardView cvKlikVote = view1.findViewById(R.id.cvKlikVote);

                        final LinearLayout containerVote = view1.findViewById(R.id.containerVote);
//                        containerVote.setVisibility(View.GONE);


                        div.addView(view1);

                        loading.dismiss();

                        rbVoteID.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                div.setVisibility(View.GONE);
                                mCamera.takePicture(null, null, mPicture);


                                builder = new AlertDialog.Builder(getActivity());
                                inflater = LayoutInflater.from(getActivity());
                                final View dialView = inflater.inflate(R.layout.dialog_box_detail_vote, null);

                                final CircleImageView cvDialogDetailVote = dialView.findViewById(R.id.cvDialogDetailVote);
                                Glide.with(getActivity()).load(fotoBersama).error(R.drawable.logo).into(cvDialogDetailVote);

                                final TextView tvDialogDetailVoteNamaKetua = dialView.findViewById(R.id.tvDialogDetailVoteNamaKetua);
                                tvDialogDetailVoteNamaKetua.setText(namaKetua);

                                final TextView tvDialogDetailVoteWakilNamaKetua = dialView.findViewById(R.id.tvDialogDetailVoteWakilNamaKetua);
                                tvDialogDetailVoteWakilNamaKetua.setText(namaWakilKetua);

//                                tts.speak("Anda memilih pasangan " + namaKetua + " " + namaWakilKetua, TextToSpeech.QUEUE_FLUSH, null);


                                final TextView tvDialogDetailVoteProvinsi = dialView.findViewById(R.id.tvDialogDetailVoteProvinsi);
                                tvDialogDetailVoteProvinsi.setText("Provinsi : " + provinsi);

                                final TextView tvDialogDetailVotepartaiPengusung = dialView.findViewById(R.id.tvDialogDetailVotepartaiPengusung);
                                tvDialogDetailVotepartaiPengusung.setText("Partai pengusung : " + partaiPengusung);

                                final TextView tvDialogDetailVoteJabatan = dialView.findViewById(R.id.tvDialogDetailVoteJabatan);
                                tvDialogDetailVoteJabatan.setText(jabatan);

                                final TextView tvDialogDetailVoteVisi = dialView.findViewById(R.id.tvDialogDetailVoteVisi);
                                tvDialogDetailVoteVisi.setText("Visi : " + visi);

                                final TextView tvDialogDetailVoteMisi = dialView.findViewById(R.id.tvDialogDetailVoteMisi);
                                tvDialogDetailVoteMisi.setText("Misi : " + misi);

                                final TextView tvDialogBoxVotePertanyan = dialView.findViewById(R.id.tvDialogBoxVotePertanyan);
                                tvDialogBoxVotePertanyan.setText("Apakah anda yakin memilih paslon tersebut? " + namaKetua + " Silahkan tanda tangan dibawah ini.");

                                final InkView ink = dialView.findViewById(R.id.ink);
                                ink.setColor(getResources().getColor(android.R.color.holo_orange_light));
                                ink.setMinStrokeWidth(1.5f);
                                ink.setMaxStrokeWidth(6f);

                                final ImageView ivSignatureErase = dialView.findViewById(R.id.ivSignatureErase);
                                ivSignatureErase.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        ink.clear();
                                    }
                                });

                                final ImageView ivSignature = dialView.findViewById(R.id.ivSignature);
                                final View viewDialogVote = dialView.findViewById(R.id.viewDialogVote);

                                final FrameLayout camera_preview = dialView.findViewById(R.id.camera_preview);
                                final Button button_capture = dialView.findViewById(R.id.button_capture);

//                                camera_preview.addView(mPreview);

                                button_capture.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        mCamera.takePicture(null, null, mPicture);
                                    }
                                });

                                final Button btnDialogBoxYA = dialView.findViewById(R.id.btnDialogBoxYA);
                                btnDialogBoxYA.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
//                                        button_capture.performClick();
//                                        if (mPicture.toString().isEmpty()){
//                                            button_capture.performClick();
//                                        }else {

                                            Bitmap drawing = ink.getBitmap(android.R.color.white);
                                            ivSignature.setImageBitmap(drawing);

                                            ivResultLaporanAkhir.setDrawingCacheEnabled(true);
                                            Bitmap bitmap = ivResultLaporanAkhir.getDrawingCache();
//                                        Bitmap bitmap = ivResultLaporanAkhir.getDrawingCache();

                                            // CALL THIS METHOD TO GET THE URI FROM THE BITMAP
//                                        Uri tempUri = getImageUri(getActivity(), drawing);
                                            Uri tempUriSignature = getImageUri(getActivity(), drawing);
                                            Uri tempUriFotoFront = getImageUriLaporan(getActivity(), bitmap);

                                            // CALL THIS METHOD TO GET THE ACTUAL PATH
                                            File finalFileSignature = new File(getRealPathFromURI(tempUriSignature));
                                            File finalFileFotoFront = new File(getRealPathFromURILaporan(tempUriFotoFront));

//                                        }
                                        final ProgressDialog loading = ProgressDialog.show(getActivity(), "Loading", "Mengirim data vote...", false, false);

                                        rbVoteID.isChecked();

                                        ApiServiceGas apiGas = ClientGas.getInstanceRetrofit();
                                        apiGas.postVote(
                                                nama_lengkap, tvDialogDetailVoteNamaKetua.getText().toString().trim(),
                                                tvDialogDetailVoteWakilNamaKetua.getText().toString().trim(),
                                                provinsi,
                                                "1",
                                                "vote",
                                                "insertDataVote"
                                        )
                                                .enqueue(new Callback<ResponseBody>() {
                                                    @Override
                                                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                                        if (response.isSuccessful()) {
                                                            try {
//                                                                loading.dismiss();
                                                                JSONObject object1 = new JSONObject(response.body().string());
                                                                Toast.makeText(getActivity(), "Vote telah berasil direkam", Toast.LENGTH_SHORT).show();
                                                                //Creating a shared preference
                                                                SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                                                                //Creating editor to store values to shared preferences
                                                                SharedPreferences.Editor editor = sharedPreferences.edit();

                                                                //Adding values to editor
                                                                editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, true);
                                                                editor.putString(Config.SHARED_VOTESAVING, tvDialogDetailVoteNamaKetua.getText().toString().trim());

                                                                editor.commit();
                                                                postDataHistory();
//                                                                startActivity(new Intent(getActivity(), HomeActivity.class));
                                                            } catch (JSONException e) {
                                                                e.printStackTrace();
                                                            } catch (IOException e) {
                                                                e.printStackTrace();
                                                            }
                                                        }
                                                    }

                                                    @Override
                                                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                                                        loading.dismiss();
                                                        Toast.makeText(getActivity(), "" + Config.ERROR_NETWORK, Toast.LENGTH_SHORT).show();
                                                    }
                                                });

                                        ApiServiceGas apiServiceGasUpdateDataVote = ClientGas.getInstanceRetrofit();
                                        apiServiceGasUpdateDataVote.updateDataStatusVote(
                                                "updateDataStatusVote",
                                                "register",
                                                nik,
                                                hwId,
                                                "Telah Memilih",
                                                Config.BASE_URL_IMAGE + h,
                                                Config.BASE_URL_IMAGE + k
                                        ).enqueue(new Callback<ResponseBody>() {
                                            @Override
                                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                                if (response.isSuccessful()) {
                                                    String statusVote = "Telah Memilih";
                                                    //Creating a shared preference
                                                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                                                    //Creating editor to store values to shared preferences
                                                    SharedPreferences.Editor editor = sharedPreferences.edit();

                                                    //Adding values to editor
                                                    editor.putString(Config.SHARED_HWID, hwId);
                                                    editor.putString(Config.SHARED_DIGITAL_SIGNATURE, Config.BASE_URL_IMAGE + h);
                                                    editor.putString(Config.SHARED_FOTO_FRONT, Config.BASE_URL_IMAGE + k);
                                                    editor.putString(Config.SHARED_STATUS_APLIKASI, "Aktif");
                                                    editor.putString(Config.SHARED_STATUS_VOTE, statusVote);

                                                    editor.commit();
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                                Toast.makeText(getActivity(), "" + Config.ERROR_NETWORK, Toast.LENGTH_SHORT).show();
                                            }
                                        });


                                    }
                                });

                                final AlertDialog show = builder.show();
                                final Button btnDialogBoxTidak = dialView.findViewById(R.id.btnDialogBoxTidak);
                                btnDialogBoxTidak.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        // Create our Preview view and set it as the content of our activity.
                                        startActivity(new Intent(getActivity(), HomeActivity.class));

                                    }
                                });


                                builder.setView(dialView);
                                builder.show();
                                builder.setCancelable(false);
                                builder.setOnKeyListener(new DialogInterface.OnKeyListener() {
                                    @Override
                                    public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                                        return false;
                                    }
                                });

                            }
                        });


                        cvKlikVote.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {


                                builder = new AlertDialog.Builder(getActivity());
                                inflater = LayoutInflater.from(getActivity());
                                final View dialView = inflater.inflate(R.layout.dialog_box_detail_vote, null);

                                final CircleImageView cvDialogDetailVote = dialView.findViewById(R.id.cvDialogDetailVote);
                                Glide.with(getActivity()).load(fotoBersama).error(R.drawable.logo).into(cvDialogDetailVote);

                                final TextView tvDialogDetailVoteNamaKetua = dialView.findViewById(R.id.tvDialogDetailVoteNamaKetua);
                                tvDialogDetailVoteNamaKetua.setText(namaKetua);

                                final TextView tvDialogDetailVoteWakilNamaKetua = dialView.findViewById(R.id.tvDialogDetailVoteWakilNamaKetua);
                                tvDialogDetailVoteWakilNamaKetua.setText(namaWakilKetua);
                                tts.speak("Anda memilih pasangan " + namaKetua + " " + namaWakilKetua, TextToSpeech.QUEUE_FLUSH, null);

                                final TextView tvDialogDetailVoteProvinsi = dialView.findViewById(R.id.tvDialogDetailVoteProvinsi);
                                tvDialogDetailVoteProvinsi.setText("Provinsi : " + provinsi);

                                final TextView tvDialogDetailVotepartaiPengusung = dialView.findViewById(R.id.tvDialogDetailVotepartaiPengusung);
                                tvDialogDetailVotepartaiPengusung.setText("Partai Pengusung : " + partaiPengusung);

                                final TextView tvDialogDetailVoteJabatan = dialView.findViewById(R.id.tvDialogDetailVoteJabatan);
                                tvDialogDetailVoteJabatan.setText(jabatan);

                                final TextView tvDialogDetailVoteVisi = dialView.findViewById(R.id.tvDialogDetailVoteVisi);
                                tvDialogDetailVoteVisi.setText("Visi : " + visi);

                                final TextView tvDialogDetailVoteMisi = dialView.findViewById(R.id.tvDialogDetailVoteMisi);
                                tvDialogDetailVoteMisi.setText("Misi : " + misi);

                                final TextView tvDialogBoxVotePertanyan = dialView.findViewById(R.id.tvDialogBoxVotePertanyan);
                                tvDialogBoxVotePertanyan.setVisibility(View.GONE);
                                tvDialogBoxVotePertanyan.setText("Apakah anda yakin memilih paslon tersebut? " + namaKetua);

                                final CardView cvDetail = dialView.findViewById(R.id.cvDetail);
                                cvDetail.setVisibility(View.GONE);

                                final InkView ink = dialView.findViewById(R.id.ink);
                                ink.setVisibility(View.GONE);

                                final ImageView ivSignature = dialView.findViewById(R.id.ivSignature);
                                ivSignature.setVisibility(View.GONE);

                                final Button btnDialogBoxYA = dialView.findViewById(R.id.btnDialogBoxYA);
                                btnDialogBoxYA.setVisibility(View.GONE);

                                final View viewDialogVote = dialView.findViewById(R.id.viewDialogVote);
                                viewDialogVote.setVisibility(View.GONE);

                                final ImageView ivSignatureErase = dialView.findViewById(R.id.ivSignatureErase);
                                ivSignatureErase.setVisibility(View.GONE);
//                                btnDialogBoxYA.setOnClickListener(new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View view) {
//                                        final ProgressDialog loading = ProgressDialog.show(getActivity(), "Loading", "Mengirim data vote...", false, false);
//                                        rbVoteID.isChecked();
//
//                                        ApiServiceGas apiGas = ClientGas.getInstanceRetrofit();
//                                        apiGas.postVote(
//                                                nama_lengkap, tvDialogDetailVoteNamaKetua.getText().toString().trim(),
//                                                tvDialogDetailVoteWakilNamaKetua.getText().toString().trim(),
//                                                provinsi,
//                                                "1",
//                                                "vote",
//                                                "insertDataVote"
//                                        )
//                                                .enqueue(new Callback<ResponseBody>() {
//                                                    @Override
//                                                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                                                        if (response.isSuccessful()) {
//                                                            try {
////                                                                loading.dismiss();
//                                                                JSONObject object1 = new JSONObject(response.body().string());
//                                                                Toast.makeText(getActivity(), "Vote telah berasil direkam", Toast.LENGTH_SHORT).show();
//                                                                //Creating a shared preference
//                                                                SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
//
//                                                                //Creating editor to store values to shared preferences
//                                                                SharedPreferences.Editor editor = sharedPreferences.edit();
//
//                                                                //Adding values to editor
//                                                                editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, true);
//                                                                editor.putString(Config.SHARED_VOTESAVING, tvDialogDetailVoteNamaKetua.getText().toString().trim());
//
//                                                                editor.commit();
//                                                                postDataHistory();
////                                                                startActivity(new Intent(getActivity(), HomeActivity.class));
//                                                            } catch (JSONException e) {
//                                                                e.printStackTrace();
//                                                            } catch (IOException e) {
//                                                                e.printStackTrace();
//                                                            }
//                                                        }
//                                                    }
//
//                                                    @Override
//                                                    public void onFailure(Call<ResponseBody> call, Throwable t) {
//                                                        loading.dismiss();
//                                                        Toast.makeText(getActivity(), "" + Config.ERROR_NETWORK, Toast.LENGTH_SHORT).show();
//                                                    }
//                                                });
//                                    }
//                                });

                                final AlertDialog show = builder.show();
                                final Button btnDialogBoxTidak = dialView.findViewById(R.id.btnDialogBoxTidak);
                                btnDialogBoxTidak.setVisibility(View.GONE);

                                btnDialogBoxTidak.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
//                                        getdataVOte();
//                                        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
//                                            @Override
//                                            public void onClick(DialogInterface dialogInterface, int i) {
//                                                dialogInterface.cancel();
//                                            }
//                                        });
                                        startActivity(new Intent(getActivity(), HomeActivity.class));

                                    }
                                });


                                builder.setView(dialView);
                                builder.show();
                                builder.setCancelable(false);
                                builder.setOnKeyListener(new DialogInterface.OnKeyListener() {
                                    @Override
                                    public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                                        return false;
                                    }
                                });

                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<DapilModels>> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(getActivity(), "" + Config.ERROR_NETWORK, Toast.LENGTH_SHORT).show();
            }
        });

    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getActivity().getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        imagePath = cursor.getString(idx);
        h = new File(imagePath).getName();
//        cursor.close();
        uploadImage();
        return cursor.getString(idx);
    }

    public Uri getImageUriLaporan(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public String getRealPathFromURILaporan(Uri uri) {
        Cursor cursor = getActivity().getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx1 = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        imagePathLaporan = cursor.getString(idx1);
        k = new File(imagePathLaporan).getName();

        uploadImageLaporan();
        return cursor.getString(idx1);
    }

    private void uploadImage() {

        final ProgressDialog p;
        p = new ProgressDialog(getActivity());
        p.setMessage("Proses Upload Foto");
        p.show();

        ApiServiceGas s = RetroClient.getService();

        File f = new File(imagePath);


        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), f);

        final MultipartBody.Part part = MultipartBody.Part.createFormData("uploaded_file", f.getName(), requestFile);
        Call<Result> resultCAll = s.postIMmage(part);
        resultCAll.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {

                p.dismiss();
                if (response.isSuccessful()) {
                    if (response.body().getResult().equals("success"))
                        Toast.makeText(getActivity(), "Sukses", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getActivity(), "Upload Gambar Gagal", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Upload Gambar Gagal", Toast.LENGTH_SHORT).show();
                }

                imagePath = "";

            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Toast.makeText(getActivity(), "Gagal Upload Fail", Toast.LENGTH_SHORT).show();
                p.dismiss();


            }
        });
    }
    private void uploadImageLaporan() {

        ApiServiceGas s = RetroClient.getService();

        File f = new File(imagePathLaporan);


        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), f);

        final MultipartBody.Part part = MultipartBody.Part.createFormData("uploaded_file", f.getName(), requestFile);
        Call<Result> resultCAll = s.postIMmage(part);
        resultCAll.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {

                if (response.isSuccessful()) {
                    if (response.body().getResult().equals("success"))
                        Toast.makeText(getActivity(), "Sukses", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getActivity(), "Upload Gambar Gagal", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Upload Gambar Gagal", Toast.LENGTH_SHORT).show();
                }

                imagePathLaporan = "";

            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Toast.makeText(getActivity(), "Gagal Upload Fail", Toast.LENGTH_SHORT).show();


            }
        });
    }

    private void postDataHistory() {

        ApiServiceGas serviceGas = ClientGas.getInstanceRetrofit();
        serviceGas.updatePostVote(
                "updateDataVote",
                "histori",
                nik,
                "voted"
        ).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    SharedPreferences spSaving = getActivity().getSharedPreferences(Config.SHARED_PREF_NAME, MODE_PRIVATE);
                    SharedPreferences.Editor editor = spSaving.edit();

                    editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, true);
                    editor.putString(Config.SHARED_VOTELOGIN_SAVING, "voted");
                    editor.putString(Config.SHARED_STATUSVOTE_SAVING, "null");

                    editor.commit();

                    startActivity(new Intent(getActivity(), HomeActivity.class));
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getActivity(), "" + Config.ERROR_NETWORK, Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Mulai Camera

    Bitmap bitmap;
    private Camera.PictureCallback mPicture = new Camera.PictureCallback() {

        @Override
        public void onPictureTaken(byte[] data, Camera camera) {

            System.gc();
            bitmap = null;
            BitmapWorkerTask task = new BitmapWorkerTask(data);
            task.execute(0);
        }
    };

    @Override
    public void onInit(int i) {
        if (i == TextToSpeech.SUCCESS) {
            Locale localeID = new Locale("in", "ID");
            int result = tts.setLanguage(localeID);

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            }

        } else {
            Log.e("TTS", "Initilization Failed!");
        }
    }

    class BitmapWorkerTask extends AsyncTask<Integer, Void, Bitmap> {
        private final WeakReference<byte[]> dataf;
        private int data = 0;

        public BitmapWorkerTask(byte[] imgdata) {
            // Use a WeakReference to ensure the ImageView can be garbage
            // collected
            dataf = new WeakReference<byte[]>(imgdata);
        }

        // Decode image in background.
        @Override
        protected Bitmap doInBackground(Integer... params) {
            data = params[0];
            ResultActivity(dataf.get());
            return mainbitmap;
        }

        // Once complete, see if ImageView is still around and set bitmap.
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            saving();
//            if (mainbitmap != null) {
//
//                Intent i = new Intent();
//                i.putExtra("BitmapImage", mainbitmap);
//                setResult(-1, i);
//                // Here I am Setting the Requestcode 1, you can put according to
//                // your requirement
////                finish(); // ini buat ngelempar ke activity sebelumnya
//
//            }
        }
    }
    int requestCode;
    private void saving() {
        if (requestCode == CAMERA_PIC_REQUEST1){
            ivResultLaporanAkhir.setImageBitmap(mainbitmap);
        }
        else {
            Toast.makeText(getActivity(), "Foto Galat", Toast.LENGTH_SHORT).show();
        }
    }

    Bitmap mainbitmap;

    public void ResultActivity(byte[] data) {
        mainbitmap = null;
        mainbitmap = decodeSampledBitmapFromResource(data, 1000, 1000);
        mainbitmap=RotateBitmap(mainbitmap,270);
        mainbitmap=flip(mainbitmap);
    }

    public static Bitmap decodeSampledBitmapFromResource(byte[] data,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        // BitmapFactory.decodeResource(res, resId, options);
        BitmapFactory.decodeByteArray(data, 0, data.length, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth,
                reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeByteArray(data, 0, data.length, options);
    }

    public static int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            if (width > height) {
                inSampleSize = Math.round((float) height / (float) reqHeight);
            } else {
                inSampleSize = Math.round((float) width / (float) reqWidth);
            }
        }
        return inSampleSize;
    }

    /** A safe way to get an instance of the Camera object. */
    public static Camera getCameraInstance() {
        Camera c = null;
        Log.d("No of cameras", Camera.getNumberOfCameras() + "");
        for (int camNo = 0; camNo < Camera.getNumberOfCameras(); camNo++) {
            Camera.CameraInfo camInfo = new Camera.CameraInfo();
            Camera.getCameraInfo(camNo, camInfo);

            if (camInfo.facing == (Camera.CameraInfo.CAMERA_FACING_FRONT)) {
                c = Camera.open(camNo);
                c.setDisplayOrientation(90);
            }
        }
        return c; // returns null if camera is unavailable
    }

    private void releaseCamera() {
        if (mCamera != null) {
            mCamera.release(); // release the camera for other applications
            mCamera = null;
        }
    }

    // rotate the bitmap to portrait
    public static Bitmap RotateBitmap(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(),
                source.getHeight(), matrix, true);
    }

    //the front camera displays the mirror image, we should flip it to its original
    Bitmap flip(Bitmap d)
    {
        Matrix m = new Matrix();
        m.preScale(-1, 1);
        Bitmap src = d;
        Bitmap dst = Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), m, false);
        dst.setDensity(DisplayMetrics.DENSITY_DEFAULT);
        return dst;
    }


    // Selesai Camera


    @Override
    public void onResume() {
        super.onResume();
        if (mCamera == null){
            con = getActivity();
            try {
                mCamera = getCameraInstance();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            // Create our Preview view and set it as the content of our activity.
            mPreview = new CameraPreview(getActivity(), mCamera);
            camera_preview.addView(mPreview);
        }



    }

    @Override
    public void onPause() {
        super.onPause();
        releaseCamera();
    }



    private void initView(View view) {
        div = (LinearLayout) view.findViewById(R.id.div);
        ivResultLaporanAkhir = view.findViewById(R.id.ivResult);
        camera_preview = view.findViewById(R.id.camera_preview);
    }

}

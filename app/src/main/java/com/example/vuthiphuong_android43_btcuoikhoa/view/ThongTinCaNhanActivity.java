package com.example.vuthiphuong_android43_btcuoikhoa.view;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.vuthiphuong_android43_btcuoikhoa.MainActivity;
import com.example.vuthiphuong_android43_btcuoikhoa.R;
import com.example.vuthiphuong_android43_btcuoikhoa.fragment.PersonFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.io.IOException;

public class ThongTinCaNhanActivity extends AppCompatActivity {
    public static final int MY_REQUEST_CODE = 10;
//    Context context;
    ImageView imgAvatar;
    EditText etFullName, etEmail, etAddress;
    Button btnUpdate, btnBackTTCN;
    Uri uri;
    MainActivity mainActivity;
    PersonFragment personFragment;
    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result.getResultCode() == RESULT_OK){
                Intent intent = result.getData();
                if(intent == null){
                    return;
                }
                Uri uri = intent.getData();
                setUri(uri);
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    setBitmapImageView(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();

                }
            }
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_ca_nhan);
        imgAvatar = findViewById(R.id.img_avatar);
        etFullName = findViewById(R.id.et_fullName);
        etEmail = findViewById(R.id.et_Email);
       // etAddress = findViewById(R.id.et_Address);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnBackTTCN = findViewById(R.id.btnBackTTCN);
        //mainActivity = (MainActivity) getApplicationContext();
        btnBackTTCN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        setUserInformation();
        initListener();
    }

    private void setUserInformation(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null){
            return;
        }
        etFullName.setText(user.getDisplayName());
        etEmail.setText(user.getEmail());
       // etAddress.setText(user.getPhotoUrl().toString());
       // etAddress.setText(user.getPhotoUrl().toString());
        Glide.with(getApplication()).load(user.getPhotoUrl()).error(R.drawable.ic_avatar_default).into(imgAvatar);
    }

    private void initListener() {
        imgAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //mainActivity.onClickRequestPermission();
                onClickRequestPermission();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickUpdate();
            }
        });
    }
//    private void onClickRequestPermission(){
//        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
//            MainActivity.openGallery();
//            return;
//        }
//        if(getBaseContext().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
//            mainActivity.openGallery();
//        }
//        else {
//            String [] permisstions = {Manifest.permission.READ_EXTERNAL_STORAGE};
//            requestPermissions(permisstions, MY_REQUEST_CODE);
//        }
//    }

    public void setBitmapImageView(Bitmap bitmapImageView){
        imgAvatar.setImageBitmap(bitmapImageView);
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    private void onClickUpdate(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null){
            return;
        }
        String strFullName = etFullName.getText().toString().trim();
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(strFullName)
                .setPhotoUri(uri)
                .build();

        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplication(), "Cập nhật thành công", Toast.LENGTH_LONG).show();
//                              mainActivity.showUserInformation();
//                              personFragment.showUserInformation();
                            Intent intent = new Intent(getBaseContext(), MainActivity.class);
                            startActivity(intent);
                        }
                    }
                });
    }

        public void onClickRequestPermission(){
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            openGallery();
            return;
        }
        if(getBaseContext().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            openGallery();
            return;
        }
        else {
        String [] permisstions = {Manifest.permission.READ_EXTERNAL_STORAGE};
        requestPermissions(permisstions, MY_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == MY_REQUEST_CODE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                openGallery();
            }
        }
    }

    public void openGallery(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        activityResultLauncher.launch(Intent.createChooser(intent, "Select Picture"));
    }
}
package com.example.vuthiphuong_android43_btcuoikhoa.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vuthiphuong_android43_btcuoikhoa.MainActivity;
import com.example.vuthiphuong_android43_btcuoikhoa.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity{
    EditText etEmailSignup, etPasswordSignup, etConfirmPasswordSignup;
    Button btnSignup;
    ProgressDialog progressDialog;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        etEmailSignup = findViewById(R.id.etEmailSignup);
        etPasswordSignup = findViewById(R.id.etPasswordSignup);
        etConfirmPasswordSignup = findViewById(R.id.etConfirmPasswordSignup);
        btnSignup = findViewById(R.id.btnSignup);
        progressDialog = new ProgressDialog(this);

        initListener();
    }

    private void initListener(){
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSignUp();
            }
        });
    }

    private void onClickSignUp(){
        String strEmail = etEmailSignup.getText().toString().trim();
        String strPassword = etPasswordSignup.getText().toString().trim();
        String strConfirmpassword = etConfirmPasswordSignup.getText().toString().trim();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        progressDialog.show();
        if(strEmail.trim().length() == 0){
            Toast.makeText(this, "Vui lòng nhập email", Toast.LENGTH_SHORT).show();
        }
        else if(strPassword.trim().length()== 0){
            Toast.makeText(this, "Vui lòng nhập password", Toast.LENGTH_SHORT).show();
        }
        else if(!strConfirmpassword.equals(strPassword)){
            Toast.makeText(this, "Nhập lại mật khẩu", Toast.LENGTH_SHORT).show();
        }
        else {
            auth.createUserWithEmailAndPassword(strEmail, strPassword)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressDialog.dismiss();
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Toast.makeText(SignupActivity.this, "Đăng ký thành công",
                                        Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                                startActivity(intent);
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                firebaseDatabase = FirebaseDatabase.getInstance();
                                databaseReference = firebaseDatabase.getReference();
                                databaseReference.child("GioHang").child(user.getUid()).setValue("id_giohang");
                                databaseReference.child("DiaChiNhanHang").child(user.getUid()).setValue("id_giohang");
                                databaseReference.child("DonDangGiao").child(user.getUid()).setValue("id_dondanggiao");
                                databaseReference.child("DonDaMua").child(user.getUid()).setValue("id_dondamua");

                                finishAffinity();
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(SignupActivity.this, "Đăng ký thất bại",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }

    }


//    @Override
//    public void onClick(View v) {
//        progressDialog.setMessage("Đang xử lý");
//        progressDialog.setIndeterminate(true);
//        progressDialog.show();
//
//        String username = etUsernameSignup.getText().toString();
//        String password = etPasswordSignup.getText().toString();
//        String confirmpassword = etConfimPasswordSignup.getText().toString();
//        String error = getString(R.string.thongbaoloidangky);
//
//        if(username.trim().length() == 0){
//            error += "username";
//            Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
//        }
//        else if(password.trim().length()== 0){
//            error += "password";
//            Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
//        }
//        else if(!confirmpassword.equals(password)){
//            Toast.makeText(this, "Nhập lại mật khẩu", Toast.LENGTH_SHORT).show();
//        }
//        else {
//            firebaseAuth.createUserWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                @Override
//                public void onComplete(@NonNull Task<AuthResult> task) {
//                    progressDialog.dismiss();
//                    Toast.makeText(SignupActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
//    }
}
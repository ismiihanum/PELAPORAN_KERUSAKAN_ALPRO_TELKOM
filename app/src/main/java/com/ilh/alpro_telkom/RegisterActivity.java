package com.ilh.alpro_telkom;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ilh.alpro_telkom.model.ResponseErrorModel;
import com.ilh.alpro_telkom.rest.ApiConfigServer;
import com.ilh.alpro_telkom.rest.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private EditText edtNamaLengkap;
    private EditText edtUsername;
    private EditText edtPassword;
    private RadioButton rbPelapor;
    private RadioButton rbValidator;
    private RadioButton rbTeknisi;
    private Button btnRegistrasi;

    private ResponseErrorModel responseErrorModel;

    private String rule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        postData();
    }

    private void postData() {
        ApiService apiService = ApiConfigServer.getApiService();
        apiService.register(edtNamaLengkap.getText().toString().trim(), edtUsername.getText().toString().trim(), edtPassword.getText().toString().trim(),rule)
                .enqueue(new Callback<ResponseErrorModel>() {
                    @Override
                    public void onResponse(Call<ResponseErrorModel> call, Response<ResponseErrorModel> response) {
                        if (response.isSuccessful()){
                            responseErrorModel = response.body();
                            Toast.makeText(RegisterActivity.this, "" + responseErrorModel.getErrorMsg(), Toast.LENGTH_SHORT).show();
                            finishAffinity();
                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        }
                    }
                    @Override
                    public void onFailure(Call<ResponseErrorModel> call, Throwable t) {
                        Toast.makeText(RegisterActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void initView() {
        edtNamaLengkap = findViewById(R.id.edt_nama_lengkap);
        edtUsername = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password);
        rbPelapor = findViewById(R.id.rb_pelapor);
        rbValidator = findViewById(R.id.rb_validator);
        rbTeknisi = findViewById(R.id.rb_teknisi);
        btnRegistrasi = findViewById(R.id.btn_registrasi);
    }
}

package com.ilh.alpro_telkom;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ilh.alpro_telkom.model.ResponseErrorModel;
import com.ilh.alpro_telkom.rest.ApiConfigServer;
import com.ilh.alpro_telkom.rest.ApiService;
import com.ilh.alpro_telkom.ui.pelapor.PelaporActivity;
import com.ilh.alpro_telkom.ui.teknisi.TeknisiActivity;
import com.ilh.alpro_telkom.ui.validator.ValidatorActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText edtUsername;
    private EditText edtPassword;
    private Button btnLogin;

    private ResponseErrorModel responseErrorModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginRule();
            }
        });
    }

    private void loginRule() {
        ApiService apiService = ApiConfigServer.getApiService();
        apiService.login(edtUsername.getText().toString().trim(), edtPassword.getText().toString().trim()).enqueue(new Callback<ResponseErrorModel>() {
            @Override
            public void onResponse(Call<ResponseErrorModel> call, Response<ResponseErrorModel> response) {
                if (response.isSuccessful()){
                    responseErrorModel = response.body();
                    Toast.makeText(LoginActivity.this, "" + responseErrorModel.getRule(), Toast.LENGTH_SHORT).show();

                    if (responseErrorModel.getRule().contains("validator")){
                        finishAffinity();
                        startActivity(new Intent(getApplicationContext(), ValidatorActivity.class));
                    } else if (responseErrorModel.getRule().contains("teknisi")){
                        finishAffinity();
                        startActivity(new Intent(getApplicationContext(), TeknisiActivity.class));
                    } else if (responseErrorModel.getRule().contains("user")){
                        finishAffinity();
                        startActivity(new Intent(getApplicationContext(), PelaporActivity.class));
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseErrorModel> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        edtUsername = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password);
        btnLogin = findViewById(R.id.btn_login);
    }
}

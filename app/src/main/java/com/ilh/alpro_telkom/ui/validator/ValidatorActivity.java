package com.ilh.alpro_telkom.ui.validator;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ilh.alpro_telkom.R;
import com.ilh.alpro_telkom.adapter.ValidatorAdapter;
import com.ilh.alpro_telkom.model.PelaporModel;
import com.ilh.alpro_telkom.rest.ApiConfigServer;
import com.ilh.alpro_telkom.rest.ApiService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ValidatorActivity extends AppCompatActivity {

    private RecyclerView rv;
    private ArrayList<PelaporModel> pelaporModels;
    private ValidatorAdapter validatorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validator);
        initView();
        pelaporModels = new ArrayList<>();
        getData();
    }

    public void getData() {
        ApiService apiService = ApiConfigServer.getApiService();
        apiService.getAllData().enqueue(new Callback<ArrayList<PelaporModel>>() {
            @Override
            public void onResponse(Call<ArrayList<PelaporModel>> call, Response<ArrayList<PelaporModel>> response) {
                if (response.isSuccessful()){
                    pelaporModels = response.body();
                    rv.setLayoutManager(new LinearLayoutManager(ValidatorActivity.this));
                    validatorAdapter = new ValidatorAdapter(ValidatorActivity.this, pelaporModels);
                    validatorAdapter.notifyDataSetChanged();
                    rv.setAdapter(validatorAdapter);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<PelaporModel>> call, Throwable t) {
                Toast.makeText(ValidatorActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        rv = findViewById(R.id.rv);
    }
}

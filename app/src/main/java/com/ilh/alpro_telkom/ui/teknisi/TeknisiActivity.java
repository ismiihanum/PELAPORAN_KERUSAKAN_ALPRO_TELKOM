package com.ilh.alpro_telkom.ui.teknisi;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.ilh.alpro_telkom.R;
import com.ilh.alpro_telkom.helper.Config;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

public class TeknisiActivity extends AppCompatActivity {

    private TextView tvKeluar;
    private TextView tvUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teknisi);
        initView();
        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        tvUsername.setText(sharedPreferences.getString(Config.SHARED_PREF_USERNAME, ""));
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                TeknisiActivity.this.getSupportFragmentManager(), FragmentPagerItems.with(TeknisiActivity.this)
                .add("PEKERJAAN", PekerjaanFragment.class)
                .add("Histori", HistoryTeknisiFragment.class)
                .create());

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);

        SmartTabLayout viewPagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);
        viewPagerTab.setViewPager(viewPager);

        tvKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();
                Config.logout(TeknisiActivity.this);
            }
        });

    }

    private void initView() {
        tvKeluar = findViewById(R.id.tv_keluar);
        tvUsername = findViewById(R.id.tv_username);
    }
}

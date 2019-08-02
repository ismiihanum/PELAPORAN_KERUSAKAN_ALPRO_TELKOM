package com.ilh.alpro_telkom.ui.teknisi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.ilh.alpro_telkom.R;
import com.ilh.alpro_telkom.ui.validator.DataPelaporFragment;
import com.ilh.alpro_telkom.ui.validator.HistoryValidatorFragment;
import com.ilh.alpro_telkom.ui.validator.ValidatorActivity;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

public class TeknisiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teknisi);


        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                TeknisiActivity.this.getSupportFragmentManager(), FragmentPagerItems.with(TeknisiActivity.this)
                .add("DATA", DataPelaporFragment.class)
                .add("Histori", HistoryValidatorFragment.class)
                .create());

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);

        SmartTabLayout viewPagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);
        viewPagerTab.setViewPager(viewPager);
    }
}

package cn.mrfish.module04_aac_databinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import java.util.Date;

import cn.mrfish.module04_aac_databinding.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding =  DataBindingUtil.setContentView(this,
                R.layout.activity_main);
        binding.setTime(new Date());

    }
}

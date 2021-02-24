package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
public class MainActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new StudentListFragment();
    }

}
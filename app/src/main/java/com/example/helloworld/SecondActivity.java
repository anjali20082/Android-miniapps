package com.example.helloworld;

import android.content.Context;
import android.content.Intent;
import androidx.fragment.app.Fragment;
import java.util.UUID;

public class SecondActivity extends SingleFragmentActivity {

    public static final String EXTRA_CRIME_ID =

            "com.example.helloworld.crime_id";
    @Override
    protected Fragment createFragment() {
        return new StudentDetailsFragment();
//        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);
//        //int crimeId
//        return StudentDetailsFragment.newInstance(crimeId);
    }

    public static Intent newIntent(Context packageContext, UUID crimeId) { Intent
            intent = new Intent(packageContext,SecondActivity. class);
        intent.putExtra( EXTRA_CRIME_ID, crimeId);
        return intent;
    }

}

package com.shym.front_end.ui.bienvenueClient;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.shym.front_end.R;

public class BienvenueClientActivity extends AppCompatActivity {

    Button bienv_next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenue_client);

        /*bienv_next = (Button) findViewById(R.id.bienv_next);
        FragmentManager fragmentManager = getSupportFragmentManager();
        ButtonClickListener listener = new ButtonClickListener(this, fragmentManager);
        bienv_next.setOnClickListener(listener);*/

    }
}
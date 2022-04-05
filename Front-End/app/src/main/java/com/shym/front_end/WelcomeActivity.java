package com.shym.front_end;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.shym.front_end.databinding.ActivityWelcomeBinding;

public class WelcomeActivity extends AppCompatActivity {
    private Button start;
    ActivityWelcomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityWelcomeBinding.inflate(getLayoutInflater());


        setContentView(binding.getRoot());
        start= (Button) findViewById(R.id.bienvenueButton);


        binding.bienvenueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                GoToRegister();
            }
        });


    };

    public void GoToRegister() {
        Intent  intent=new Intent(this , RegisterActivity.class);
        startActivity(intent);

    };
}
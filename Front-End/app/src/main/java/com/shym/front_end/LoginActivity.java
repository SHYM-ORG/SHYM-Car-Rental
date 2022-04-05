package com.shym.front_end;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.shym.front_end.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;

    private EditText email;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLoginBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        email=binding.email;
        password=binding.password;
        binding.buttonregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                GoToRegister();
            }
        });
        binding.buttonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String txt_email=email.getText().toString();
                String txt_password=password.getText().toString();


                LoginUser(txt_email,txt_password);
            }
        });
    }
    public void GoToRegister() {
        Intent intent=new Intent(this , RegisterActivity.class);
        startActivity(intent);

    };
    public void LoginUser(String email,String password){
        Toast.makeText(this,email  + password, Toast.LENGTH_LONG).show();
        Intent intent=new Intent(this , MainActivity.class);
        startActivity(intent);
    }
}
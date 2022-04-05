package com.shym.front_end;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.shym.front_end.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding binding;
    private EditText fullname;
    private EditText email;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityRegisterBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        fullname=binding.username;
        email=binding.email;
        password=binding.password;


        binding.buttonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                GoToLogin();
            }
        });
        binding.buttonregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_fullname=fullname.getText().toString();
                String txt_email=email.getText().toString();
                String txt_password=password.getText().toString();


                RegisterUser(txt_fullname,txt_email,txt_password);
            }
        });
    }
    public void GoToLogin() {
        Intent intent=new Intent(this , LoginActivity.class);
        startActivity(intent);

    };
    public void RegisterUser(String fullname,String email,String password){
        Toast.makeText(this,email + fullname + password, Toast.LENGTH_LONG).show();
        Intent intent=new Intent(this , MainActivity.class);
        startActivity(intent);
    }
}
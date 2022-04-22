package com.shym.front_end;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.shym.front_end.Fragments.AgencyProfileFragment;
import com.shym.front_end.Fragments.ContactUsFragment;
import com.shym.front_end.Fragments.EditProfileFragment;
import com.shym.front_end.Fragments.HomeFragment;
import com.shym.front_end.Fragments.RegisterAgencyFragment;
import com.shym.front_end.Fragments.RegisterClientFragment;
import com.shym.front_end.Fragments.RentalFragment;
import com.shym.front_end.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

     ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences sharedPref = this.getSharedPreferences("auth", this.MODE_PRIVATE);
        SharedPreferences.Editor ed = sharedPref.edit();
        ed.putString("token", "null");
        ed.commit();
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        //this hides the action bar
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());

        binding.navView.setOnItemSelectedListener(item ->{

switch(item.getItemId()){

    case  R.id.home:
        replaceFragment(new HomeFragment());
        break;
    case  R.id.rental:
        replaceFragment(new RentalFragment());

        break;
    case  R.id.profile:
        replaceFragment(new AgencyProfileFragment());

        break;
}
                    return true;
        }

        );
    }


    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_right);
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }

    public void goToRegisterClient(View view) {
        replaceFragment(new RegisterClientFragment());
    }
    public void goToRegisterAgency(View view) {
        replaceFragment(new RegisterAgencyFragment());
    }
    public void goToLogin (View view) {
        replaceFragment(new AgencyProfileFragment());
    }
    public void goToContactUs(View view) { replaceFragment(new ContactUsFragment()); }


}
package com.shym.front_end;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.shym.front_end.Fragments.HomeFragment;
import com.shym.front_end.Fragments.ProfileFragment;
import com.shym.front_end.Fragments.RentalFragment;
import com.shym.front_end.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

     ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        //this hides the action bar

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragement(new HomeFragment());

        binding.navView.setOnItemSelectedListener(item ->{



switch(item.getItemId()){

    case  R.id.home:
        replaceFragement(new HomeFragment());
        break;
    case  R.id.rental:
        replaceFragement(new RentalFragment());

        break;
    case  R.id.profile:
        replaceFragement(new ProfileFragment());

        break;
}
                    return true;
        }

        );
    }


    private void replaceFragement(Fragment fragment){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();

    }

}
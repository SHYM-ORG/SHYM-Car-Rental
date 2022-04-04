package com.shym.front_end.ui.bienvenueClient;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.shym.front_end.R;
import com.shym.front_end.ui.bienvenueAgency.BienvenueAgencyActivity;

public class ButtonClickListener implements View.OnClickListener {

    Context context;
    /*FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    public ButtonClickListener (Context context, FragmentManager fragmentManager) {
        this.context = context;
        this.fragmentManager = fragmentManager;
    }*/
    public ButtonClickListener (Context context) {
        this.context = context;
    }
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case  R.id.bienvenueAgence: {
                Intent intent = new Intent((Activity)context, BienvenueAgencyActivity.class);
                context.startActivity(intent);
                break;
            }
            case R.id.bienvenueClient: {
                Intent intent = new Intent((Activity)context, BienvenueClientActivity.class);
                context.startActivity(intent);
                break;
            }
            case R.id.bienv_next: {
                /*fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right);
                fragmentTransaction.replace(R.id.bienvenueFragmentClient, new ModelPreferenceFragment());
                fragmentTransaction.commit();*/
                break;
            }
            case R.id.model_pref_next: {
                /*fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right);
                fragmentTransaction.replace(R.id.bienvenueFragmentClient, new CatPreferenceFragment());
                fragmentTransaction.commit();*/
                break;
            }
        }
    }
}

package com.shym.front_end.ui.bienvenueClient;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.shym.front_end.R;
import com.shym.front_end.ui.bienvenueAgency.BienvenueAgencyActivity;

public class ButtonClickListener implements View.OnClickListener {

    Context context;
    public ButtonClickListener (Context context) {
        this.context = context;
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case  R.id.bienvenueAgence: {
                Toast.makeText(context, "agence", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent((Activity)context, BienvenueAgencyActivity.class);
                context.startActivity(intent);
            }
            case R.id.bienvenueClient: {

            }
        }
    }
}

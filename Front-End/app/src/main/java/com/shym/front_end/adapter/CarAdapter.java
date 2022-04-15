package com.shym.front_end.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shym.front_end.models.Car;
import com.shym.front_end.R;


import com.squareup.picasso.Picasso;

import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.ViewHolder> {
    @Override
    public int getItemCount() {
        return mCars.size();
    }
    private Context mContext;
    private List<Car> mCars;

    public CarAdapter(Context mContext, List<Car> mCars) {
        this.mContext = mContext;
        this.mCars = mCars;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.car, parent, false);
        return new CarAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        final Car car = mCars.get(position);
        Picasso.get().load(car.getImage()).into(holder.imagecar);
        holder.carmodel.setText(car.getModel());
        holder.carplace.setText(car.getPlace());


    }





    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imagecar;
        public Button carbutton;


        public TextView carmodel;
        public TextView carplace;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imagecar = itemView.findViewById(R.id.carpicture);
            carbutton = itemView.findViewById(R.id.carbutton);
            carmodel = itemView.findViewById(R.id.carmodel);
            carplace = itemView.findViewById(R.id.carplace);


        }
    }
}



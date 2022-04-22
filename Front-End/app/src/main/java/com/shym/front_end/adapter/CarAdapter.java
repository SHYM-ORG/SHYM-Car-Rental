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


import com.shym.front_end.utils.VolleyUtils;
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
        Picasso.get().load(VolleyUtils.getImageUrl(car.getImage())).into(holder.carImage);
        holder.carModel.setText(car.getModel());
        holder.carSeries.setText(car.getSeries());
        holder.carPricePerDay.setText(Integer.toString(car.getPricePerDay()) + " DH");
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView carImage;
        public Button carButton;
        public TextView carModel;
        public TextView carSeries;
        public TextView carPricePerDay;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            carImage = itemView.findViewById(R.id.carpicture);
            carButton = itemView.findViewById(R.id.carbutton);
            carModel = itemView.findViewById(R.id.carmodel);
            carSeries = itemView.findViewById(R.id.carseries);
            carPricePerDay = itemView.findViewById(R.id.carpriceperday);
        }
    }
}



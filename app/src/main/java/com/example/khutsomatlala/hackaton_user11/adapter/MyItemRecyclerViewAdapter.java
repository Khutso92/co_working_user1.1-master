package com.example.khutsomatlala.hackaton_user11.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.khutsomatlala.hackaton_user11.PlaceDetailsActivity;
import com.example.khutsomatlala.hackaton_user11.R;
import com.example.khutsomatlala.hackaton_user11.model.PlacePicture;
import com.example.khutsomatlala.hackaton_user11.model.WorkingSpace;

import java.util.List;

import static com.example.khutsomatlala.hackaton_user11.R.id.image;

public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private final List<WorkingSpace> mValues;
    private Activity activity;

    public MyItemRecyclerViewAdapter(List<WorkingSpace> items, Activity activity) {
        this.mValues = items;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.working_spaces_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);

        System.out.println(mValues.get(position).getPlaceDetails().getPlaceName() + " " + mValues.get(position).getPlaceDetails().getPlaceHours());

        //Assigning data
        Glide.with(activity).load(mValues.get(position).getPictures().get(0).getImageUrl());
        holder.hours.setText(mValues.get(position).getPlaceDetails().getPlaceHours());
        holder.price.setText("R" + mValues.get(position).getPlaceDetails().getPrice());
        holder.address.setText(mValues.get(position).getPlaceDetails().getPlaceAddress());
        holder.placeName.setText(mValues.get(position).getPlaceDetails().getPlaceName());

   holder.feat1.setText(mValues.get(position).getFeatures().get(0).getTitle().toString());
      holder.feat2.setText(mValues.get(position).getFeatures().get(1).getTitle().toString());
         holder.feat3.setText(mValues.get(position).getFeatures().get(2).getTitle().toString());





        Glide.with(activity).load(mValues.get(position).getPictures().get(0).getImageUrl()).into(holder.pic);

       // System.out.println(mValues.get(position).getPictures().get(0).getImageUrl());


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(activity, PlaceDetailsActivity.class);

                String lat = mValues.get(position).getPlaceDetails().getPlaceLatitude();
                String lon = mValues.get(position).getPlaceDetails().getPlaceLongitude();
                String name = mValues.get(position).getPlaceDetails().getPlaceName();
                String call = mValues.get(position).getPlaceDetails().getPlaceCell();
                String hours = mValues.get(position).getPlaceDetails().getPlaceHours();
                String address = mValues.get(position).getPlaceDetails().getPlaceAddress();
                String infor = mValues.get(position).getPlaceDetails().getPlaceInfo();
                String pic1 = mValues.get(position).getPictures().get(0).getImageUrl();
                String email = mValues.get(position).getPlaceDetails().getPlaceWebsite();
                String price = Long.toString(mValues.get(position).getPlaceDetails().getPrice());

                System.out.println("pic 1 - " + pic1);

                intent.putExtra("lat", lat);
                intent.putExtra("lon", lon);
                intent.putExtra("name", name);
                intent.putExtra("call", call);
                intent.putExtra("infor", infor);
                intent.putExtra("hours", hours);
                intent.putExtra("address", address);
                intent.putExtra("pic1", pic1);
                intent.putExtra("price", price);
                intent.putExtra("email", email);
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;

        public ImageView pic;
        public TextView hours;
        public TextView price;
        public TextView address;
        public TextView placeName;
        public TextView feat1;
        public TextView feat2;
        public TextView feat3;

        public WorkingSpace mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;

            pic = view.findViewById(R.id.wsPic);
            hours = view.findViewById(R.id.wsHours);
            price = view.findViewById(R.id.wsPrice);
            address = view.findViewById(R.id.wsAddress);
            placeName = view.findViewById(R.id.wsName);
            feat1 = view.findViewById(R.id.wsFeat1);
            feat2 = view.findViewById(R.id.wsFeat2);
            feat3= view.findViewById(R.id.wsFeat3);

        }

        @Override
        public String toString() {
            return super.toString() + " '" + placeName.getText() + "'";
        }
    }
}

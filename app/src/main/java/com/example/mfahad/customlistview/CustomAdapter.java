package com.example.mfahad.customlistview;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Transformation;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by M.FAHAD on 7/31/2017.
 */

public class CustomAdapter extends BaseAdapter {

    Context context ;
    ArrayList<Employee> arrayList;
    private ImageView image;
    public CustomAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<Employee> objects) {
//        super(context, resource, objects);
        this.context = context;
        this.arrayList = objects;
    }



    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View list_view_item = null;
        if(convertView == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            list_view_item = layoutInflater.inflate(R.layout.list_view_item,null);
        }
        else{
            list_view_item = convertView;
        }

        Employee emp = arrayList.get(position);
        ((TextView)list_view_item.findViewById(R.id.fname)).setText(emp.getFirstName());
        ((TextView)list_view_item.findViewById(R.id.lname)).setText(emp.getLastName());
        ((TextView)list_view_item.findViewById(R.id.designation)).setText(emp.getDesignation());
        image = (ImageView)list_view_item.findViewById(R.id.imageView);

        Glide.with(context).load(R.drawable.android).asBitmap().centerCrop().into(new BitmapImageViewTarget(image) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                image.setImageDrawable(circularBitmapDrawable);
            }
        });

        return list_view_item;
    }
}

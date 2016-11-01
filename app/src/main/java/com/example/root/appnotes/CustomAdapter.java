package com.example.root.appnotes;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;
import java.util.Random;
import java.util.zip.Inflater;

/**
 * Created by root on 01/11/16.
 *//*

public class CustomAdapter extends ArrayAdapter {



    public CustomAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
        {
            LayoutInflater viewInflater;
            viewInflater = LayoutInflater.from(getContext());
            convertView = viewInflater.inflate(R.layout.textviewlayout, null);
        }

        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        convertView.setBackgroundColor(color);

        return convertView;
    }
}*/
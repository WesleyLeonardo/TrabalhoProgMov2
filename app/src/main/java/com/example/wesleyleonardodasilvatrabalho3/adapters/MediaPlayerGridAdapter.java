package com.example.wesleyleonardodasilvatrabalho3.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class MediaPlayerGridAdapter extends BaseAdapter {
    Context context;
    int[] list;

    public MediaPlayerGridAdapter(Context context, int[] list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.length;
    }

    @Override
    public Object getItem(int position) {
        return list[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ImageView image = new ImageView(context);
        image.setImageResource(list[position]);
        image.setLayoutParams(new ViewGroup.LayoutParams(300,300));
        image.setScaleType(ImageView.ScaleType.CENTER_CROP);
        image.setPadding(3,3,3,3);
        return image;
    }
}

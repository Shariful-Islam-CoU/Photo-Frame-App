package com.example.photoframe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class CustomAdapter extends BaseAdapter {

    int[] photos;
    Context context;
    LayoutInflater layoutInflater;

    CustomAdapter(Context context,int[] photos){
        this.context=context;
        this.photos= photos;
    }


    @Override
    public int getCount() {
        return photos.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
         layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

          view=  layoutInflater.inflate(R.layout.sample,viewGroup,false);
        }

      ImageView imageView= view.findViewById(R.id.imageviewId);
        imageView.setImageResource(photos[i]);

        return view;
    }
}

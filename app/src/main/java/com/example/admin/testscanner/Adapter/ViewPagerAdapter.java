package com.example.admin.testscanner.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.admin.testscanner.R;
import com.example.admin.testscanner.ScrollingActivity;
import com.example.admin.testscanner.model.viewpagermodel;

import java.util.ArrayList;

import static com.example.admin.testscanner.ScrollingActivity.EXTRA_DATA;


public class ViewPagerAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<viewpagermodel> data ;
   // private Integer [] images = {R.drawable.bkbl,R.drawable.butterfly,R.drawable.bkbl,R.drawable.butterfly};

    public ViewPagerAdapter(Context context, ArrayList<viewpagermodel> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.row_viewpager, null);
        final viewpagermodel model = data.get(position);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(context, ScrollingActivity.class);
                intent.putExtra(EXTRA_DATA,data.get(position));
                context.startActivity(intent);
            }
        });
        Glide.with(context)
             /*   .load(images[position])*/
                .load(model.getResource())
                .crossFade()
                .placeholder(R.color.color10pink)
                .into(imageView);
        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);
        return view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);

    }
}
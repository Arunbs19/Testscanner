package com.example.admin.testscanner;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.testscanner.Adapter.ViewPagerAdapter;
import com.example.admin.testscanner.model.viewpagermodel;
import com.example.admin.testscanner.utility.ExpandingViewPagerTransformer;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    LinearLayout sliderDotspanel;
    private int dotscount;
    private ImageView[] dots;
    ArrayList<viewpagermodel> lst;
    Button btn_btn;
    TextView txttitlebar;
    TextView txt_title;
    TextView txt_subtitle;
    ViewGroup.LayoutParams layoutparams;
    ActionBar actionbar;
    int ci=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actionbarinit();
        init();
        viewpagerinit();

      }

    private void init() {
        try {

            viewPager = (ViewPager) findViewById(R.id.viewPager);
            txt_title=(TextView)findViewById(R.id.txt_title);
            txt_subtitle=(TextView)findViewById(R.id.txt_subtitle);
            sliderDotspanel = (LinearLayout) findViewById(R.id.SliderDots);
            btn_btn = (Button) findViewById(R.id.btn_btn);

            lst =new  ArrayList<viewpagermodel>();
            lst.add( new viewpagermodel("1", "hby", "annamalai", "rajini kanth", R.drawable.butterfly));
            lst.add(new viewpagermodel("2", "hgygys", "basha", "rajini anthony", R.drawable.bkbl));
            lst.add(new viewpagermodel("3", "hgygys", "chinna counter", "vijay kanth", R.drawable.butterfly));
            lst.add(new viewpagermodel("4", "hgygys", "dharmadhurai", "vijaysethupathi", R.drawable.bkbl));


            btn_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent =  new Intent(MainActivity.this, ScrollingActivity.class);
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                    startActivity(intent);
                }
            });

        } catch (Exception ex) {
            Log.e("Main init", ex.getMessage().toString());
        }

    }

    private void actionbarinit() {
        try {
            actionbar = getSupportActionBar();
            actionbar.setDisplayHomeAsUpEnabled(true);

            txttitlebar =new  TextView(this);
            layoutparams =new  ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            txttitlebar.setLayoutParams(layoutparams);
            txttitlebar.setText("Welcome");
            txttitlebar.setTextColor(Color.WHITE);
            txttitlebar.setSingleLine(true);
            txttitlebar.setTypeface(null, Typeface.BOLD);
            txttitlebar.setGravity(Gravity.CENTER);
            txttitlebar.setTextSize(20f);
            actionbar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            actionbar.setCustomView(txttitlebar);
        } catch (Exception ex) {

            Log.e("Main actionbarinit", ex.getMessage().toString());
        }

    }

    private void viewpagerinit() {

        try {
            //  differentDensityAndScreenSize(getApplicationContext());
            ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(MainActivity.this,lst);

            viewPager.setAdapter(viewPagerAdapter);
            viewPager.setClipToPadding(false);

//automovement
            java.util.Timer timer;
            timer=new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    viewPager.post(new Runnable() {
                        @Override
                        public void run() {

                            Log.d("viewPager",""+ci);
                            viewPager.setCurrentItem(ci%4);
                            ci++;

                        }
                    });
                }
            },1000,3000);


            dotscount = viewPagerAdapter.getCount();
            dots = new ImageView[dotscount];

            for(int i = 0; i < dotscount; i++){
                dots[i] = new ImageView(this);
                dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.non_active_dot));
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(8, 0, 8, 0);
                sliderDotspanel.addView(dots[i], params);

            }
            viewpagermodel model = lst.get(0);
            txt_title.setText(model.getTitle());
            txt_subtitle.setText(model.getSubtitle());
            dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    for(int i = 0; i< dotscount; i++){
                        dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.non_active_dot));
                    }
                    viewpagermodel model = lst.get(position);
                    txt_title.setText(model.getTitle());
                    txt_subtitle.setText(model.getSubtitle());
                    dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
            viewPager.setPageTransformer(true, new ExpandingViewPagerTransformer());
            viewPager.setPadding(80, 0, 80, 0);
            viewPager.setAdapter(viewPagerAdapter);

        }catch (Exception ex){
            Log.e("viewpagerinit",ex.getMessage().toString());
        }


    }
}

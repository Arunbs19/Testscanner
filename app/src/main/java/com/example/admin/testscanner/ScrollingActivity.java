package com.example.admin.testscanner;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import com.example.admin.testscanner.model.viewpagermodel;

import com.bumptech.glide.Glide;

public class ScrollingActivity extends AppCompatActivity {

    ImageView imgheader;
    public static final String EXTRA_DATA = "extradata";
    private viewpagermodel datamodel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        try {
            if (getIntent().hasExtra(EXTRA_DATA)) {
                datamodel = getIntent().getParcelableExtra(EXTRA_DATA);
            } else {
                throw new IllegalArgumentException("Detail activity must receive a movie parcelable");
            }


        }catch (Exception ex){
            Log.e("scroll oncreate",ex.getMessage().toString());
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

       // getSupportActionBar().setIcon(R.mipmap.ic_launcher);
       // getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("Avengers: Age");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "floatingbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });
        init();
    }

    private void init() {
        try {

            imgheader =(ImageView)findViewById(R.id.imgheader);
            Glide.with(getApplicationContext())
                    /*   .load(images[position])*/
                    .load(datamodel.getResource())
                    .crossFade()
                    .placeholder(R.color.color10pink)
                    .into(imgheader);


        }catch (Exception ex){
            Log.e("scroll init",ex.getMessage().toString());
        }

    }
}

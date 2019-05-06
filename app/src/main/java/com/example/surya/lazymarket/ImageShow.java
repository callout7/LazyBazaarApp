package com.example.surya.lazymarket;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.jsibbold.zoomage.ZoomageView;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.URL;

import jp.wasabeef.blurry.Blurry;
import jp.wasabeef.glide.transformations.BlurTransformation;
import uk.co.senab.photoview.PhotoViewAttacher;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

public class ImageShow extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_show);

        ZoomageView imageShow = findViewById(R.id.imageShow);
        final RelativeLayout layout =(RelativeLayout)findViewById(R.id.imageShowLayout);
//        layout.setBackground(ContextCompat.getDrawable(this, R.drawable.ready));

        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra("imageUri");
        Glide.with(this).load(imageUrl)
                .apply(bitmapTransform(new BlurTransformation(22, 3))).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                layout.setBackground(resource);
            }
        });
        Picasso.get()
                .load(imageUrl)
                .placeholder(R.mipmap.cc)
                .fit()
                .into(imageShow);
    }
}

package com.skyland.zimuzutv.zimuzutv.Util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.util.logging.Logger;

/**
 * Created by skyland on 2016/12/24.
 */

public class MyImageGetter implements Html.ImageGetter {

    private URLDrawable urlDrawable = null;
    private TextView textView;
    private Context context;

    public MyImageGetter(Context context, TextView textView) {
        this.textView = textView;
        this.context = context;
    }

    @Override
    public Drawable getDrawable(final String source) {
        urlDrawable = new URLDrawable();

        Glide.with(context).load(source).asBitmap().fitCenter().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                urlDrawable.bitmap = resource;
                urlDrawable.setBounds(0, 0, resource.getWidth(), resource.getHeight());
                textView.invalidate();
                textView.setText(textView.getText());//不加这句显示不出来图片，原因不详
            }
        });
        return urlDrawable;
    }

    public class URLDrawable extends BitmapDrawable {
        public Bitmap bitmap;

        @Override
        public void draw(Canvas canvas) {
            super.draw(canvas);
            if (bitmap != null) {
                canvas.drawBitmap(bitmap, 0, 0, getPaint());
            }
        }
    }
}

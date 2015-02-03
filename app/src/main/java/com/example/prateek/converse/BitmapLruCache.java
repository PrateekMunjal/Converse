package com.example.prateek.converse;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by prateek on 3/2/15.
 */
public class BitmapLruCache extends LruCache<String,Bitmap> implements ImageLoader.ImageCache{


    public BitmapLruCache(int maxSize) {
        super(maxSize);
    }

    @Override
    public Bitmap getBitmap(String s) {
        return null;
    }

    @Override
    public void putBitmap(String s, Bitmap bitmap) {

    }
}

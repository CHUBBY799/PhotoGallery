package com.zepeng.photogallery;

import android.graphics.Bitmap;
import android.util.Log;
import android.util.LruCache;

public class ImageCache {
    private static final String TAG = "PhotoGalleryFragment";
    private LruCache<String,Bitmap> mLruCache;

    public ImageCache(){
        long maxMemory = Runtime.getRuntime().maxMemory();
        Log.d(TAG, "ImageCache: maxMemory : "+maxMemory);
        int cacheSize = (int) (maxMemory / 8);
        mLruCache = new LruCache<String, Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();
            }
        };
    }

    public void addBitmapToMemoryCache(String key,Bitmap bitmap){
        Log.d(TAG, "before add cache size : "+mLruCache.size() );
        if(getBitmapFromMemoryCache(key) == null){
            mLruCache.put(key,bitmap);
        }
        Log.d(TAG, "after add cache size : "+mLruCache.size() );
    }

    public Bitmap getBitmapFromMemoryCache(String key){
        return mLruCache.get(key);
    }

    public void removeBitmapFromMemoryCache(String key){
        mLruCache.remove(key);
    }
}

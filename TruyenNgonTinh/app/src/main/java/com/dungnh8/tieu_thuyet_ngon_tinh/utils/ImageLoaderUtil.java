package com.dungnh8.tieu_thuyet_ngon_tinh.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;

import com.dungnh8.tieu_thuyet_ngon_tinh.R;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

import java.io.File;

public class ImageLoaderUtil {
    private static final String TAG = ImageLoaderUtil.class.getSimpleName();

    public static File getCachedDir(Context context) {
        File cachedDir;
        if (Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
            cachedDir = new File(Environment.getExternalStorageDirectory(),
                context.getString(R.string.app_name));
        } else {
            cachedDir = context.getCacheDir();
        }
        if (!cachedDir.exists()) {
            cachedDir.mkdirs();
        }
        return cachedDir;
    }

    public static ImageLoaderConfiguration getConfiguration(Context context, int memory) {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
            .diskCacheExtraOptions(960, 960, null)
            .memoryCacheExtraOptions(200, 200)
            .threadPoolSize(4)
            .threadPriority(Thread.NORM_PRIORITY)
            .tasksProcessingOrder(QueueProcessingType.LIFO) // default
            .denyCacheImageMultipleSizesInMemory()
            .memoryCache(new LruMemoryCache(memory))
            .diskCache(new UnlimitedDiscCache(getCachedDir(context))) // default
            .imageDownloader(new BaseImageDownloader(context)) // default
            .writeDebugLogs()
            .defaultDisplayImageOptions(getDisplayOptions())
            .build();
        return config;
    }

    public static DisplayImageOptions getDisplayOptions() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
            .cacheInMemory(true)
            .cacheOnDisk(true)
            .bitmapConfig(Bitmap.Config.RGB_565)
            .imageScaleType(ImageScaleType.EXACTLY)
            .build();
        return options;
    }
}
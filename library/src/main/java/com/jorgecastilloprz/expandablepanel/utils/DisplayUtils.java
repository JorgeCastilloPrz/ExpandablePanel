package com.jorgecastilloprz.expandablepanel.utils;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by jorge on 5/15/14.
 */
public class DisplayUtils {

    public static int getDisplayHeight(Context context) {

        Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        Point size = new Point();

        //Para lidiar con APIs mas antiguas
        int height;
        try {
            display.getRealSize(size);
            height = size.y;
        } catch (NoSuchMethodError e) {
            height = display.getHeight();
        }

        return height;
    }
}

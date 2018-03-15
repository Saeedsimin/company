package com.example.shehnepours.taxam.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by shehnepour.s on 11/13/2017.
 */

public class FontsHandeller {
    // Defining different fonts

    private static Typeface byekanTypeface;
    private static Typeface sansRegTypeface;
    private static Typeface sansBlackTypeface;
    private static Typeface sansBoldTypeface;
    private static Typeface sansLightTypeface;
    private static Typeface sansMediumTypeface;
    private static Typeface sansUltraTypeface;

    public static Typeface setFont(Context context, String type){
        byekanTypeface = Typeface.createFromAsset(context.getAssets(),"fonts/byekan.ttf");
//        sansRegTypeface = Typeface.createFromAsset(context.getAssets(),"fonts/IRANSans.ttf");
        sansBlackTypeface = Typeface.createFromAsset(context.getAssets(),"fonts/IRANSans_Black.ttf");
        sansBoldTypeface = Typeface.createFromAsset(context.getAssets(),"fonts/IRANSans_Bold.ttf");
        sansLightTypeface = Typeface.createFromAsset(context.getAssets(),"fonts/IRANSans_Light.ttf");
        sansMediumTypeface = Typeface.createFromAsset(context.getAssets(),"fonts/IRANSans_Medium.ttf");
        sansUltraTypeface = Typeface.createFromAsset(context.getAssets(),"fonts/IRANSans_UltraLight.ttf");



        if (type.equals("byekan")) {
            return byekanTypeface;
        }
//        else if (type.equals("sans")) {
//            return sansRegTypeface;
//        }
        else if(type.equals("sans_blk")) {
            return sansBlackTypeface;
        } else if(type.equals("sans_bld")) {
            return sansBoldTypeface;
        } else if(type.equals("sans_lit")) {
            return sansLightTypeface;
        } else if(type.equals("sans_med")) {
            return sansMediumTypeface;
        } else if(type.equals("sans_ult")) {
            return sansUltraTypeface;
        }


        return sansRegTypeface;
    }

}

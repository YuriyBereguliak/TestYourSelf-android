package ua.edu.nulp.testyourself.ui.adapters;

import android.databinding.BindingAdapter;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

/**
 * TestYourSelf-android
 * Created by Yuriy Bereguliak on 12/12/17.
 */

public class ImageBindingAdapter {

    @BindingAdapter({"bind:image"})
    public static void loadImage(ImageView imageView, byte[] bytes) {
        imageView.setImageBitmap(BitmapFactory.decodeByteArray(bytes, 0, 0));
    }
}

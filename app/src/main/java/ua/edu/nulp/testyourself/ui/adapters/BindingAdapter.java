package ua.edu.nulp.testyourself.ui.adapters;

import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;

/**
 * TestYourSelf-android
 * Created by Yuriy Bereguliak on 12/12/17.
 */

public class BindingAdapter {

    @android.databinding.BindingAdapter({"bind:image"})
    public static void loadImage(ImageView imageView, byte[] bytes) {
        if (bytes == null) {
            return;
        }
        imageView.setImageBitmap(BitmapFactory.decodeByteArray(bytes, 0, 0));
    }

    @android.databinding.BindingAdapter("bind:visible")
    public static void showHide(View view, boolean show) {
        view.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}

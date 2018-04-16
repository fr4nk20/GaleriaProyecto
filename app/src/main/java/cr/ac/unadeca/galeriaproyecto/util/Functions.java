package cr.ac.unadeca.galeriaproyecto.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import cr.ac.unadeca.galeriaproyecto.R;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * Created by pc on 11/4/2018.
 */

public class Functions {
    private static RequestOptions options = new RequestOptions()
            .fitCenter()
            .placeholder(R.mipmap.ic_launcher_round)
            .error(R.mipmap.ic_launcher)
            .override(300, 500)
            .fitCenter()
            .diskCacheStrategy(DiskCacheStrategy.ALL);

    public static void loadImage(String image, ImageView imagePrev, Context context) {
        Glide.with(context)
                .load(image)
                .apply(options)
                .transition(withCrossFade())
                .into(imagePrev);
    }
    public static void loadImage(File image, ImageView imagePrev, Context context) {
        Glide.with(context)
                .load(image)
                .apply(options)
                .transition(withCrossFade())
                .into(imagePrev);
    }
    public static void loadImage( ImageView imagePrev, Context context) {
        Glide.with(context)
                .load(R.mipmap.ic_launcher_round)
                .apply(options)
                .transition(withCrossFade())
                .into(imagePrev);

    }
    public static String md5(String s) {

        return digest(s, "MD5");
    }

    private static char[] hextable = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    public static String byteArrayToHex(byte[] array) {
        String s = "";
        for (int i = 0; i < array.length; ++i) {
            int di = (array[i] + 256) & 0xFF; // Make it unsigned
            s = s + hextable[(di >> 4) & 0xF] + hextable[di & 0xF];
        }
        return s;
    }
    public static String digest(String s, String algorithm) {
        MessageDigest m = null;
        try {
            m = MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return s;
        }
        m.update(s.getBytes(), 0, s.length());
        return byteArrayToHex(m.digest());
    }

}

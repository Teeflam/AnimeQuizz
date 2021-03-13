package com.example.animequizz;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class AsyncBitmapDownloader extends AsyncTask<String,Void, Bitmap> {
    private final WeakReference<ImageView> image;

    public AsyncBitmapDownloader(ImageView iv) {
        // assign the image view
        this.image = new WeakReference<>(iv);
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        // initialize the result
        Bitmap bmp = null;

        try {
            // retrieve the url
            URL url = new URL(params[0]);

            // connect to the url
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();

            try {
                // get data
                InputStream is = new BufferedInputStream(urlConnection.getInputStream());
                bmp = BitmapFactory.decodeStream(is);
            } finally {
                urlConnection.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bmp;
    }

    @Override
    protected void onPostExecute(Bitmap bmp) {
        // update image
        image.get().setImageBitmap(bmp);
    }

}

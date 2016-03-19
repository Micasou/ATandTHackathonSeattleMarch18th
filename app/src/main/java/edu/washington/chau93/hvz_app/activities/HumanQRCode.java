package edu.washington.chau93.hvz_app.activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.InputStream;

import edu.washington.chau93.hvz_app.R;

public class HumanQRCode extends AppCompatActivity {


    private EditText userIDEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_human_qrcode);

        String userID = "ThisIsMyTempUserID1234";

        userIDEditText = (EditText)findViewById(R.id.humanQRCodeQRCodeEditText);
        userIDEditText.setText(userID);

        // show The Image in a ImageView
        new DownloadImageTask((ImageView) findViewById(R.id.humanQRCodeQRCodeImage))
                .execute("https://chart.googleapis.com/chart?chs=200x200&cht=qr&chl=" + userID + "&choe=UTF-8");
    }




    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}

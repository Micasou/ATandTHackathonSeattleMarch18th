package edu.washington.chau93.hvz_app.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class RulesActivity extends AppCompatActivity {
    /** A reference to the webview. */
    private WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myWebView = new WebView(this);

        // Enable javascript
        myWebView.getSettings().setJavaScriptEnabled(true);

        myWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request
                    , WebResourceError error) {
                super.onReceivedError(view, request, error);
                Toast.makeText(RulesActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        // disable scroll on touch
        myWebView.setOnTouchListener(new WebTouchListener());

        // Load the HvZ official website
        myWebView.loadUrl("https://humansvszombies.org/");

        // Set our webview as the content view.
        setContentView(myWebView);

    }

    private class WebTouchListener implements View.OnTouchListener {
        /** Keep track of the x position to prevent horizontal scrolling on webview. */
        private float myXPosition;

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getPointerCount() > 1) {
                //Multi touch detected
                return true;
            }

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN: {
                    // save the x
                    myXPosition = event.getX();
                }
                break;

                case MotionEvent.ACTION_MOVE:
                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_UP: {
                    // set x so that it doesn't move
                    event.setLocation(myXPosition, event.getY());
                }
                break;

            }
            return false;
        }
    }
}

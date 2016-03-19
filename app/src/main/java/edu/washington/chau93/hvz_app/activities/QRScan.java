package edu.washington.chau93.hvz_app.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import edu.washington.chau93.hvz_app.R;

//public class QRScan extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_qrscan);
//    }
//}

public class QRScan extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrscan);
//        HandleClick hc = new HandleClick();
        findViewById(R.id.qrScanScanBtn).setOnClickListener(new ScanClickListener());

    }

//    private class HandleClick implements DialogInterface.OnClickListener, View.OnClickListener {
//        @Override
//        public void onClick(View arg0) {
//            Intent intent = new Intent("com.google.zxing.client.android.SCAN");
//            intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
//            startActivityForResult(intent, 0);	//Barcode Scanner to scan for us
//        }
//    }


    private class ScanClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent("com.google.zxing.client.android.SCAN");
            intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
            startActivityForResult(intent, 0);	//Barcode Scanner to scan for us
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0) {
//            TextView tvStatus=(TextView)findViewById(R.id.tvStatus);
            EditText humanID = (EditText)findViewById(R.id.qrScanID);
            if (resultCode == RESULT_OK) {
//                tvStatus.setText(intent.getStringExtra("SCAN_RESULT_FORMAT"));
                humanID.setText(intent.getStringExtra("SCAN_RESULT"));
            } else if (resultCode == RESULT_CANCELED) {
//                tvStatus.setText("Press a button to start a scan.");
                humanID.setText("Scan cancelled.");
            }
        }
    }
}
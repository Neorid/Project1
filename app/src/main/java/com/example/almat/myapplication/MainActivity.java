package com.example.almat.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {
    private Button buttun;
    private Button btn;
    private Button poisk;
    private EditText IDtext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttun = (Button) findViewById(R.id.button);
        btn = (Button) findViewById(R.id.button2);
        poisk = (Button) findViewById(R.id.poisk);
        //final TextView textView = (TextView) findViewById(R.id.txtConcent);


        final Activity activity = this;


        //Initialization click button(scan) QR code
        buttun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator integrator = new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("Scan");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();
            }
        });
        //Initialization click Data base Search
        poisk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        //Show click next Activity
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity2();//show two Activity
            }
        });
    }

    //Click show activity two
    public void openActivity2() {
        Intent intent = new Intent(this, password.class);//Activity Two show
        startActivity(intent);
    }

    //Code QR and Barqode
    @Override
    protected void onActivityResult(int requesCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requesCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {

                //textView.setText("You canselled this scanner");
                Toast.makeText(this, "You canselled this scanner", Toast.LENGTH_LONG).show();

            } else {
                //textView.setText(result.getContents());
                Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requesCode, resultCode, data);
        }
    }
}


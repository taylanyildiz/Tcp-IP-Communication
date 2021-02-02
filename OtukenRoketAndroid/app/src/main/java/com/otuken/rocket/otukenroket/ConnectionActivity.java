package com.otuken.rocket.otukenroket;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


public class ConnectionActivity extends AppCompatActivity {


    private ImageView imageViewConnection,imageViewAppName;
    private Button buttonConnect;
    private TextView textViewInfo;
    private EditText editTextDeviceName,editTextIpAdress,editTextPortAdress;



    public static String port,ip,device;
    public void findId(){
        imageViewConnection = findViewById(R.id.imageViewConnection);
        imageViewAppName = findViewById(R.id.imageViewAppName);
        buttonConnect = findViewById(R.id.buttonConnect);
        textViewInfo = findViewById(R.id.textViewInfo);
        editTextDeviceName = findViewById(R.id.editTextDeviceName);
        editTextIpAdress = findViewById(R.id.editTextIpAdress);
        editTextPortAdress = findViewById(R.id.editTextPortAdress);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        findId();
        buttonConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(editTextIpAdress.getText()) || TextUtils.isEmpty(editTextPortAdress.getText()) ||
                TextUtils.isEmpty(editTextDeviceName.getText()) || editTextDeviceName.getText().length() < 6){
                    if(TextUtils.isEmpty(editTextIpAdress.getText())){
                        Toast.makeText(ConnectionActivity.this,"Lütfen IP kısmını Doludurunuz",Toast.LENGTH_SHORT).show();
                    }
                    if(TextUtils.isEmpty(editTextPortAdress.getText())){
                        Toast.makeText(ConnectionActivity.this,"Lütfen PORT kısmını Doludurunuz",Toast.LENGTH_SHORT).show();
                    }
                    if(TextUtils.isEmpty(editTextDeviceName.getText())){
                        Toast.makeText(ConnectionActivity.this,"Lütfen Cihaz İsim kısmını Doludurunuz",Toast.LENGTH_SHORT).show();
                    }
                    if(editTextDeviceName.getText().length() < 6){
                        Toast.makeText(ConnectionActivity.this,"Cihaz isminiz 6 haneden az veya çok olamaz",Toast.LENGTH_SHORT).show();
                    }
                }
                else {

                    ip = editTextIpAdress.getText().toString().trim();
                    port = editTextPortAdress.getText().toString().trim();
                    device = editTextDeviceName.getText().toString().trim();
                    startActivity(new Intent(ConnectionActivity.this,ReceiveActivity.class));
                    finish();
                }

            }
        });
    }




}

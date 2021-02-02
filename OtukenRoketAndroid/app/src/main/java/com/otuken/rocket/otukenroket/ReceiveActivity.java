package com.otuken.rocket.otukenroket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ReceiveActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Data> dataArrayList;
    private CardTasarim cardTasarim;
    private Data d1,d2,d3,d4,d5,d6;
    private ImageView imageViewExit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        imageViewExit = findViewById(R.id.imageViewExit);
        new Thread(new ClientThread()).start();
        dataArrayList = new ArrayList<>();
        recyclerView = findViewById(R.id.receyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        d1 = new Data("battery","V","0");
        d2 = new Data("height","m","0");
        d3 = new Data("speed","m/s","0");
        d4 = new Data("temperature","°C","0");
        d5 = new Data("moisture","g/m³ ","0");
        d6 = new Data("pressure","Pa","0");
        dataArrayList.add(d1);
        dataArrayList.add(d2);
        dataArrayList.add(d3);
        dataArrayList.add(d4);
        dataArrayList.add(d5);
        dataArrayList.add(d6);
        cardTasarim = new CardTasarim(ReceiveActivity.this,dataArrayList);
        recyclerView.setAdapter(cardTasarim);
        imageViewExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    startActivity(new Intent(ReceiveActivity.this,ConnectionActivity.class));
                    finish();
                    if(socket != null){
                        socket.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private Socket socket;
    private BufferedReader reader;
    private PrintWriter pw;

    public class ClientThread implements Runnable{
        @Override
        public void run() {
            try {
                if(socket == null){

                    socket = new Socket(ConnectionActivity.ip,Integer.parseInt(ConnectionActivity.port));
                    pw = new PrintWriter(socket.getOutputStream());

                    reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    if(socket.isConnected()){
                        pw.write(ConnectionActivity.device);
                        pw.flush();
                        new Thread(new ReceiveThread()).start();
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(socket == null){
                        Toast.makeText(ReceiveActivity.this,"Bağlantı yok",Toast.LENGTH_LONG).show();
                    }

                }
            });

        }
    }

    String dataList;
    public class ReceiveThread implements Runnable{
        @Override
        public void run() {
            try {
                while (socket.isConnected()){
                    dataList = reader.readLine();
                    if(dataList != null){
                        if (dataList.startsWith("a")){
                            final String[] dizi = dataList.split("a");
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    d4.setValue(dizi[1]);
                                    d2.setValue(dizi[2]);
                                    d3.setValue(dizi[3]);
                                    d5.setValue(dizi[4]);
                                    d6.setValue(dizi[5]);
                                    d1.setValue(dizi[6]);
                                    cardTasarim.notifyDataSetChanged();

                                }
                            });
                        }
                    }
                }
                if(!socket.isConnected()){
                    socket = null;
                    startActivity(new Intent(ReceiveActivity.this,ConnectionActivity.class));
                    finish();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

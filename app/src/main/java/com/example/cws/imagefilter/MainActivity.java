package com.example.cws.imagefilter;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import org.apache.http.HttpConnection;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    static Camera camera = null;
    private int cameraId = 0;
String savePass = "";
    GridView gridView ;
List<String> count ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = (GridView)findViewById(R.id.grid);
count = new ArrayList<>();

        for (int i = 1 ; i <10 ; i++)
        {
            count.add(""+i);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.adapterlayout, R.id.text1, count);
        gridView.setAdapter(adapter);

      gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

              savePass = savePass + count.get(i);
              Log.d("countget",""+count.get(i));
          }
      });

        Button reset = (Button)findViewById(R.id.continues);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savePass = "";
            }
        });

        Button continuess = (Button)findViewById(R.id.continues);
        continuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(savePass.equals(""))
                {
                    Toast.makeText(MainActivity.this,"Please make a password.",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent i = new Intent(MainActivity.this,SecondActivity.class);
                    i.putExtra("SavePassword",savePass);
                    startActivity(i);
                    finish();
                }
            }
        });

    }


    @Override
    protected void onPause() {
        super.onPause();

    }

  }

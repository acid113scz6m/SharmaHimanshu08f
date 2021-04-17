package com.example.cws.imagefilter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Handler;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.PictureCallback;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hanks.htextview.HTextView;
import com.hanks.htextview.HTextViewType;


public class SecondActivity extends AppCompatActivity implements SurfaceHolder.Callback{


    private Camera mCamera;
    SurfaceView surfaceView;
    SurfaceHolder surfaceHolder;
    PictureCallback rawCallback;
    Camera.ShutterCallback shutterCallback;
    PictureCallback jpegCallback;
        EditText savePass;

        String password = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


    }

    int ij = 0 ;
    public void captureImage(View v) throws IOException
    {

      /*  mCamera.takePicture(null,null,jpegCallback);*/

        if(savePass.getText().toString().trim().equals(password))
        {
            Log.d("savePass",savePass.getText().toString() +" - "+password);
            final Dialog dialog = new Dialog(SecondActivity.this);
            dialog.setContentView(R.layout.custom);

            TextView  hTextView = (TextView)dialog. findViewById(R.id.text);
            hTextView.setText("Hurray,  You have done it.");

            hTextView.setTypeface(null);


            dialog.show();
            new CountDownTimer(5000, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {
                    // TODO Auto-generated method stub

                }

                @Override
                public void onFinish() {
                    // TODO Auto-generated method stub

                    dialog.dismiss();
                }
            }.start();
            ij = 0;
        }
        else
        {
            Log.d("savePas2s",savePass.getText().toString() +" - "+password);
            mCamera.takePicture(null,null,jpegCallback);
            if(ij < 3) {
                ij++;
                Toast.makeText(SecondActivity.this, "Wrong Password", Toast.LENGTH_SHORT).show();
            }
            else
            {
                ij = 0;
                getAnimation();

            }
        }
    }

    public void getAnimation()
    {
        final Dialog dialog = new Dialog(SecondActivity.this);
        dialog.setContentView(R.layout.custom);
        TextView  hTextView = (TextView) dialog.findViewById(R.id.text);
        dialog.setCancelable(false);
        hTextView.setText("Sorry, Account Blocked.");

        hTextView.setTypeface(null);


        dialog.show();
        new CountDownTimer(5000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onFinish() {
                // TODO Auto-generated method stub

                dialog.dismiss();
            }
        }.start();

    }

    public void refreshCamera()
    {
        if(surfaceHolder.getSurface() == null)
        {
            return;
        }

        try {
            mCamera.stopPreview();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        try {
            mCamera.setPreviewDisplay(surfaceHolder);
            mCamera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

        if (getFrontCameraId() > 0) {
            Toast.makeText(SecondActivity.this, "Yes , Device has Front Cam", Toast.LENGTH_SHORT).show();


        try {
            mCamera = Camera.open(getFrontCameraId());
        } catch (RuntimeException e) {
            System.out.println(e);
            return;
        }

        Camera.Parameters parameters;
      /*  parameters = mCamera.getParameters();

        parameters.setPreviewSize(300,300);
        mCamera.setParameters(parameters);*/
        mCamera.setDisplayOrientation(90);
        try {
            mCamera.setPreviewDisplay(surfaceHolder);
            mCamera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }
        else
        {
            Toast.makeText(SecondActivity.this,"No Front Cam",Toast.LENGTH_SHORT).show();
            return;
        }
    }


   public int getFrontCameraId() {
        CameraInfo ci = new CameraInfo();
        for (int i = 0 ; i < Camera.getNumberOfCameras(); i++) {
            Camera.getCameraInfo(i, ci);
            if (ci.facing == CameraInfo.CAMERA_FACING_FRONT) return i;
        }
        return -1; // No front-facing camera found
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2)
    {
            refreshCamera();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder)
    {
        mCamera.stopPreview();
        mCamera.release();
        mCamera = null;
    }
}
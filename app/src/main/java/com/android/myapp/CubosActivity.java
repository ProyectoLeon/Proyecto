package com.android.myapp;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
//import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;


import org.neuroph.contrib.imgrec.ImageRecognitionPlugin;
import org.neuroph.contrib.imgrec.image.Image;
import org.neuroph.contrib.imgrec.image.ImageFactory;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.contrib.imgrec.image.ImageAndroid;
public class CubosActivity extends Activity {//implements View.OnTouchListener {

    private final int SELECT_PHOTO = 1;
    private final int LOADING_DATA_DIALOG = 2;
    private final int RECOGNIZING_IMAGE_DIALOG = 3;

    private TextView txtAnswer;
    private LinearLayout screen;

    private Bitmap bitmap;
    private Image image;

    private NeuralNetwork nnet;
    private ImageRecognitionPlugin imageRecognition;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cubos);

        txtAnswer = (TextView) findViewById(R.id.txtAnswer);
        screen = (LinearLayout) findViewById(R.id.screen);
//        screen.setOnTouchListener(this);

        dispatchTakePictureIntent();
        loadData();
    }

/*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        switch (requestCode) {
            case SELECT_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        Uri selectedImage = imageReturnedIntent.getData();
                        // get file path of selected image
                        String filePath = getRealPathFromURI(selectedImage);
                        // get image
                        image = ImageFactory.getImage(filePath);

                        InputStream imageStream = getContentResolver().openInputStream(selectedImage);
                        bitmap = Bitmap.createBitmap(BitmapFactory.decodeStream(imageStream));
                        // show image
                        txtAnswer.setCompoundDrawablesWithIntrinsicBounds(null, null, null, new BitmapDrawable(bitmap));
                        // show image name
                        txtAnswer.setText("Esto es " + recognize(image));
                    } catch (FileNotFoundException fnfe) {
                        Log.d("Neuroph Android Example", "File not found");
                    }
                }
        }
    }
*/

    //    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Intent imageIntent = new Intent(Intent.ACTION_PICK);
        imageIntent.setType("image/*");
        // show gallery
        startActivityForResult(imageIntent, SELECT_PHOTO);
        return false;
    }

    private Runnable loadDataRunnable = new Runnable() {
        public void run() {
            // open neural network
            InputStream is = getResources().openRawResource(R.raw.red_cubos);
            // load neural network
            nnet = NeuralNetwork.load(is);
            imageRecognition = (ImageRecognitionPlugin) nnet.getPlugin(ImageRecognitionPlugin.class);
            // dismiss loading dialog
            dismissDialog(LOADING_DATA_DIALOG);
        }
    };

    private String recognize(Image image) {
        showDialog(RECOGNIZING_IMAGE_DIALOG);
        // recognize image
        HashMap<String, Double> output = imageRecognition.recognizeImage(image);
        dismissDialog(RECOGNIZING_IMAGE_DIALOG);
        return getAnswer(output);
    }

    private void loadData() {
        showDialog(LOADING_DATA_DIALOG);
        // load neural network in separate thread with stack size = 32000
        new Thread(null, loadDataRunnable, "dataLoader", 32000).start();
    }

    public String getRealPathFromURI(Uri contentUri) {

        // converts uri to file path, converts /external/images/media/9 to /sdcard/neuroph/fish.jpg
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(contentUri, projection, null, null, null);
        int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();

        return cursor.getString(columnIndex);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        ProgressDialog dialog;

        if (id == LOADING_DATA_DIALOG) {
            dialog = new ProgressDialog(this);
            dialog.setTitle("Neuroph Example");
            dialog.setMessage("Loading data...");
            dialog.setCancelable(false);

            return dialog;
        } else if (id == RECOGNIZING_IMAGE_DIALOG) {
            dialog = new ProgressDialog(this);
            dialog.setTitle("Neuroph Example");
            dialog.setMessage("Recognizing image...");
            dialog.setCancelable(false);

            return dialog;
        }
        return null;
    }


    private String getAnswer(HashMap<String, Double> output) {
        double highest = 0;
        String answer = "";
        for (Map.Entry<String, Double> entry : output.entrySet()) {
            if (entry.getValue() > highest) {
                highest = entry.getValue();
                answer = entry.getKey();
            }
        }

        return answer;
    }

    static final int REQUEST_IMAGE_CAPTURE = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    //    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            bitmap = (Bitmap) extras.get("data");
            File file = new File(getFilesDir() + "path");
            try {
                OutputStream os = new BufferedOutputStream(new FileOutputStream(file));
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            image = ImageFactory.getImage(file);

//            InputStream imageStream = getContentResolver().openInputStream(selectedImage);
//            bitmap = Bitmap.createBitmap(BitmapFactory.decodeStream(imageStream));
            // show image
            txtAnswer.setCompoundDrawablesWithIntrinsicBounds(null, null, null, new BitmapDrawable(bitmap));
            // show image name
            txtAnswer.setText("Esto es " + recognize(image));

            file.delete();

        }
    }

}
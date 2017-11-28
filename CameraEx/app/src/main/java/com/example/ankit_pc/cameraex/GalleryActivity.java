package com.example.ankit_pc.cameraex;
import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.googlecode.tesseract.android.TessBaseAPI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class GalleryActivity extends Activity {
    Bitmap image;
    private TessBaseAPI mTess;
    String OCRresult=null;
    String datapath = "";String OcrResult="";
    Button ocrbutton,process;
    protected void onCreate(Bundle bd)
    {
        super.onCreate(bd);
        setContentView(R.layout.activity_main1);

       ocrbutton=findViewById(R.id.OCRbutton);process=findViewById(R.id.processButton);

        Intent intent = this.getIntent();
        image = (Bitmap) intent.getParcelableExtra("BitmapImage");
        ImageView i1 = (ImageView) findViewById(R.id.imageView);
        i1.setImageBitmap(image);

        String language = "eng";
        datapath = getFilesDir()+ "/tesseract/";
        mTess = new TessBaseAPI();

        checkFile(new File(datapath + "tessdata/"));

        mTess.init(datapath, language);
        ocrbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 OCRresult = null;
                Log.d("inside onclick","true");
                mTess.setImage(image);
                OCRresult = mTess.getUTF8Text();
                TextView OCRTextView = findViewById(R.id.OCRTextView);
                OCRTextView.setText(OCRresult);
                Log.d("result of text",OCRresult);


            }
        });

        process.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Process.class);
                i.putExtra("text",OCRresult);
                startActivity(i);
            }
        });


    }

    public void processImage(View view){
        mTess.setImage(image);
        OCRresult = mTess.getUTF8Text();
        TextView OCRTextView = (TextView) findViewById(R.id.OCRTextView);
        OCRTextView.setText(OCRresult);

        Intent i = new Intent(getApplicationContext(),Process.class);

    }
    public void process(View view)
    {
        Intent i = new Intent(this,Process.class);

        i.putExtra("text",OCRresult);
        startActivity(i);

    }

    private void checkFile(File dir) {
        if (!dir.exists()&& dir.mkdirs()){
            copyFiles();
        }
        if(dir.exists()) {
            String datafilepath = datapath+ "/tessdata/eng.traineddata";
            File datafile = new File(datafilepath);

            if (!datafile.exists()) {
                copyFiles();
            }
        }
    }

    private void copyFiles() {
        try {
            String filepath = datapath + "/tessdata/eng.traineddata";
            AssetManager assetManager = getAssets();

            InputStream instream = assetManager.open("tessdata/eng.traineddata");
            OutputStream outstream = new FileOutputStream(filepath);

            byte[] buffer = new byte[1024];
            int read;
            while ((read = instream.read(buffer)) != -1) {
                outstream.write(buffer, 0, read);
            }


            outstream.flush();
            outstream.close();
            instream.close();

            File file = new File(filepath);
            if (!file.exists()) {
                throw new FileNotFoundException();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


package br.com.gods.mathcalendar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import br.com.gods.mathcalendar.utils.DateUtils;
import de.cketti.shareintentbuilder.ShareIntentBuilder;

public class ProblemActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView image;
    private TextView currentDate;
    private TextView shareButton;
    private TextView saveButton;
    private TextView answerButton;
    private ImageView nextButton;
    private ImageView pastButton;
    private DateUtils dateUtils;
    private String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setViews();
        setListeners();
        dateUtils = new DateUtils();


        url = getIntent().getStringExtra("URL");
        String date = getIntent().getStringExtra("DATE");

        if(date == null){
            date = dateUtils.getCurrentDate();


        }
        System.out.println("Url" + url);
        if(url == "" || url == null){
            url = "http://2.bp.blogspot.com/_OfYYlOGGnDk/TKHs_WCjl3I/AAAAAAAAByg/JvFTEHBkgkU/s1600/pastel.jpg";
        }

        Picasso.with(this).load(url).resize(200, 200).into(image);

        currentDate.setText(date);

    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.back_button:
                Toast.makeText(this, "Past Problem", Toast.LENGTH_SHORT).show();
                break;
            case R.id.next_button:
                Toast.makeText(this, "Next Problem", Toast.LENGTH_SHORT).show();
                break;
            case R.id.saveBox:
            case R.id.saveButton:

                //Toast.makeText(this, "Save the problem", Toast.LENGTH_SHORT).show();
                break;
            case R.id.answerBox:
            case R.id.answerButton:
                Intent intent = new Intent(this, AnswerActivity.class);
                startActivity(intent);
                //Toast.makeText(this, "Problem's answer", Toast.LENGTH_SHORT).show();
                break;
            case R.id.shareBox:
            case R.id.shareButton:
                onShareItem();
                break;

        }


    }

    private void setViews(){
        image = (ImageView) findViewById(R.id.problem_image);
        currentDate = (TextView) findViewById(R.id.currentDate);
        shareButton = (TextView) findViewById(R.id.shareButton);
        saveButton = (TextView) findViewById(R.id.saveButton);
        answerButton = (TextView) findViewById(R.id.answerButton);
        nextButton = (ImageView) findViewById(R.id.next_button);
        pastButton = (ImageView) findViewById(R.id.back_button);
    }

    private void setListeners(){
        saveButton.setOnClickListener(this);
        shareButton.setOnClickListener(this);
        answerButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
        pastButton.setOnClickListener(this);

    }



    @Override
    public void onBackPressed(){
        this.finish();
    }

    public void onShareItem() {
        // Get access to bitmap image from view
        // Get access to the URI for the bitmap
        Drawable mDrawable = image.getDrawable();
        Bitmap mBitmap = ((BitmapDrawable)mDrawable).getBitmap();

        String path = MediaStore.Images.Media.insertImage(getContentResolver(),
                mBitmap, "Image Description", null);

        Uri uri = Uri.parse(path);


        String text = getString(R.string.share_text);
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, text);
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
        shareIntent.setType("image/*");
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivity(Intent.createChooser(shareIntent, "Share images..."));

    }

}

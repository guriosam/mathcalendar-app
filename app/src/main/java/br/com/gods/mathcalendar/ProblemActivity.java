package br.com.gods.mathcalendar;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dd.CircularProgressButton;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawableOld;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.Calendar;

import br.com.gods.mathcalendar.utils.DateUtils;
import br.com.gods.mathcalendar.utils.LocalCache;

public class ProblemActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView image;
    private TextView currentDate;
    private CircularProgressButton shareButton;
    private CircularProgressButton saveButton;
    private CircularProgressButton answerButton;
    private ImageView nextButton;
    private ImageView pastButton;
    private DateUtils dateUtils;
    private String url;
    private Activity act;
    private SimpleAdapter adapter;

    //Paid version
    private boolean vip;
    private int day;
    private int month;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Change to false when insert payment
        vip = true;

        try {

            act = this;
            adapter = new SimpleAdapter(this, true);
            //toolbar.setTitle("");
            //toolbar.hideOverflowMenu();

            setViews();
            setListeners();


            if(vip) {
                toolbar.setNavigationIcon(new IconicsDrawableOld(this, GoogleMaterial.Icon.gmd_event).actionBarSize());

                toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        act.finish();
                    }
                });


               // day = LocalCache.getInstance().getDay();
               // month = LocalCache.getInstance().getMonth();

               // pastButton.setVisibility(View.VISIBLE);
               // nextButton.setVisibility(View.VISIBLE);


            }


            dateUtils = new DateUtils();

            url = getIntent().getStringExtra("URL");
            String date = getIntent().getStringExtra("DATE");

            if (date == null) {
                date = dateUtils.getCurrentDate();
            }


            Picasso.with(this).load(url).placeholder(R.drawable.progress_animation).into(image, new com.squareup.picasso.Callback() {
                @Override
                public void onSuccess() {
                    //Success image already loaded into the view
                }

                @Override
                public void onError() {
                    //Error placeholder image already loaded into the view, do further handling of this situation here

                    Toast.makeText(act, act.getString(R.string.error_load_image), Toast.LENGTH_SHORT).show();
                    act.finish();
                }
            });

            currentDate.setText(date);

        } catch (Exception e){
            Toast.makeText(act, act.getString(R.string.general_error), Toast.LENGTH_SHORT).show();
            act.finish();
        }


    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case android.R.id.home:
                act.finish();
                break;
            case R.id.back_button:
                Toast.makeText(this, "Past Problem", Toast.LENGTH_SHORT).show();
                break;
            case R.id.next_button:
                Toast.makeText(this, "Next Problem", Toast.LENGTH_SHORT).show();
                break;
            case R.id.save:
                onSaveItem();
                //Toast.makeText(this, "Save the problem", Toast.LENGTH_SHORT).show();
                break;
            case R.id.answer:
                Intent intent = new Intent(this, AnswerActivity.class);
                intent.putExtra("URL", url.replace("problema", "resposta"));
                startActivity(intent);
                //Toast.makeText(this, "Problem's answer", Toast.LENGTH_SHORT).show();
                break;
            case R.id.share:

                DialogPlus dialog = DialogPlus.newDialog(this)
                        .setAdapter(adapter)
                        .setExpanded(false)

                        .setGravity(Gravity.CENTER)
                        .setOnItemClickListener(new OnItemClickListener() {
                            @Override
                            public void onItemClick(DialogPlus dialog, Object item, View view, int position) {


                            }
                        })
                        .setExpanded(true)  // This will enable the expand feature, (similar to android L share dialog)
                        .create();
                dialog.show();

                onShareItem();
                break;

        }


    }

    private void setViews(){
        image = (ImageView) findViewById(R.id.problem_image);
        currentDate = (TextView) findViewById(R.id.currentDate);
        shareButton = (CircularProgressButton) findViewById(R.id.share);
        saveButton = (CircularProgressButton) findViewById(R.id.save);
        answerButton = (CircularProgressButton) findViewById(R.id.answer);
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

        try {
            // Get access to bitmap image from view
            // Get access to the URI for the bitmap
            Drawable mDrawable = image.getDrawable();
            Bitmap mBitmap = ((BitmapDrawable) mDrawable).getBitmap();

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

        } catch(Exception e ){
            Toast.makeText(this, getString(R.string.enable_store), Toast.LENGTH_SHORT).show();
        }

    }

    public void onSaveItem(){
        try {
            Drawable mDrawable = image.getDrawable();
            Bitmap mBitmap = ((BitmapDrawable) mDrawable).getBitmap();

            String path = MediaStore.Images.Media.insertImage(getContentResolver(),
                    mBitmap, "Image Description", null);

            Toast.makeText(this, "Problem saved", Toast.LENGTH_SHORT).show();

        }  catch(Exception e ){
            Toast.makeText(this, getString(R.string.enable_store), Toast.LENGTH_SHORT).show();
        }
    }

}

package br.com.gods.mathcalendar;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class AnswerActivity extends AppCompatActivity {

    private Activity act;
    private ImageView image;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        act = this;

        try{

            image = (ImageView) findViewById(R.id.problem_image);
            url = getIntent().getStringExtra("URL");

            Picasso.with(this).load(url).into(image, new com.squareup.picasso.Callback() {
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

        } catch (Exception e){
            Toast.makeText(act, act.getString(R.string.general_error), Toast.LENGTH_SHORT).show();
            act.finish();
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_answer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_back) {
            this.finish();
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

}

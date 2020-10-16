package com.example.implicitintent;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {
    private Button sendEmailBtn;
    private Button moreInfoBtn;

    private EditText editText;
    private Button  searchBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        editText = (EditText) findViewById(R.id.queryEditTextText);
        searchBtn = (Button) findViewById(R.id.searchBtn);

        sendEmailBtn = (Button)findViewById(R.id.shareViaEmailBtn);
        sendEmailBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SendEmail.class);
            startActivity(intent);
        });

        moreInfoBtn = (Button)findViewById(R.id.moreInfoBtn);
        moreInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBrowser(v);

            }
        });

    }

    public void openBrowser(View view){
        Uri thing = Uri.parse("http://www.cookingforengineers.com/");
        Intent intent = new Intent(Intent.ACTION_VIEW, thing);
        //or:
        //intent.setData(thing);

       if(intent.resolveActivity(getPackageManager()) != null){
           startActivity(intent);
       }else{
           Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
        }
    }


    public void openMaps(View view){

        //generate random location
        //these params can be changed to limit the radius
        double MaxLat = 90;
        double MinLat = -90;
        double MaxLon = 180;
        double MinLon = -180;

        BigDecimal dbLon = new BigDecimal(Math.random() * (MaxLon - MinLon) + MinLon);
        String lonVal = dbLon.setScale(4, BigDecimal.ROUND_HALF_UP).toString();

        BigDecimal dbLat = new BigDecimal(Math.random() * (MaxLat - MinLat) + MinLat);
        String latVal = dbLat.setScale(4, BigDecimal.ROUND_HALF_UP).toString();

        String stringToParse =  "geo:"+  latVal +", " + lonVal;
        Uri location = Uri.parse(stringToParse);
        //Uri location = Uri.parse("geo:40.7934, -77.8600");
        //Uri location = Uri.parse("geo:?q=south+hills+school+of+business");

        Intent intent = new Intent(Intent.ACTION_VIEW);
        //or:
        intent.setData(location);
        intent.setPackage("com.google.android.apps.maps");

        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }else{
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
        }
    }


    public void openPort(View view){

        Intent intent = new Intent("com.example.myportfolio.MainActivity");

        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }else{
            Toast.makeText(this, "will go to github now", Toast.LENGTH_LONG).show();
            Uri gitPort = Uri.parse("https://github.com/elalimardani/MyPortfolioApp");
            Intent intentGitPort = new Intent(Intent.ACTION_VIEW, gitPort);
            if(intentGitPort.resolveActivity(getPackageManager()) != null){
                startActivity(intentGitPort);
            }else{
                Toast.makeText(this, "Could not open the git", Toast.LENGTH_LONG).show();
            }
        }
    }


    public void openSearch(View view){
        String query = editText.getText().toString().trim();
        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
        intent.putExtra(SearchManager.QUERY, query);
        if(intent.resolveActivity(getPackageManager()) !=null){
            startActivity(intent);
        }else {
            Toast.makeText(this, "No Googling today!", Toast.LENGTH_LONG).show();
        }
    }

}
package com.example.implicitintent;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button sendEmailBtn;
    private Button moreInfoBtn;
    private ImageButton imageButton;
    private EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        imageButton = (ImageButton) findViewById(R.id.imageButton);


        sendEmailBtn = (Button)findViewById(R.id.shareViaEmailBtn);
        sendEmailBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SendEmail.class);
            startActivity(intent);
        });

        moreInfoBtn = (Button)findViewById(R.id.moreInfoBtn);
        moreInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    public void openBrowser(View view){
        Uri thing = Uri.parse("http://www.google.com");
        Intent intent = new Intent(Intent.ACTION_VIEW, thing);
        //or:
        //intent.setData(thing);

       if(intent.resolveActivity(getPackageManager()) != null){
           startActivity(intent);
       }else{
           Toast.makeText(this, "sad face", Toast.LENGTH_LONG).show();
        }
    }


    public void openMaps(View view){
        //Uri thing = Uri.parse("geo:40.7934, -77.8600");
        //Uri thing = Uri.parse("geo:?q=south+hills+school+of+business");
        Uri thing = Uri.parse("geo:?q=south+hills,State+College,PA");
        Intent intent = new Intent(Intent.ACTION_VIEW);
        //or:
        intent.setData(thing);

        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }else{
            Toast.makeText(this, "sad face", Toast.LENGTH_LONG).show();
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
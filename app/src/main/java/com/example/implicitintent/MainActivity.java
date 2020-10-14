package com.example.implicitintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public Button sendEmailBtn;
    //Button moreInfoBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        sendEmailBtn.findViewById(R.id.shareViaEmailBtn);
        sendEmailBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SendEmail.class);
            startActivity(intent);
        });

       /* moreInfoBtn.findViewById(R.id.moreInfoBtn);
        moreInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, )
            }
        });*/

    }


}
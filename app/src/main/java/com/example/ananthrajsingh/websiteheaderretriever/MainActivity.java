package com.example.ananthrajsingh.websiteheaderretriever;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String WEBSITE_ADDRESS_EXTRA = "address";

    Button mSubmitButton;
    EditText mWebsiteEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSubmitButton = findViewById(R.id.submit_button);
        mWebsiteEditText = findViewById(R.id.website_edit_text);
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String address = mWebsiteEditText.getText().toString();
                Intent intent = new Intent(MainActivity.this, HeaderActivity.class);
                intent.putExtra(WEBSITE_ADDRESS_EXTRA, address);
                startActivity(intent);
            }
        });
    }
}

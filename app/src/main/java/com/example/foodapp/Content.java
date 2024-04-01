package com.example.foodapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class Content extends AppCompatActivity {

    TextView txtPostName,txtPostcontent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_content);



        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_back); // Đặt icon back tại đây
        }

        txtPostcontent = findViewById(R.id.txtPostcontent);
        txtPostName = findViewById(R.id.PostName);

        Intent intent = getIntent();
       String tenTruyen = intent.getStringExtra("postname");
       String content = intent.getStringExtra("content");

       txtPostName.setText(tenTruyen);
       txtPostcontent.setText(content);


        txtPostcontent.setMovementMethod(new ScrollingMovementMethod());

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
           finish(); // Quay lại Fragment trước đó
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
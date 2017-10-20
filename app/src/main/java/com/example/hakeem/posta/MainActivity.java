package com.example.hakeem.posta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button newPost = null;
    Button viewPosts = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newPost = (Button)findViewById(R.id.new_post);
        newPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PostInfo.class);
                startActivity(intent);
            }
        });
        viewPosts = (Button) findViewById(R.id.view_posts);
        viewPosts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TimeLine.class);
                startActivity(intent);
            }
        });

    }
}

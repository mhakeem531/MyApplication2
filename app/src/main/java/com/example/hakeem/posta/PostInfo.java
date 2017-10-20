package com.example.hakeem.posta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class PostInfo extends AppCompatActivity {

    public static final String LOG_TAG = MainActivity.class.getName();
    private EditText postContent = null;
    private EditText publisherName = null;
    private String PostFromEditText;
    private String publisherNameFromEditText;
    private byte[] parametersBytes;
    private String line;
    private BufferedReader outStream = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_info);

        Button post = (Button) findViewById(R.id.post_button);
        postContent = (EditText) findViewById(R.id.post_content);
        publisherName = (EditText) findViewById(R.id.publisher_name);

        //https://mhakeem531.000webhostapp.com/insertPost.php

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostFromEditText = postContent.getText().toString();
                publisherNameFromEditText = publisherName.getText().toString();
                Log.e(LOG_TAG, "clicked && " + PostFromEditText + "&&&" + publisherNameFromEditText);
                if (!PostFromEditText.isEmpty() && !publisherNameFromEditText.isEmpty()) {
                    insertNewPost();
                }
                finish();
            }
        });

    }

    private void insertNewPost() {
        final String url = "https://mhakeem531.000webhostapp.com/insertPost.php";
        String connectionParameters;
        String nameKey = "&writer=";
        String postKey = "postcontent=";
        try {
            connectionParameters =  postKey + URLEncoder.encode(PostFromEditText, "UTF-8")
                    + nameKey + URLEncoder.encode(publisherNameFromEditText, "UTF-8");
            parametersBytes = connectionParameters.getBytes();
            Log.e(LOG_TAG, " get parameters " + connectionParameters);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    URL insertNewPost = new URL(url);
                    HttpURLConnection Connection = (HttpURLConnection) insertNewPost.openConnection();
                    Connection.setRequestMethod("POST");
                    Connection.getOutputStream().write(parametersBytes);
                    final InputStreamReader stream = new InputStreamReader(Connection.getInputStream());
                    outStream = new BufferedReader(stream);
                    line = outStream.readLine();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.e(LOG_TAG, line);
                            Toast.makeText(PostInfo.this, line, Toast.LENGTH_LONG).show();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        final Thread th = new Thread(runnable);
        th.start();
    }
}

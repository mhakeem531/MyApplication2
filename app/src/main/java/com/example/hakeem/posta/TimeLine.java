package com.example.hakeem.posta;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


/**
 * Created by hakeem on 10/20/17.
 */

public class TimeLine extends AppCompatActivity {
    public static final String LOG_TAG = TimeLine.class.getName();
    private ArrayList<Post> allPosts = new ArrayList<Post>();
    private PostAdapter adapter;
    private String result;
    private final String url = "https://mhakeem531.000webhostapp.com/queryPostaJson.php";
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.posts_list);
        listView = (ListView) findViewById(R.id.list);
        fillAdapter();
    }
    private void fillAdapter() {
        Runnable runnable = new Runnable() {
            URL getPostsURL = null;

            @Override
            public void run() {
                try {
                    getPostsURL = new URL(url);
                    HttpURLConnection Connection = (HttpURLConnection) getPostsURL.openConnection();
                    final InputStreamReader stream = new InputStreamReader(Connection.getInputStream());
                    final BufferedReader outStream = new BufferedReader(stream);
                    final StringBuilder txtBuilder = new StringBuilder();
                    String line;
                    while ((line = outStream.readLine()) != null){
                        txtBuilder.append(line);
                    }
                    result = txtBuilder.toString();
                    Log.e(LOG_TAG, "x is " + result.charAt(0) + result.charAt(1) + result.charAt(2));
                    JSONObject object = new JSONObject(result);

                    String id, postContent, writer;
                    JSONArray postsArray = object.getJSONArray("posts");
                    Log.e(LOG_TAG, "length is "  + postsArray.length());
                    for (int i = 0 ; i < postsArray.length() ; i++){
                        JSONObject current = postsArray.getJSONObject(i);
                        id = current.getString("id");
                        postContent = current.getString("postcontent");
                        writer = current.getString("writer");
                        allPosts.add(new Post(postContent, writer, id));
                        Log.e(LOG_TAG, "id is " + id + "content is " + postContent + "writer is " + writer);
                    }



                    Log.e(LOG_TAG, "for finish");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    Log.e(LOG_TAG, "1error JOSON");
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e(LOG_TAG, "2error JOSON");
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e(LOG_TAG, "error JOSON");
                }
            }
        };

         final Thread th = new Thread(runnable);
        th.start();
        try {
            th.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        th.setPriority(Thread.MAX_PRIORITY);
        if(!th.isAlive()){
            adapter = new PostAdapter(this, allPosts);
            listView.setAdapter(adapter);
        }

    }
}

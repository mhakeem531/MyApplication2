package com.example.hakeem.posta;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by hakeem on 10/20/17.
 */

public class PostAdapter extends ArrayAdapter<Post> {
    public TextView postContetn;// = (ImageView) listItemView.findViewById(R.id.book_cover);
    public Button publisherName;



    public PostAdapter(Context context, ArrayList<Post> posts) {
        super(context, 0, posts);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        Post currentPost = getItem(position);

        postContetn = (TextView)listItemView.findViewById(R.id.post_content);
        publisherName = (Button)listItemView.findViewById(R.id.publisher);

        postContetn.setText(currentPost.getPostContent());
        publisherName.setText(currentPost.getPublisherName());


        return listItemView;
    }
}

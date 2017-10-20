package com.example.hakeem.posta;

/**
 * Created by hakeem on 10/20/17.
 */

public class Post {

    private String id;
    private String publisherName;
    private String postContent;

    public Post(String post, String name, String id){
        postContent = post;
        publisherName = name;
        this.id = id;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }


    @Override
    public String toString() {
        return "Post{" +
                "publisherName='" + publisherName + '\'' +
                ", postContent='" + postContent + '\'' +
                '}';
    }
}

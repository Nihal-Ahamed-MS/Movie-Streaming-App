package com.example.moviestreamingapp.model;

public class BannerMovies {

    Integer id;
    String movieName;
    String imageUrl;
    String fileUrls;

    public BannerMovies(Integer id, String movieName, String imageUrl, String fileUrls) {
        this.id = id;
        this.movieName = movieName;
        this.imageUrl = imageUrl;
        this.fileUrls = fileUrls;
    }

    public Integer getId() {
        return id;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getFileUrls() {
        return fileUrls;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setFileUrls(String fileUrls) {
        this.fileUrls = fileUrls;
    }
}

package com.example.moviestreamingapp.model;

public class HomeMovies {
    Integer id;
    String movieName;
    String imageUrl;
    String fileUrls;

    public HomeMovies(Integer id, String movieName, String imageUrl, String fileUrls) {
        this.id = id;
        this.movieName = movieName;
        this.imageUrl = imageUrl;
        this.fileUrls = fileUrls;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getFileUrls() {
        return fileUrls;
    }

    public void setFileUrls(String fileUrls) {
        this.fileUrls = fileUrls;
    }

    @Override
    public String toString() {
        return "HomeMovies{" +
                "id=" + id +
                ", movieName='" + movieName + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", fileUrls='" + fileUrls + '\'' +
                '}';
    }
}

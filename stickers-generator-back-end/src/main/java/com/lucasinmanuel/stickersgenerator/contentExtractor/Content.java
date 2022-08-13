package com.lucasinmanuel.stickersgenerator.contentExtractor;

public class Content {

    private String title;
    private String posterPath;
    private String voteAverage;

    public Content(String title, String posterPath, String voteAverage){
        this.title = title;
        this.posterPath = posterPath;
        this.voteAverage = voteAverage;
    }

    public String getTitle() {
        return title;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

}

package com.lucasinmanuel.stickersgenerator.urlRequest;

public class CreateUrl {

    InfoUrl infoUrl = new InfoUrl();

    public String getMovieSearch(String search,String pageNumber){

        String url = "https://api.themoviedb.org/3/search/movie" + "?api_key=" + infoUrl.getKey() + "&query=" + search + "&language=" + infoUrl.getLanguage()
                + "&page=" + pageNumber;

        return url;

    }

    public String getYearMovieSearch(String search, String year,String pageNumber){

        String url = "https://api.themoviedb.org/3/search/movie" + "?api_key=" + infoUrl.getKey() + "&query=" + search + "&language=" + infoUrl.getLanguage()
            + "&year=" + year + "&page=" + pageNumber;

        return url;

    }

    public String getMovieListType(String listType, String pageNumber){

        String url = "https://api.themoviedb.org/3/movie/" + listType + "?api_key=" + infoUrl.getKey() + "&language=" + infoUrl.getLanguage()
                + "&page=" + pageNumber;
        return url;

    }

    public String getMovie(String MovieId){

        String url = "https://api.themoviedb.org/3/movie/" + MovieId + "?api_key=" + infoUrl.getKey() + "&language=" + infoUrl.getLanguage();
        return url;

    }

}

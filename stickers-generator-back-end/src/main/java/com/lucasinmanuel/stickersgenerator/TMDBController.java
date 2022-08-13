package com.lucasinmanuel.stickersgenerator;

import com.lucasinmanuel.stickersgenerator.contentExtractor.Content;
import com.lucasinmanuel.stickersgenerator.contentExtractor.ContentExtractorTMDB;
import com.lucasinmanuel.stickersgenerator.stickerCreator.StickerResponse;
import com.lucasinmanuel.stickersgenerator.stickerCreator.StickersGenerator;
import com.lucasinmanuel.stickersgenerator.urlRequest.ClientHttp;
import com.lucasinmanuel.stickersgenerator.urlRequest.CreateUrl;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

@RestController
public class TMDBController {

    @CrossOrigin
    @GetMapping("tmdb/movies/search={search}&page-number={pageNumber}")
    public String searchMoviesApiTMDB(@PathVariable String search,@PathVariable String pageNumber){

        CreateUrl createUrl = new CreateUrl();
        String url = createUrl.getMovieSearch(search,pageNumber);

        ClientHttp httpRequest = new ClientHttp();
        String json = httpRequest.buscarDados(url);

        return json;

    }

    @CrossOrigin
    @GetMapping("tmdb/movies/search={search}&year={year}&page-number={pageNumber}")
    public String searchYearMoviesApiTMDB(@PathVariable String search,@PathVariable String year,@PathVariable String pageNumber){

        CreateUrl createUrl = new CreateUrl();
        String url = createUrl.getYearMovieSearch(search,year,pageNumber);

        ClientHttp httpRequest = new ClientHttp();
        String json = httpRequest.buscarDados(url);

        return json;

    }

    @CrossOrigin
    @GetMapping("tmdb/movies/list-type={listType}&page-number={pageNumber}")
    public String listTypeApiTMDB(@PathVariable String listType, @PathVariable String pageNumber) {

        CreateUrl createUrl = new CreateUrl();
        String url = createUrl.getMovieListType(listType, pageNumber);

        //FAZENDO REQUISIÇÃO HTTP
        ClientHttp httpRequest = new ClientHttp();
        String json = httpRequest.buscarDados(url);

        return json;

    }

    @CrossOrigin
    @GetMapping("tmdb/movies/generate-sticker/image-url={imageUrl}&comment={comment}&font-color={fontColor}&font-size={fontSize}")
    public StickerResponse gerarFigurinha(@PathVariable String imageUrl,@PathVariable String comment,@PathVariable String fontColor,@PathVariable String fontSize){

        //PEGANDO A IMAGEM
        String endereco = "https://image.tmdb.org/t/p/w500/"+imageUrl;

        InputStream inputStream;
        try {
            inputStream = new URL(endereco).openStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int R = Integer.parseInt(fontColor.split(",")[0]);
        int G = Integer.parseInt(fontColor.split(",")[1]);
        int B = Integer.parseInt(fontColor.split(",")[2]);

        //GERAR A FIGURINHA E PASSAR PARA O FORMATO BASE64
        StickersGenerator generator = new StickersGenerator();
        String imgBinData = generator.criar(inputStream,comment,new Color(R,G,B), Integer.parseInt(fontSize),"png");

        return new StickerResponse(imgBinData);

    }

}

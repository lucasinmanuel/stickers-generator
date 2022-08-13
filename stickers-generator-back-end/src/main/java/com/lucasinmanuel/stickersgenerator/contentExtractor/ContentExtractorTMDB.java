package com.lucasinmanuel.stickersgenerator.contentExtractor;

import com.google.gson.*;

import java.util.ArrayList;
import java.util.List;

public class ContentExtractorTMDB {
    public List<Content> extrair(String arquivoJson,boolean temArray){

        Gson g = new Gson();
        JsonObject listaObjetos = g.fromJson(arquivoJson, JsonObject.class);

        List<Content> contents = new ArrayList<>();

        if(!temArray){

            //PEGANDO NOME DO FILME
            String titulo = listaObjetos.get("title").getAsString().replace(":","")
                    .replace(" ","-").replace("\"","");

            //PEGANDO NOTA DO FILME
            String nota = listaObjetos.get("vote_average").getAsString().replace(",",".");

            //PEGANDO A IMAGEM
            String imgHalfPath = listaObjetos.get("poster_path").getAsString();

            var conteudo = new Content(titulo,imgHalfPath,nota);
            contents.add(conteudo);

        }else{

            JsonArray listaArray = listaObjetos.getAsJsonArray("results");

            for (int i = 0;i < listaArray.size();i++) {

                JsonObject conteudoObject = listaArray.get(i).getAsJsonObject();

                //PEGANDO O TÍTULO
                String titulo;
                titulo = conteudoObject.get("title").toString();

                //PEGANDO A NOTA
                String nota;
                nota = conteudoObject.get("vote_average").toString();

                //PEGANDO A IMAGEM
                String imgHalfPath ;
                imgHalfPath = conteudoObject.get("poster_path").toString();

                var conteudo = new Content(titulo,nota,imgHalfPath);
                contents.add(conteudo);

            }

        }
        return contents;
    }

}

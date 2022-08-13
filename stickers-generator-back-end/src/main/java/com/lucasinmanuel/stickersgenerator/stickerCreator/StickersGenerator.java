package com.lucasinmanuel.stickersgenerator.stickerCreator;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

public class StickersGenerator {

    public String criar(InputStream arquivo,String frase,Color corTexto, int fontSize,String formatoArquivo){

        //LEITURA DA IMAGEM ORIGINAL
        BufferedImage imageOriginal;
        try{
            imageOriginal = ImageIO.read(arquivo);
        }catch (IOException e){
            throw new RuntimeException(e);
        }

        //CRIANDO IMAGEM VAZIA EM MEMÓRIA
        int larguraImage = imageOriginal.getWidth();
        int alturaImage = imageOriginal.getHeight();
        double alturaAreaTexto = 0.18 * alturaImage;
        double novaAltura = alturaImage + alturaAreaTexto;
        BufferedImage novaImagem = new BufferedImage(larguraImage, (int) novaAltura,BufferedImage.TRANSLUCENT);

        //ADCIONANDO IMAGEM ORIGNAL A IMAGEM VAZIA COM BACKGROUND-COLOR ORANGE
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imageOriginal,0,0, null);

        //DEFININDO A COR/TAMANHO PARA FRASE
        int newSizeTexto = larguraImage / fontSize;
        Font fontTexto = new Font(Font.SANS_SERIF,Font.BOLD,newSizeTexto);
        graphics.setColor(corTexto);
        graphics.setFont(fontTexto);

        //ESCREVENDO E CENTRALIZANDO UMA FRASE NA NOVA IMAGEM
        FontMetrics fontMetrics = graphics.getFontMetrics();
        double larguraFrase = fontMetrics.stringWidth(frase);
        int alturaFrase = newSizeTexto / 2;

        int xTextoFrase = (int) (larguraImage / 2 - larguraFrase / 2);
        double yTextoFrase = (novaAltura - alturaAreaTexto / 2) + alturaFrase;
        graphics.drawString(frase,xTextoFrase, (int) yTextoFrase);

        //ESCREVENDO A NOVA IMAGEM NO FORMATO BINDATA
        try {

            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            ImageIO.write(novaImagem,formatoArquivo,bytes);
            String resultantImages = Base64.getEncoder().encodeToString(bytes.toByteArray());

            return resultantImages;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}

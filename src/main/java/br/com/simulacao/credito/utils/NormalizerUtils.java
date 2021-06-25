package br.com.simulacao.credito.utils;

import java.text.Normalizer;

public class NormalizerUtils {

    public static String retornaTextoSemAcentuacaoEspacoEMinusculo(String texto) {

        return Normalizer
                .normalize(texto, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "")
                .replaceAll(" ", "")
                .toLowerCase();
    }
}
package model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Lenguaje {
    public enum Lenguajes {
        Java,
        Kotlin,
        JavaScript,
        Typescript,
        Python,
        C
    }

    public static String lenguajesACadena (List<Lenguajes> lenguajes) {
        String cadena = "";
        for (Lenguajes lenguaje : lenguajes) {
            cadena+= lenguaje.name();
        }
        return cadena;
    }

    public static List<Lenguajes> cadenaALenguajes (String cadena) {
        return Arrays.stream(cadena.split(";")).map(s -> Lenguajes.valueOf(s)).collect(Collectors.toList());
    }
}







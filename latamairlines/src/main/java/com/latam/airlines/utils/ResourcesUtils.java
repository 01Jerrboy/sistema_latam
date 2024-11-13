package com.latam.airlines.utils;

import java.util.ArrayList;
import java.util.List;

public class ResourcesUtils {
    
    public static List<String>listTipoVuelo(){
        List<String> lista = new ArrayList<>();
        lista.add("Ida");
        lista.add("Ida y Vuelta");
        return lista;
    }
}

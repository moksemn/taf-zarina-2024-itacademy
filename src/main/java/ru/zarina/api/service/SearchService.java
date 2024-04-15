package ru.zarina.api.service;

import ru.zarina.util.DataGenerator;

import java.util.HashMap;
import java.util.Map;

public class SearchService {
    public static String URL = "https://sort.diginetica.net/search";

    public static Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("content-type", "application/json");
        return headers;
    }

    public static Map<String,String> getQueryParamsWithCorrectProduct(){
        return getQueryParams(DataGenerator.getRandomCorrectProduct());
    }
    public static Map<String,String> getQueryParamsWithIncorrectProduct(){
        return getQueryParams(DataGenerator.getRandomString(20));
    }
    private static Map<String, String> getQueryParams(String product) {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("st", product);
        queryParams.put("apiKey", "L1WU1VJBYK");
        queryParams.put("strategy", "advanced_xname,zero_queries");
        queryParams.put("fullData", "true");
        queryParams.put("withCorrection", "true");
        queryParams.put("withFacets", "true");
        queryParams.put("treeFacets", "true");
        queryParams.put("regionId", "global");
        queryParams.put("useCategoryPrediction", "false");
        queryParams.put("size", "18");
        queryParams.put("offset", "0");
        queryParams.put("showUnavailable", "true");
        queryParams.put("unavailableMultiplier", "0.2");
        queryParams.put("preview", "false");
        queryParams.put("withSku", "false");
        queryParams.put("sort", "DEFAULT");
        return queryParams;
    }
}

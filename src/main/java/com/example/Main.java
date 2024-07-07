package com.example;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import com.google.gson.Gson;

public class Main {
    public static void main(String[] args) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            Gson gson = new Gson();
            String url = "https://pokeapi.co/api/v2/pokemon?offset=0&limit=10";
            URI uri = new URI(url);
            // Buat URL dengan parameter limit dan offset
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

            // Deserialisasi JSON Response
            Pokemon jsonObject = gson.fromJson(response.body(), Pokemon.class);

            System.out.println("Pokemon Identified: " + jsonObject.count);
            System.out.println("====================================");

            for (PokemonResult result : jsonObject.results) {
                System.out.println("Name: " + result.name);
                System.out.println("URL: " + result.url);
                System.out.println("====================================");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
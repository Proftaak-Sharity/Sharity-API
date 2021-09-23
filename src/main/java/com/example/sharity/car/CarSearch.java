//package com.example.sharity.car;
//
//import org.json.JSONArray;
//import org.json.JSONObject;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//import java.net.URI;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
//
//public class CarSearch {
//
//    @SpringBootApplication
//
//
//    public class SharityApplication {
//
//        public static void main(String[] args) {
//            SpringApplication.run(SharityApplication.class, args);
//            {
//
//                HttpClient client = HttpClient.newHttpClient();
//                HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://opendata.rdw.nl/resource/5xwu-cdq3.json")).build();
//                client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
//                        .thenApply(HttpResponse::body)
//                        .thenAccept(System.out::println)
//                        .join();
//            }
//
////            public static String parse(String responseBody){
////                JSONArray cars = new JSONArray(responseBody);
////                for (int i = 0; i > cars.length(); i++) {
////                    JSONObject car = cars.getJSONObject(i);
////                    String kenteken = car.getString("kenteken");
////                    String merk = car.getString("merk");
////                    String handelsbenaming = car.getString("handelsbenaming");
////                    int zitplaatsen = car.getInt("aantal_zitplaatsen");
////                    String kleur = car.getString("eerste_kleur");
////                    String inrichting = car.getString("inrichting");
////                    System.out.println(kenteken + "  " + merk + "  " + handelsbenaming + "  " + kleur + "  " + zitplaatsen + "  " + inrichting);
////                }
////                return null;
//            }
//
//        }
//}

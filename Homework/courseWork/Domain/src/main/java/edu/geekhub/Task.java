package edu.geekhub;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import edu.geekhub.Service.CryptoCoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.TimerTask;

@Component
@Scope("prototype")
class Task extends TimerTask {

    private CryptoCoinService coinService;
    private Gson gson;

    @Autowired
    public Task(CryptoCoinService coinService) {
        this.coinService = coinService;
        this.gson = new Gson();
    }


    @Override
    public void run() {
        String url = "https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=10&page=1&sparkline=false";

        try {
            HttpClient httpClient = HttpClient.newBuilder()
                    .version(HttpClient.Version.HTTP_2)
                    .build();

            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(url))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            JsonArray array = gson.fromJson(response.body(), JsonArray.class);


            HashMap<String, Float> result = new HashMap<>();
            for (JsonElement element : array) {
                JsonObject coin = element.getAsJsonObject();
                String name = coin.get("name").getAsString();
                Float price;
                if (coin.get("current_price").isJsonNull()) {
                    price = 0.0F;
                } else {
                    price = coin.get("current_price").getAsFloat();
                }
                result.put(name, price);
            }
            System.out.println("Top 10 cryptocurrencies:");
            System.out.println(result);

            coinService.AddValues(result, LocalDateTime.now());
            System.out.println(coinService.getByName("bitcoin").toString());
            System.out.println(coinService.getLastPriceByName("Bitcoin"));

        } catch (IOException | InterruptedException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
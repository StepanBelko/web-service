package by.itstep.stpnbelko.webservice.dao.impl;

import by.itstep.stpnbelko.webservice.model.Coin;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static by.itstep.stpnbelko.webservice.util.JSONParser.URLReader;

public class CoinDAO {

    private static final URL tickersUrl;
    private static List<Coin> coinsList = new ArrayList<>();

    static {
        try {
            tickersUrl = new URL("https://api.coinlore.net/api/tickers/");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Coin> getCoinList() {
        return coinsList;
    }


    public static List<Coin> getCoinsListFromUrl(URL url) {
        try {
            String tickersJson = URLReader(url, StandardCharsets.UTF_8);
            // Считываем json
            Object obj = new JSONParser().parse(tickersJson);
            // Кастим obj в JSONObject
            JSONObject rootJsonObj = (JSONObject) obj;

            JSONArray jsonArray = (JSONArray) rootJsonObj.get("data");

            ObjectMapper mapper = new ObjectMapper();

            coinsList = mapper.readValue(String.valueOf(jsonArray), new TypeReference<>() {
            });

            return coinsList;

        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) {
        System.out.println(getCoinsListFromUrl(tickersUrl).get(0));
    }


}

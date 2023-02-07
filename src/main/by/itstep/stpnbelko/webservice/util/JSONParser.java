package by.itstep.stpnbelko.webservice.util;

import by.itstep.stpnbelko.webservice.model.Coin;
import by.itstep.stpnbelko.webservice.model.Currency;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

public class JSONParser {

    public static void listToJson(List<Currency> currencyList) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            mapper.writeValue(new File("allCur.json"), currencyList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Currency> getFromJson() {
        ObjectMapper mapper = new ObjectMapper();
        List<Currency> currencyList;
        File file = new File("allCur.json");
        try {
            currencyList = mapper.readValue(file, new TypeReference<List<Currency>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return currencyList;
    }

    public static List<Coin> getFromJson(String json) {
        ObjectMapper mapper = new ObjectMapper();
        List<Coin> currencyList;
        try {
            currencyList = mapper.readValue(json, new TypeReference<List<Coin>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return currencyList;
    }

    public static String URLReader(URL url, Charset encoding) throws IOException {
        try (InputStream in = url.openStream())
        {
            byte[] bytes = in.readAllBytes();
            return new String(bytes, encoding);
        }
    }

    public static void main(String[] args){
//        ObjectMapper mapper = new ObjectMapper();
//        try {
//            // Java объект из JSON файла
//            Coin coinFromFile = mapper.readValue(new File("/../model/coin.txt"), Coin.class);
//
//
//            // Java объект из JSON файла по URL (если данный файл доступен по указанному URL)
//             Coin coinFromWeb = mapper.readValue(new URL("https://api.coinlore.net/api/ticker/?id=90 (BTC)"), Coin.class);
//
//            System.out.println("Coin from WEB : " + coinFromWeb);
//            System.out.println("Coin from File : " + coinFromFile);
//
//        } catch (Exception e){
//            System.out.println(e.getMessage());
//        }
//
//        listToJson(XMLCurrencyParser.getAllCurrenciesList());


        try {
            String coinJson = URLReader(new URL("https://api.coinlore.net/api/ticker/?id=90"), Charset.forName("UTF-8"));
            List<Coin> coins = (List<Coin>) getFromJson(coinJson);
            System.out.println("coins list size = " + coins.size());
            Coin coin = coins.get(0);
            System.out.println(coin);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


}

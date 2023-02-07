package by.itstep.stpnbelko.webservice.util;

import by.itstep.stpnbelko.webservice.model.Currency;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
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

//        listToJson(XMLCurrencyParser.getAllCurrenciesList());
        getFromJson();
    }


}

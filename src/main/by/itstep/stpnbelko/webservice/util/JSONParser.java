package by.itstep.stpnbelko.webservice.util;

import by.itstep.stpnbelko.webservice.model.Coin;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.json.Json;

import java.io.File;
import java.net.URL;

public class JSONParser {

    public static void main(String[] args){
        ObjectMapper mapper = new ObjectMapper();
        try {
            // Java объект из JSON файла
            Coin coinFromFile = mapper.readValue(new File("/../model/coin.txt"), Coin.class);

            
            // Java объект из JSON файла по URL (если данный файл доступен по указанному URL)
             Coin coinFromWeb = mapper.readValue(new URL("https://api.coinlore.net/api/ticker/?id=90 (BTC)"), Coin.class);

            System.out.println("Coin from WEB : " + coinFromWeb);
            System.out.println("Coin from File : " + coinFromFile);

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}

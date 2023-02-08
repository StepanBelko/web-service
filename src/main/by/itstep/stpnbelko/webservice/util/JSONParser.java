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
            currencyList = mapper.readValue(file, new TypeReference<>() {
            });
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


}

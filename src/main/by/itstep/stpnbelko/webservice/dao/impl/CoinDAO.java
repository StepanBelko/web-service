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
import static by.itstep.stpnbelko.webservice.util.JSONParser.getCoinsListFromUrl;

public class CoinDAO {

    private static CoinDAO dao = new CoinDAO();

    private static List<Coin> coinsList = getCoinsListFromUrl();

    public List<Coin> getCoinList() {
        return coinsList;
    }

    public Coin getById(int id) {
        for (Coin coin : coinsList) {
            if (coin.getId() == id) {
                return coin;
            }
        }
        return null;
    }

    public Coin getBySymbol(String symbol) {
        for (Coin coin : coinsList) {
            if (coin.getSymbol().equalsIgnoreCase(symbol)) {
                return coin;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(dao.getById(80));
        System.out.println(dao.getBySymbol("ETH"));
    }


}

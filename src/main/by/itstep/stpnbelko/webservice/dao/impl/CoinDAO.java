package by.itstep.stpnbelko.webservice.dao.impl;

import by.itstep.stpnbelko.webservice.model.Coin;

import java.util.ArrayList;
import java.util.List;

public class CoinDAO {

    private static List<Coin> coinsList = new ArrayList<>();

    public List<Coin> getCoinList() {
        return coinsList;
    }



}

package by.itstep.stpnbelko.webservice.model;


import lombok.Data;

@Data
public class Coin {

    private int id;
    private String symbol;
    private String name;
    private String nameid;
    private int rank;
    private double price_usd;
    private double percent_change_24h;
    private double percent_change_1h;
    private double percent_change_7d;
    private double market_cap_usd;
    private double volume24;
    private double volume24_native;
    private double csupply;
    private double price_btc;
    private double tsupply;
    private double msupply;


}

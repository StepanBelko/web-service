package by.itstep.stpnbelko.webservice.dao.impl;

import by.itstep.stpnbelko.webservice.model.Currency;
import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.List;

public class CurrencyDAO {

    private static List<Currency> currencyList = new ArrayList<>();

    public List<Currency> getCurrencyList() {
        return currencyList;
    }

    public void add(Currency currency) {
        currencyList.add(currency);
    }

    public void setCurrencyProp(Currency currency, Node currencyProp) {
        String propName = currencyProp.getNodeName();
        String propValue = currencyProp.getChildNodes().item(0).getTextContent();
        switch (propName.toLowerCase().trim()) {
            case "numcode" : {
                currency.setNumCode(Integer.parseInt(propValue));}
            break;
            case "charcode" : {
                currency.setCharCode(propValue);
                break;
            }
            case "scale" : {
                currency.setScale(Integer.parseInt(propValue));
                break;
            }
            case "name" : {
                currency.setName(propValue);
                break;
            }
            case "rate" : {
                currency.setRate(Double.parseDouble(propValue));
                break;
            }
        }
    }

}

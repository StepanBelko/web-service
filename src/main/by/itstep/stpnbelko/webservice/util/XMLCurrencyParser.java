package by.itstep.stpnbelko.webservice.util;


import by.itstep.stpnbelko.webservice.dao.impl.CurrencyDAO;
import by.itstep.stpnbelko.webservice.model.Currency;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public class XMLCurrencyParser {

    public XMLCurrencyParser() {
    }

    private static final String CURRENCY_URL = "https://www.nbrb.by/Services/XmlExRates.aspx";

    private static Document loadDocument(String url) {
        System.out.println("loadDocument");
        Document doc = null;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        try {
            doc = factory.newDocumentBuilder().parse(new java.net.URL(url).openStream());
        } catch (java.net.ConnectException e) {
            System.err.print(" Oops! Something goes wrong! This is Belarus, baby! \nConnection timed out. ");
            System.err.print(CURRENCY_URL + " is not responsible. Please, try again later");
        } catch (SAXException | IOException | ParserConfigurationException e) {
            e.printStackTrace();
        }

        System.out.println("doc : " + doc);
        return doc;
    }

    public static String getCurrency(String currencyCode) {

        boolean isCurrencyCodeNext = false;
        Document doc = loadDocument(CURRENCY_URL);

        if (doc != null) {

            NodeList nodes = doc.getFirstChild().getChildNodes();

            for (int i = 0; i < nodes.getLength(); i++) {
                Node parent = nodes.item(i);

                if (parent.getNodeType() == 1) {
                    NodeList children = parent.getChildNodes();

                    for (int ii = 0; ii < children.getLength(); ii++) {
                        Node child = children.item(ii);
                        if (child.hasChildNodes()) {
                            if ((child.getNodeName().trim().equalsIgnoreCase("Rate")) && (isCurrencyCodeNext)) {
                                isCurrencyCodeNext = false;
                                return child.getFirstChild().getTextContent();
                            }

                            if (child.getFirstChild().getTextContent().trim().equalsIgnoreCase(currencyCode)) {
                                isCurrencyCodeNext = true;
                            }
                        }
                    }
                }
            }
        }
        return "0.0";
    }


    public static List<Currency> getAllCurrenciesList() {
        // Создается дерево DOM документа из файла
        Document doc = loadDocument(CURRENCY_URL);
        //dao для работы с листом
        CurrencyDAO dao = new CurrencyDAO();
        // Получаем корневой элемент
        Node root = doc.getDocumentElement();

        // Просматриваем все подэлементы корневого - т.е. валюты
        NodeList currencies = root.getChildNodes();

        for (int i = 0; i < currencies.getLength(); i++) {
            Node currency = currencies.item(i);
            // Если нода не текст, то это валюта - заходим внутрь
            //Почему нужно проверять что это не текст?
            if (currency.getNodeType() != Node.TEXT_NODE) {
                Currency valuta = new Currency();
                NodeList currencyProps = currency.getChildNodes();
                valuta.setId(Integer.parseInt(currency.getAttributes().getNamedItem("Id").getNodeValue()));
                for (int j = 0; j < currencyProps.getLength(); j++) {
                    Node currencyProp = currencyProps.item(j);
                    // Если нода не текст, то это один из параметров валюты - добавляем в лист
                    if (currencyProp.getNodeType() != Node.TEXT_NODE) {
                        dao.setCurrencyProp(valuta, currencyProp);
                    }
                }
                dao.add(valuta);
                System.out.println("added currency : " + valuta);
                System.out.println("===========");
            }
        }

        return dao.getCurrencyList();
    }

}

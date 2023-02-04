package by.itstep.stpnbelko.webservice.model;


import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@XmlRootElement
public class Currency {

    private int id;
    private int numCode;
    private String charCode;
    private int scale;
    private String name;
    private double rate;

}

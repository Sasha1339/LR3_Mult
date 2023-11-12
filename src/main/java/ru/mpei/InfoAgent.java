package ru.mpei;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name = "infoAgent")
@XmlAccessorType(XmlAccessType.FIELD)
public class InfoAgent {
    @XmlElement(name = "localName")
    private String localName;
    @XmlElement(name = "weight")
    private Integer weight;

}

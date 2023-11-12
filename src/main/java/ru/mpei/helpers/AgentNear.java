package ru.mpei.helpers;

import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.List;

@Data
@XmlRootElement(name = "config")
@XmlAccessorType(XmlAccessType.FIELD)
public class AgentNear {
    @XmlAttribute(name = "init")
    private boolean init;
    @XmlAttribute(name = "search")
    private String search;
    @XmlElement(name = "infoAgent")
    private List<InfoAgent> infoAgent;
}

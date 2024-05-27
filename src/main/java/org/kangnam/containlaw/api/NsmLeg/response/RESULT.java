package org.kangnam.containlaw.api.NsmLeg.response;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="RESULT")
public class RESULT {
    @XmlElement(name = "CODE")
    private String code;

    @XmlElement(name = "MESSAGE")
    private String message;
}

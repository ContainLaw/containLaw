package org.kangnam.containlaw.api.NsmLeg.response;

import lombok.Data;
import javax.xml.bind.annotation.*;
import java.util.List;

@Data
@XmlRootElement(name = "TVBPMBILL11")
@XmlAccessorType(XmlAccessType.FIELD)
public class TVBPMBILL11 {
    @XmlElement(name = "list_total_count")
    private int listTotalCount;

    @XmlElement(name = "RESULT")
    private RESULT result;

    @XmlElement(name = "row")
    private List<ROW> rows;
}


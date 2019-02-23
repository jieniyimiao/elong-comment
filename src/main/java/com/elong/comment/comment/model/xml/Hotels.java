package com.elong.comment.comment.model.xml;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "Hotels")
@XmlAccessorType(XmlAccessType.FIELD)
public class Hotels {
    @XmlElement(name = "Hotel")
    private List<Hotel> hotels;

    public List<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(List<Hotel> hotels) {
        this.hotels = hotels;
    }
}

package com.elong.comment.comment.model.xml;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "HotelIndex")
@XmlAccessorType(XmlAccessType.FIELD)
public class HotelIndex {
    @XmlElement(name = "Hotels")
    private Hotels hotelIndex;

    public Hotels getHotelIndex() {
        return hotelIndex;
    }

    public void setHotelIndex(Hotels hotelIndex) {
        this.hotelIndex = hotelIndex;
    }
}


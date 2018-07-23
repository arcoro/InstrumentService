package com.spring.entidad;

import java.io.Serializable;
import java.util.Date;

public class Instrument implements Serializable {
    private String instrumentId;

    public Instrument() {
    }

    public Instrument(String instrumentId) {
        this.instrumentId = instrumentId;
    }

    public String getInstrumentId() {
        return instrumentId;
    }

    public void setInstrumentId(String instrumentId) {
        this.instrumentId = instrumentId;
    }

    @Override
    public String toString() {
        return "Instrument{" +
                "instrumentId='" + instrumentId + '\'' +
                '}';
    }
}

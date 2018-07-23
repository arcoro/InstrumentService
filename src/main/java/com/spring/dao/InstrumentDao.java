package com.spring.dao;

import com.spring.entidad.Instrument;

import java.util.List;

public interface InstrumentDao{
    List<Instrument> findAll();
    Instrument findById(String instrumentId);
    public void save(Instrument instrument);
    Instrument update(Instrument instrument);
    Integer delete(Instrument instrument);
}

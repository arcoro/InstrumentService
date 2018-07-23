package com.spring.dao;

import com.spring.entidad.Instrument;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

@Repository
public class InstrumentDaoImpl implements InstrumentDao {

    private final String LISTAR="SELECT * FROM INSTRUMENT";
    private final String insert = "INSERT INTO INSTRUMENT (instrumentId) values (?)";

    private final JdbcTemplate jdbcTemplate;

    private final RabbitTemplate rabbitTemplate;
    @Value("${app.queueName}")
    String queueName;

    public InstrumentDaoImpl(JdbcTemplate jdbcTemplate, RabbitTemplate rabbitTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public List<Instrument> findAll() {
        return jdbcTemplate.query(LISTAR,new InstrumentMapper());
    }

    @Override
    public Instrument findById(String intrumentId) {
        return null;
    }

    @Override
    public void save(Instrument instrument) {
        Object[] datos = new Object[] {instrument.getInstrumentId()};
        int[] tipos = new int[]{Types.VARCHAR};
        int registro = jdbcTemplate.update(insert,datos,tipos);
        rabbitTemplate.convertAndSend(queueName, instrument);

    }

    @Override
    public Instrument update(Instrument instrument) {
        return null;
    }

    @Override
    public Integer delete(Instrument instrument) {
        return null;
    }
}

class InstrumentMapper implements RowMapper<Instrument> {

    @Override
    public Instrument mapRow (ResultSet rs, int rowNum) throws SQLException {
        Instrument instrument = new Instrument();
        instrument.setInstrumentId(rs.getString("instrumentId"));
        return instrument;
    }
}

package com.spring;

import com.spring.dao.InstrumentDao;
import com.spring.dao.InstrumentDaoImpl;
import com.spring.entidad.Instrument;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.GregorianCalendar;
import java.util.List;

@SpringBootApplication
@EnableRabbit
public class Application {

    public static final String QUEUE_SPECIFIC_NAME = "${app.queueName}";

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    public MappingJackson2MessageConverter consumerJackson2MessageConverter() {
        return new MappingJackson2MessageConverter();
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context= SpringApplication.run(Application.class, args);
        InstrumentDao instrumentDao = context.getBean(InstrumentDaoImpl.class);
        /*
        Instrument guitar = new Instrument();
        guitar.setInstrumentId("Guitar");
        instrumentDao.save(guitar);

        Instrument piano = new Instrument();
        piano.setInstrumentId("Piano");
        instrumentDao.save(piano);
        */

        List<Instrument> instruments=instrumentDao.findAll();
        System.out.println(instruments);
    }

}

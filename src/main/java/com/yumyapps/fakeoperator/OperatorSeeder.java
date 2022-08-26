package com.yumyapps.fakeoperator;


import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;

@Slf4j
@Component
public class OperatorSeeder {

    @Autowired
    ServiceManagerRepository serviceManagerRepository;

    /*@EventListener
    public void seedDate(ApplicationReadyEvent event) {
        for (int i = 0; i < 500; i++) {

            Faker fakeOperator = new Faker();
            ServiceManager serviceManager = new ServiceManager();
            serviceManager.setLoginId(fakeOperator.internet().emailAddress());
            serviceManager.setGivenName(fakeOperator.name().fullName());
            serviceManager.setFamilyName(fakeOperator.name().lastName());
            serviceManager.setFkRoleId(fakeOperator.number().numberBetween(1, 50));
            serviceManager.setRegisterUser((long)fakeOperator.random().nextInt(1,50));
            serviceManager.setRegisterDate(fakeOperator.date().past(fakeOperator.random().nextInt(1,50), TimeUnit.DAYS));
            serviceManager.setIsInvalid(fakeOperator.random().nextBoolean());
            serviceManager.setIsNotLocked(fakeOperator.random().nextBoolean());
            serviceManager.setLoginPassword(fakeOperator.random().nextInt(150, 500000).toString());
            serviceManager.setAuthorities(new String[]{"user:read", "user:right"});
            serviceManager.setValidStartDate(fakeOperator.date().past(fakeOperator.random().nextInt(1, 50), TimeUnit.DAYS));
            serviceManager.setExpirationDate(fakeOperator.date().future(fakeOperator.random().nextInt(1, 100), TimeUnit.DAYS));
            serviceManagerRepository.save(serviceManager);

        }

    }*/


    public void createCSV(ApplicationReadyEvent event) {

        String fileName = "src/main/resources/cars.csv";
        Path myPath = Paths.get(fileName);
        try (var writer = Files.newBufferedWriter(myPath, StandardCharsets.UTF_8)) {

            StatefulBeanToCsv<ServiceManager> beanToCsv = new StatefulBeanToCsvBuilder<ServiceManager>(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .withQuotechar(CSVWriter.DEFAULT_ESCAPE_CHARACTER)
                    .build();
            beanToCsv.write(serviceManagerRepository.findAll());

        } catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException | IOException ex) {

            log.info(ex.getMessage(), ex);
        }


    }

}

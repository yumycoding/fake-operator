package com.yumyapps.fakeoperator;


import com.github.javafaker.Faker;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.stream.IntStream;

@Slf4j
@Component
public class OperatorSeeder {

    @Autowired
    ServiceManagerRepository serviceManagerRepository;

    @Autowired
    UserRepository userRepository;

    //    @EventListener
    public void seedDate(ApplicationReadyEvent event) {
        for (int i = 0; i < 500; i++) {

            Faker fakeOperator = new Faker();
            ServiceManager serviceManager = new ServiceManager();
            serviceManager.setLoginId(fakeOperator.internet().emailAddress());
            serviceManager.setGivenName(fakeOperator.name().fullName());
            serviceManager.setFamilyName(fakeOperator.name().lastName());
            serviceManager.setFkRoleId(fakeOperator.number().numberBetween(1, 50));
            serviceManager.setRegisterUser((long) fakeOperator.random().nextInt(1, 50));
            serviceManager.setRegisterDate(fakeOperator.date().past(fakeOperator.random().nextInt(1, 50), TimeUnit.DAYS));
            serviceManager.setIsInvalid(fakeOperator.random().nextBoolean());
            serviceManager.setIsNotLocked(fakeOperator.random().nextBoolean());
            serviceManager.setLoginPassword(fakeOperator.random().nextInt(150, 500000).toString());
            serviceManager.setAuthorities(new String[]{"user:read", "user:right"});
            serviceManager.setValidStartDate(fakeOperator.date().past(fakeOperator.random().nextInt(1, 50), TimeUnit.DAYS));
            serviceManager.setExpirationDate(fakeOperator.date().future(fakeOperator.random().nextInt(1, 100), TimeUnit.DAYS));
            serviceManagerRepository.save(serviceManager);

        }


    }

    @EventListener
    public void seedUser(ApplicationReadyEvent event) {

        Faker faker = new Faker();
        for (int i = 0; i < 10; i++) {

            Map<String, String> metaData = new HashMap<>();
            metaData.put(faker.artist().name(), faker.ancient().hero());
            metaData.put(faker.artist().name(), faker.ancient().hero());
            metaData.put(faker.artist().name(), faker.ancient().hero());
            metaData.put(faker.artist().name(), faker.ancient().hero());

            userRepository.save(User.builder()
                    .name(faker.name().fullName())
                    .metaData(metaData)
                    .build());
        }

    }
//    public void createCSV(ApplicationReadyEvent event) {
//
//        String fileName = "src/main/resources/cars.csv";
//        Path myPath = Paths.get(fileName);
//        try (var writer = Files.newBufferedWriter(myPath, StandardCharsets.UTF_8)) {
//
//            StatefulBeanToCsv<ServiceManager> beanToCsv = new StatefulBeanToCsvBuilder<ServiceManager>(writer)
//                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
//                    .withQuotechar(CSVWriter.DEFAULT_ESCAPE_CHARACTER)
//                    .build();
//            beanToCsv.write(serviceManagerRepository.findAll());
//
//        } catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException | IOException ex) {
//
//            log.info(ex.getMessage(), ex);
//        }
//
//
//    }

//    List<Person> people = IntStream.rangeClosed(1,100)
//                .mapToObj(i -> new Person(
//                        faker.name().firstName(),
//                        faker.name().lastName(),
//                        faker.phoneNumber().cellPhone(),
//                        faker.internet().emailAddress(),
//                        new Address(
//                                faker.address().streetAddress(),
//                                faker.address().city(),
//                                faker.address().state(),
//                                faker.address().zipCode()
//                        )
//                )).toList();
//
//        repository.saveAll(people);

}

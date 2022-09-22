package com.yumyapps.fakeoperator;

import com.opencsv.CSVWriter;
import com.opencsv.ICSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping(path = "/")
@RequiredArgsConstructor
public class OperatorController {

    private final ServiceManagerRepository managerRepository;


    @GetMapping(path = "/generate")
    public void exportCSV(HttpServletResponse response) throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException {
        //set file name and content type
        String filename = "users.csv";
        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + filename + "\"");
        //create a csv writer
        StatefulBeanToCsv<ServiceManager> writer = new StatefulBeanToCsvBuilder<ServiceManager>(response.getWriter())
                .withQuotechar(ICSVWriter.NO_QUOTE_CHARACTER)
                .withSeparator(ICSVWriter.DEFAULT_SEPARATOR)
                .withOrderedResults(false)
                .build();
        //write all users to csv file
        writer.write(managerRepository.findAll());

    }

    @GetMapping(path = "/findAll")
    public Page<ServiceManager> findAllManagers(@RequestParam(name = "pageNumber", defaultValue = "1") int pageNumber,
                                                @RequestParam(value = "pageSize", defaultValue = "25") int pageSize,
                                                @RequestParam(value = "field", required = false, defaultValue = "givenName") String field,
                                                @RequestParam(value = "order", required = false, defaultValue = "false") boolean order) {

        PageRequest request = PageRequest.of(pageNumber, pageSize).withSort(Sort.Direction.ASC, field);
        return managerRepository.findAll(request);
    }


}

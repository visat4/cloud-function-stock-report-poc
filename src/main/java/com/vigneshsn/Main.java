package com.vigneshsn;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vigneshsn.gcsbucket_client.GCSBucketClient;
import com.vigneshsn.model.StockReportBulkRequest;
import com.vigneshsn.model.StockReportRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

//public class Main implements HttpFunction {
//
//    PubsubClient pubsubClient;
//    public Main() {
//        pubsubClient = new PubsubClient();
//    }
//
//    @Override
//    public void service(HttpRequest httpRequest, HttpResponse httpResponse) throws Exception {
//        StockReport stockReport = getTestReport();
//        pubsubClient.publish(stockReport.toString(), stockReport.getReportId());
//        httpResponse.getWriter().write("report processed successfully!");
//    }
//
//    private StockReport getTestReport() {
//        StockReport stockReport = new StockReport();
//        Item item = new Item("Screws", 1);
//        stockReport.setItems(List.of(item));
//        return stockReport;
//    }
//}

@SpringBootApplication
public class Main {

    @Autowired
    GCSBucketClient gcsBucketClient;

    ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public Function<StockReportBulkRequest, String> processReport() {
        return value -> {
            uploadReport(value);
            //todo: pass old report id
            List<StockReportRequest> updated = getUpdatedItems(value.getReportId());
            //todo: publish updated items to topic
            return "OK";
        };
    }

    void uploadReport(StockReportBulkRequest report) {
        String payload = "";
        try {
            payload = objectMapper.writeValueAsString(report);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("payload === " +payload);
        gcsBucketClient.save(report.getReportId(),  payload);
    }

    List<StockReportRequest> getUpdatedItems(String oldReportId) {
        //todo: compare two reports
        String data = gcsBucketClient.get(oldReportId);
        System.out.println("data === " +data.toString());
        return Collections.emptyList();
    }
}
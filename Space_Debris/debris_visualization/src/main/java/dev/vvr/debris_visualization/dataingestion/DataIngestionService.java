package dev.vvr.debris_visualization.dataingestion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

@Service
public class DataIngestionService {

    private static final Logger logger = LoggerFactory.getLogger(DataIngestionService.class);

    @Value("${debris.data.file.path}")
    private String DEBRIS_DATA_FILE_PATH;

    @Scheduled(fixedDelay = 5000)
    public void ingestDebrisData() {
        logger.info("Ingesting data: " + new Date());
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(DEBRIS_DATA_FILE_PATH))) {
            String line;
            while((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException ioException) {
            logger.error(ioException.getMessage());
        }
    }
}

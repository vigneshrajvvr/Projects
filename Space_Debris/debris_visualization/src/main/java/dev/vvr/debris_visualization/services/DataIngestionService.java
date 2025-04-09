package dev.vvr.debris_visualization.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.vvr.debris_visualization.models.DebrisData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
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

    @Value("${kafka.topic.name}")
    private String TOPIC_NAME;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Scheduled(fixedDelay = 5000)
    public void ingestDebrisData() {
        logger.debug("Ingesting data: " + new Date());
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(DEBRIS_DATA_FILE_PATH))) {
            String line;
            while((line = bufferedReader.readLine()) != null) {
                String[] debrisDetails = line.split(",");
                String debrisId = debrisDetails[0].split(":")[1];
                String epoch = debrisDetails[1].split(":")[1];
                double inclination = Double.parseDouble(debrisDetails[2].split(":")[1]);
                double raan = Double.parseDouble(debrisDetails[3].split(":")[1]);
                DebrisData debrisData = new DebrisData(debrisId, epoch, inclination, raan);
                try {
                    String debrisJson = objectMapper.writeValueAsString(debrisData);
                    kafkaTemplate.send(TOPIC_NAME, debrisJson);
                    logger.info("Sent to Kafka: " + debrisJson);
                } catch (Exception ex) {
                    logger.error("Error converting to JSON or sending to Kafka: " + ex.getMessage());
                }
            }
        } catch (IOException ioException) {
            logger.error(ioException.getMessage());
        }
    }
}

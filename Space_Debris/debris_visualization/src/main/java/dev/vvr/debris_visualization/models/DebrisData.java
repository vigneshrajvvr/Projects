package dev.vvr.debris_visualization.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class DebrisData {
    private String id;
    private String epoch;
    private double inclination;
    private double raan;
}

package dev.vvr.debris_visualization.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.Date;

public class DebrisData {
    private String id;
    private String epoch;
    private double inclination;
    private double raan;

    public DebrisData(String id, String epoch, double inclination, double raan) {
        this.id = id;
        this.epoch = epoch;
        this.inclination = inclination;
        this.raan = raan;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEpoch() {
        return epoch;
    }

    public void setEpoch(String epoch) {
        this.epoch = epoch;
    }

    public double getInclination() {
        return inclination;
    }

    public void setInclination(double inclination) {
        this.inclination = inclination;
    }

    public double getRaan() {
        return raan;
    }

    public void setRaan(double raan) {
        this.raan = raan;
    }
}

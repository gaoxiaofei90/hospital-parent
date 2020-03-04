package com.llw.hospital.dto;

import java.io.Serializable;
import java.util.List;

public class FeatureByte implements Serializable {
    private List<byte[]> features;

    private String algVersion;

    public List<byte[]> getFeatures() {
        return features;
    }

    public void setFeatures(List<byte[]> features) {
        this.features = features;
    }

    public String getAlgVersion() {
        return algVersion;
    }

    public void setAlgVersion(String algVersion) {
        this.algVersion = algVersion;
    }
}

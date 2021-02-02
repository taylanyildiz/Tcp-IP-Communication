package com.otuken.rocket.otukenroket;

public class Data {
    private String imageDataName;
    private String imageValueName;
    private String value;

    public Data() {
    }

    public Data(String imageDataName, String imageValueName, String value) {
        this.imageDataName = imageDataName;
        this.imageValueName = imageValueName;
        this.value = value;
    }

    public String getImageDataName() {
        return imageDataName;
    }

    public void setImageDataName(String imageDataName) {
        this.imageDataName = imageDataName;
    }

    public String getImageValueName() {
        return imageValueName;
    }

    public void setImageValueName(String imageValueName) {
        this.imageValueName = imageValueName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

package org.project.store;

import java.time.LocalTime;
import java.util.LinkedHashMap;

public class StoreDto {
    private String[] pickers;
    private String pickingStartTime;
    private String pickingEndTime;

    public String[] getPickers() {
        return pickers;
    }

    public void setPickers(String[] pickers) {
        this.pickers = pickers;
    }

    public String getPickingStartTime() {
        return pickingStartTime;
    }

    public void setPickingStartTime(String pickingStartTime) {
        this.pickingStartTime = pickingStartTime;
    }

    public String getPickingEndTime() {
        return pickingEndTime;
    }

    public void setPickingEndTime(String pickingEndTime) {
        this.pickingEndTime = pickingEndTime;
    }
}



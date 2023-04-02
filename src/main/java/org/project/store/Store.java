package org.project.store;

import org.project.picker.Picker;
import java.time.LocalTime;
import java.util.List;

public class Store {
    private List<Picker> pickers;
    private LocalTime pickingStartTime;
    private LocalTime pickingEndTime;

    public List<Picker> getPickers() {
        return pickers;
    }

    public void setPickers(List<Picker> pickers) {
        this.pickers = pickers;
    }

    public LocalTime getPickingStartTime() {
        return pickingStartTime;
    }

    public void setPickingStartTime(LocalTime pickingStartTime) {
        this.pickingStartTime = pickingStartTime;
    }

    public LocalTime getPickingEndTime() {
        return pickingEndTime;
    }

    public void setPickingEndTime(LocalTime pickingEndTime) {
        this.pickingEndTime = pickingEndTime;
    }

    @Override
    public String toString() {
        return "Store{" +
                "pickers=" + pickers +
                ", pickingStartTime=" + pickingStartTime +
                ", pickingEndTime=" + pickingEndTime +
                '}';
    }
}

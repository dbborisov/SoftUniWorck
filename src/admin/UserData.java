package admin;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class UserData {
    private final StringProperty whatIncident;
    private final StringProperty description;
    private final StringProperty whoCalled;
    private final StringProperty onShift;
    private final StringProperty dayTime;
//    private final StringProperty status ;

    public UserData(String whatIncident, String description, String whoCalled, String onShift, String dayTime) {
        this.whatIncident = new SimpleStringProperty(whatIncident);
        this.description = new SimpleStringProperty(description);
        this.whoCalled = new SimpleStringProperty(whoCalled);
        this.onShift = new SimpleStringProperty(onShift);
        this.dayTime = new SimpleStringProperty(dayTime);
//        this.status = new SimpleStringProperty(status);
    }

//    public String getStatus() {
//        return status.get();
//    }
//
//    public StringProperty statusProperty() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status.set(status);
//    }

    public String getWhatIncident() {
        return whatIncident.get();
    }

    public StringProperty whatIncidentProperty() {
        return whatIncident;
    }

    public void setWhatIncident(String whatIncident) {
        this.whatIncident.set(whatIncident);
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public String getWhoCalled() {
        return whoCalled.get();
    }

    public StringProperty whoCalledProperty() {
        return whoCalled;
    }

    public void setWhoCalled(String whoCalled) {
        this.whoCalled.set(whoCalled);
    }

    public String getOnShift() {
        return onShift.get();
    }

    public StringProperty onShiftProperty() {
        return onShift;
    }

    public void setOnShift(String onShift) {
        this.onShift.set(onShift);
    }

    public String getDayTime() {
        return dayTime.get();
    }

    public StringProperty dayTimeProperty() {
        return dayTime;
    }

    public void setDayTime(String dayTime) {
        this.dayTime.set(dayTime);
    }
}
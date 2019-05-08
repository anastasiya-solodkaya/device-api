package ru.asolodkaia.devicesapi.database.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "AVAILABLE_DEVICE")
public class AvailableDevice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "model_id", nullable = false)
    private Model model;

    @Column(name = "device_comment", length = 150)
    private String deviceComment;

    @Column(length = 50)
    private String booker;

    @Column(name = "booked_timestamp")
    private Timestamp bookedTimestamp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public String getDeviceComment() {
        return deviceComment;
    }

    public void setDeviceComment(String deviceComment) {
        this.deviceComment = deviceComment;
    }

    public String getBooker() {
        return booker;
    }

    public void setBooker(String booker) {
        this.booker = booker;
    }

    public Timestamp getBookedTimestamp() {
        return bookedTimestamp;
    }

    public void setBookedTimestamp(Timestamp bookedTimestamp) {
        this.bookedTimestamp = bookedTimestamp;
    }
}

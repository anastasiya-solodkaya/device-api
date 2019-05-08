package ru.asolodkaia.devicesapi.database.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", length = 20)
    private String name;

    @Column(name = "descriptive_name", length = 100, unique = true)
    private String descriptiveName;

    @ManyToOne
    @JoinColumn(name="brand_id", nullable=false)
    private Brand brand;

    @OneToMany(mappedBy = "model")
    private Set<AvailableDevice> devices;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescriptiveName() {
        return descriptiveName;
    }

    public void setDescriptiveName(String descriptiveName) {
        this.descriptiveName = descriptiveName;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Set<AvailableDevice> getDevices() {
        return devices;
    }

    public void setDevices(Set<AvailableDevice> devices) {
        this.devices = devices;
    }
}

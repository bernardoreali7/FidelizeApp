package fidelizeapp.app.main;

import java.io.Serializable;

public class Cliente implements Serializable {
    private long id;
    private String name;
    private String phone;

    public Cliente(long id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }
}

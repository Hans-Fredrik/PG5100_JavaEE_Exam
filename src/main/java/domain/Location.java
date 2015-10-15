package domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by hffb on 15/10/15.
 */

@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String room;
    @NotNull
    private String buildning;


    public Location() {
    }

    public Location(String room, String buildning) {
        this.room = room;
        this.buildning = buildning;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getBuildning() {
        return buildning;
    }

    public void setBuildning(String buildning) {
        this.buildning = buildning;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location)) return false;

        Location location = (Location) o;

        if (id != location.id) return false;
        return true;
    }


    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", room='" + room + '\'' +
                ", buildning='" + buildning + '\'' +
                '}';
    }
}

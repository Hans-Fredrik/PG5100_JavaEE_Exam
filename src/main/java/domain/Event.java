package domain;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by hffb on 03/12/15.
 */
@Entity
@SecondaryTable(name = "EVENT_DETAILS")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private EventType eventType;

    @NotNull
    @Size(min = 5, max = 25)
    private String title;

    @Size(max = 100)
    private String description;

    @NotNull
    @Valid
    @ManyToOne
    @JoinColumn(name = "FK_Course")
    private Course course;

    @NotNull
    @Column(table = "EVENT_DETAILS")
    private Date startingTime;

    @NotNull
    @Column(table = "EVENT_DETAILS" )
    private Date endingTime;


    public Event() {

    }

    public Event(EventType eventType, String title, String description, Course course, Date startingTime, Date endingTime) {
        this.eventType = eventType;
        this.title = title;
        this.description = description;
        this.course = course;
        this.startingTime = startingTime;
        this.endingTime = endingTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Date getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(Date startingTime) {
        this.startingTime = startingTime;
    }

    public Date getEndingTime() {
        return endingTime;
    }

    public void setEndingTime(Date endingTime) {
        this.endingTime = endingTime;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", eventType=" + eventType +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", course=" + course +
                ", startingTime=" + startingTime +
                ", endingTime=" + endingTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Event)) return false;

        Event event = (Event) o;

        if (id != event.id) return false;

        return true;
    }
}

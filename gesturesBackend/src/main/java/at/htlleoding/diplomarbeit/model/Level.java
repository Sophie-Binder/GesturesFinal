package at.htlleoding.diplomarbeit.model;

import jakarta.persistence.*;

@Entity
@Table(name = "level")
public class Level {


    @SequenceGenerator(
            name = "level_seq",
            sequenceName = "level_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "level_seq")
    @Id
    private Long id;

    private int number;

    private int stars;

    private boolean isFinished;

    private boolean isStarted;

    @ManyToOne
    private Gesture gesture;

    @ManyToOne
    private User user;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public void setStarted(boolean started) {
        isStarted = started;
    }

    public Gesture getGesture() {
        return gesture;
    }

    public void setGesture(Gesture gesture) {
        this.gesture = gesture;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}

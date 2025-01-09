package at.htlleoding.diplomarbeit.model;

import jakarta.persistence.*;

@Entity
@Table(name = "gesture")
public class Gesture {
    @Id
    @SequenceGenerator(
            name = "gesture_seq",
            sequenceName = "gesture_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gesture_seq")
    private Long id;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}

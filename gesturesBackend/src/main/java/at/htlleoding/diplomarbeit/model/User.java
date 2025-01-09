package at.htlleoding.diplomarbeit.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "appUser")
public class User {


    @SequenceGenerator(
            name = "user_seq",
            sequenceName = "user_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @Id
    private Long id;

    private String name;

    private String email;

    @OneToMany(mappedBy = "user")
    private List<Level> levels;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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

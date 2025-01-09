package at.htlleoding.diplomarbeit.repository;

import at.htlleoding.diplomarbeit.model.Level;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class LevelRepository implements PanacheRepository<Level> {

    public List<Level> findByUserId(Long userId) {
        return list("user.id", userId);
    }

}

package at.htlleoding.diplomarbeit.repository;

import at.htlleoding.diplomarbeit.model.Gesture;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GestureRepository implements PanacheRepository<Gesture> {
}

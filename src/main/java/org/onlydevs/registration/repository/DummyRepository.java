package org.onlydevs.registration.repository;

import java.util.List;
import org.onlydevs.registration.PojaGenerated;
import org.onlydevs.registration.repository.model.Dummy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@PojaGenerated
@Repository
public interface DummyRepository extends JpaRepository<Dummy, String> {

  @Override
  List<Dummy> findAll();
}

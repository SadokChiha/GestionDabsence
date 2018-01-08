package tn.iit.ws.controle_enseignant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tn.iit.ws.controle_enseignant.entities.Enseignement;

@Repository
public interface EnseignementRepository extends JpaRepository<Enseignement, Integer> {
	@Query("select e from Enseignement e ")
	abstract List<Enseignement> findEnsByDate();
}

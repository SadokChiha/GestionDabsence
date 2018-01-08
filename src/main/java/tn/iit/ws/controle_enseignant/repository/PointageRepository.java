package tn.iit.ws.controle_enseignant.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.iit.ws.controle_enseignant.entities.Pointage;
@Repository
public interface PointageRepository extends JpaRepository<Pointage, Integer> {
	 @Query("select ex from Pointage ex where ex.idEnseignement.enseignant.id= :id")
	  abstract List<Pointage> findPointageEns(@Param("id") int id);
	 
	 @Query("select ex from Pointage ex")
	  abstract List<Pointage> findPointageDate(String date);
}

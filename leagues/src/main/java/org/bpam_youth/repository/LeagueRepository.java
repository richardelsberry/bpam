package org.bpam_youth.repository;

import java.util.List;

import org.bpam_youth.entities.LeagueEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeagueRepository extends JpaRepository<LeagueEntity, Long> {
	
	List<LeagueEntity> findByName(String name);

}

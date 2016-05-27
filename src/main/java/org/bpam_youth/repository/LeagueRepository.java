package org.bpam_youth.repository;

import java.util.List;

import org.bpam_youth.entities.LeagueEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeagueRepository extends JpaRepository<LeagueEntity, Long> {
	
	List<LeagueEntity> findAllByOrderByNameAsc();
	
	List<LeagueEntity> findAllByOrderByNameDesc();
	
	LeagueEntity findByName(String name);
	
	LeagueEntity findById(Long id);

}

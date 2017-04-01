package org.bpam_youth.repository;

import java.util.List;

import org.bpam_youth.entities.LeagueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LeagueRepository extends JpaRepository<LeagueEntity, Long> {
	
	List<LeagueEntity> findAllByOrderByNameAsc();
	
	List<LeagueEntity> findAllByOrderByNameDesc();
	
	LeagueEntity findByName(String name);
	
	LeagueEntity findById(Long id);

    List<LeagueEntity> findByCityIsIn(List<String> city);
    List<LeagueEntity> findByZipIsIn(List<String> city);
    List<LeagueEntity> findByNameIsIn(List<String> city);



	//List<LeagueEntity> findAllByZipCode(List<String> zipCodes);

    //@Query("select l from League l where l.zipCode IN(:zipCodes)")
    //List<LeagueEntity> findByZipCodeIsIn(@Param("zipCodes") List<String> zipCodes);

//    @Query("select l from League l where l.zipCode IN(:zipCodes)")
//    LeagueEntity findByZipCode(@Param("zipCodes") String zipCodes);

}

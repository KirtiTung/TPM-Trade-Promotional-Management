package com.kirti.tpm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kirti.tpm.entity.Tactic;

@Repository
public interface TacticRepository extends JpaRepository<Tactic,Long> {

}

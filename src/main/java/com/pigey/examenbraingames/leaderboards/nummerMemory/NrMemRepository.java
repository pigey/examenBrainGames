package com.pigey.examenbraingames.leaderboards.nummerMemory;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NrMemRepository extends JpaRepository<NrMemModel, Long> {

    NrMemModel findByUsername(String name);

}

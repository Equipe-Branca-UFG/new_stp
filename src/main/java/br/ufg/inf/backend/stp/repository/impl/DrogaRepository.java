package br.ufg.inf.backend.stp.repository.impl;

import br.ufg.inf.backend.stp.domain.droga.Droga;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrogaRepository extends JpaRepository<Droga, Long> {
}

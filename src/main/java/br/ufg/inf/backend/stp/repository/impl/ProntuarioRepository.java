package br.ufg.inf.backend.stp.repository.impl;

import br.ufg.inf.backend.stp.domain.prontuario.Prontuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProntuarioRepository extends JpaRepository<Prontuario, Long> {
}

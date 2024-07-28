package br.ufg.inf.backend.stp.repository.custom;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface TransferenciaRepositoryCustom {

    void inserirTransferencia(Long id, Long pacienteId, Long origemId, Long destinoId, Long meioTransporte, LocalDateTime horarioSaida, LocalDate horarioChegada);

    boolean isTransferenciaExistente(Long id) throws Exception;

    void excluirTransferencia(Long id);

    void atualizarTransferencia(Long id, Long pacienteId, Long origemId, Long destinoId, Long meioTransporte, LocalDateTime horarioSaida, LocalDate horarioChegada);
}

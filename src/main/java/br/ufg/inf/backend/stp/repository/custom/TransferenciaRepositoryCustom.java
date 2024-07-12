package br.ufg.inf.backend.stp.repository.custom;

public interface TransferenciaRepositoryCustom {

    void inserirTransferencia(Long id, Long pacienteId, Long origemId, Long destinoId, Long meioTransporte, LocalDateTime horarioSaida, LocalDate horarioChegada);

    boolean isTransferenciaExistente(Long id) throws Exception;

    void excluirTransferencia(Long id);

    void atualizarTransferencia(Long id, Long pacienteId, Long origemId, Long destinoId, Long meioTransporte, LocalDateTime horarioSaida, LocalDate horarioChegada);
}

package br.ufg.inf.backend.stp.repository.custom;

public interface PacienteRepositoryCustom {

    void inserirPaciente(Long id, String nome, String cpf, String prontuario, String condicao);

    boolean isPacienteExistente(Long id) throws Exception;

    void excluirPaciente(Long id);

    void atualizarPaciente(Long id, String nome, String cpf, String prontuario, String condicao);
}

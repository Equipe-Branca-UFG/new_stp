package br.ufg.inf.backend.stp.repository.custom;

public interface UnidadeHospitalarRepositoryCustom {

    void inserirUnidadeHospitalar(Long id, String nome, String endereco, String telefone);

    boolean isUnidadeHospitalarExistente(Long id) throws Exception;

    void excluirUnidadeHospitalar(Long id);

    void atualizarUnidadeHospitalar(Long id, String nome, String endereco, String telefone);
}

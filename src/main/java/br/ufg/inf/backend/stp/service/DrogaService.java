package br.ufg.inf.backend.stp.service;

import br.ufg.inf.backend.stp.domain.droga.Droga;
import br.ufg.inf.backend.stp.domain.droga.dto.DrogaDTO;
import br.ufg.inf.backend.stp.infra.MensagemValidacao;
import br.ufg.inf.backend.stp.infra.enumeracao.TipoMensagem;
import br.ufg.inf.backend.stp.repository.impl.DrogaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DrogaService {

    @Autowired
    private DrogaRepository drogaRepository;

    @Transactional
    public MensagemValidacao criarDroga(DrogaDTO drogaDTO) throws Exception {
        MensagemValidacao mensagemValidacao = new MensagemValidacao();
        Droga droga = new Droga(drogaDTO);
        drogaRepository.save(droga);
        mensagemValidacao.setTipoMensagem(TipoMensagem.SUCESSO);
        mensagemValidacao.setMensagem("Droga salva com sucesso!!");
        return mensagemValidacao;
    }

    public MensagemValidacao alterarDroga(Long id, DrogaDTO drogaDTO) throws Exception {
        MensagemValidacao mensagemValidacao = new MensagemValidacao();
        Droga droga = drogaRepository.findById(id)
                .orElseThrow(() -> new Exception("Droga não encontrado"));
        droga.setNome(drogaDTO.getNome());
        droga.setDosagem(drogaDTO.getDosagem());
        droga.setUnidadeMedida(drogaDTO.getUnidadeMedida());
        drogaRepository.save(droga);
        mensagemValidacao.setTipoMensagem(TipoMensagem.SUCESSO);
        mensagemValidacao.setMensagem("Droga Alterada com sucesso!!");
        return mensagemValidacao;
    }

    public List<DrogaDTO> buscarTodasDrogas() {
        List<Droga> pacientes = drogaRepository.findAll();
        return pacientes.stream().map(Droga::toDrogaDTO).collect(Collectors.toList());
    }

    public DrogaDTO buscarDrogaPorId(Long id) throws Exception {
        Droga droga = drogaRepository.findById(id)
                .orElseThrow(() -> new Exception("Droga não encontrado"));
        return droga.toDrogaDTO();
    }

    public MensagemValidacao apagarDroga(Long id) throws Exception {
        MensagemValidacao mensagemValidacao = new MensagemValidacao();
        try {
            drogaRepository.deleteById(id);
            mensagemValidacao.setMensagem("Droga Excluída com sucesso!!");
        } catch (Exception e) {
            mensagemValidacao.setMensagem("Erro ao excluir droga: " + e.getMessage());
            e.printStackTrace();  // Loga a exceção para depuração
        }
        return mensagemValidacao;
    }
}

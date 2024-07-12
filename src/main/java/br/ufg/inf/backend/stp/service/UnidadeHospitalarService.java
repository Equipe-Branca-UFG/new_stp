package br.ufg.inf.backend.stp.service;

import br.ufg.inf.backend.stp.domain.unidadeHospitalar.UnidadeHospitalar;
import br.ufg.inf.backend.stp.domain.unidadeHospitalar.dto.UnidadeHospitalarDTO;
import br.ufg.inf.backend.stp.infra.MensagemValidacao;
import br.ufg.inf.backend.stp.infra.enumeracao.TipoMensagem;
import br.ufg.inf.backend.stp.repository.UnidadeHospitalarRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UnidadeHospitalarService {

    @Autowired
    private UnidadeHospitalarRepository unidadeHospitalarRepository;

    @Transactional
    public MensagemValidacao criarUnidadeHospitalar(UnidadeHospitalarDTO unidadeHospitalarDTO) throws Exception {
        MensagemValidacao mensagemValidacao = new MensagemValidacao();
        UnidadeHospitalar unidadeHospitalar = new UnidadeHospitalar(unidadeHospitalarDTO);
        unidadeHospitalarRepository.save(unidadeHospitalar);
        mensagemValidacao.setTipoMensagem(TipoMensagem.SUCESSO);
        mensagemValidacao.setMensagem("Unidade Hospitalar salva com sucesso!!");
        return mensagemValidacao;
    }

    public List<UnidadeHospitalarDTO> buscarTodasUnidades() {
        List<UnidadeHospitalar> unidades = unidadeHospitalarRepository.findAll();
        return unidades.stream().map(UnidadeHospitalar::toDTO).collect(Collectors.toList());
    }

    public UnidadeHospitalarDTO buscarUnidadePorId(Long id) throws Exception {
        UnidadeHospitalar unidade = unidadeHospitalarRepository.findById(id)
                .orElseThrow(() -> new Exception("Unidade Hospitalar não encontrada"));
        return unidade.toDTO();
    }
}

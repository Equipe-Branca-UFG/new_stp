package br.ufg.inf.backend.stp.service;

import br.ufg.inf.backend.stp.domain.transferencia.Transferencia;
import br.ufg.inf.backend.stp.domain.transferencia.dto.TransferenciaDTO;
import br.ufg.inf.backend.stp.infra.MensagemValidacao;
import br.ufg.inf.backend.stp.infra.enumeracao.TipoMensagem;
import br.ufg.inf.backend.stp.repository.TransferenciaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransferenciaService {

    @Autowired
    private TransferenciaRepository transferenciaRepository;

    @Transactional
    public MensagemValidacao criarTransferencia(TransferenciaDTO transferenciaDTO) throws Exception {
        MensagemValidacao mensagemValidacao = new MensagemValidacao();
        Transferencia transferencia = new Transferencia(transferenciaDTO);
        transferenciaRepository.save(transferencia);
        mensagemValidacao.setTipoMensagem(TipoMensagem.SUCESSO);
        mensagemValidacao.setMensagem("Transferência salva com sucesso!!");
        return mensagemValidacao;
    }

    public List<TransferenciaDTO> buscarTodasTransferencias() {
        List<Transferencia> transferencias = transferenciaRepository.findAll();
        return transferencias.stream().map(Transferencia::toDTO).collect(Collectors.toList());
    }

    public TransferenciaDTO buscarTransferenciaPorId(Long id) throws Exception {
        Transferencia transferencia = transferenciaRepository.findById(id)
                .orElseThrow(() -> new Exception("Transferência não encontrada"));
        return transferencia.toDTO();
    }
}

package br.ufg.inf.backend.stp.service;

import br.ufg.inf.backend.stp.domain.paciente.Paciente;
import br.ufg.inf.backend.stp.domain.transferencia.Transferencia;
import br.ufg.inf.backend.stp.domain.transferencia.dto.TransferenciaDTO;
import br.ufg.inf.backend.stp.infra.MensagemValidacao;
import br.ufg.inf.backend.stp.infra.enumeracao.TipoMensagem;
import br.ufg.inf.backend.stp.repository.impl.PacienteRepository;
import br.ufg.inf.backend.stp.repository.impl.TransferenciaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransferenciaService {

    @Autowired
    private TransferenciaRepository transferenciaRepository;
    @Autowired
    private PacienteRepository pacienteRepository;

    @Transactional
    public MensagemValidacao criarTransferencia(TransferenciaDTO transferenciaDTO) throws Exception {
        Paciente paciente = pacienteRepository.findById(transferenciaDTO.getPacienteId())
                .orElseThrow(() -> new Exception("Paciente não encontrado"));

        MensagemValidacao mensagemValidacao = new MensagemValidacao();
        Transferencia transferencia = new Transferencia(transferenciaDTO, paciente);
        transferenciaRepository.save(transferencia);
        mensagemValidacao.setTipoMensagem(TipoMensagem.SUCESSO);
        mensagemValidacao.setMensagem("Transferência salva com sucesso!!");
        return mensagemValidacao;
    }

    public List<TransferenciaDTO> buscarTodasTransferencias() {
        List<Transferencia> transferencias = transferenciaRepository.findAll();
        return transferencias.stream().map(Transferencia::toTransferenciaDTO).collect(Collectors.toList());
    }

    public TransferenciaDTO buscarTransferenciaPorId(Long id) throws Exception {
        Transferencia transferencia = transferenciaRepository.findById(id)
                .orElseThrow(() -> new Exception("Transferência não encontrada"));
        return transferencia.toTransferenciaDTO();
    }
}

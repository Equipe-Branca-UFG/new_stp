package br.ufg.inf.backend.stp.service;

import br.ufg.inf.backend.stp.domain.paciente.Paciente;
import br.ufg.inf.backend.stp.domain.transferencia.Transferencia;
import br.ufg.inf.backend.stp.domain.transferencia.dto.TransferenciaDTO;
import br.ufg.inf.backend.stp.domain.transferencia.dto.TransferenciaDTOResponse;
import br.ufg.inf.backend.stp.domain.unidadeHospitalar.UnidadeHospitalar;
import br.ufg.inf.backend.stp.infra.MensagemValidacao;
import br.ufg.inf.backend.stp.infra.enumeracao.TipoMensagem;
import br.ufg.inf.backend.stp.repository.impl.PacienteRepository;
import br.ufg.inf.backend.stp.repository.impl.TransferenciaRepository;
import br.ufg.inf.backend.stp.repository.impl.UnidadeHospitalarRepository;
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
    @Autowired
    private UnidadeHospitalarRepository unidadeHospitalarRepository;

    @Transactional
    public MensagemValidacao criarTransferencia(TransferenciaDTO transferenciaDTO) throws Exception {
        Paciente paciente = pacienteRepository.findById(transferenciaDTO.getPacienteId())
                .orElseThrow(() -> new Exception("Paciente não encontrado"));
        
        UnidadeHospitalar origem = unidadeHospitalarRepository.findById(transferenciaDTO.getOrigemId())
        .orElseThrow(() -> new Exception("Unidade Origem não encontrada"));

        UnidadeHospitalar destino = unidadeHospitalarRepository.findById(transferenciaDTO.getDestinoId())
        .orElseThrow(() -> new Exception("Unidade Destino não encontrada"));


        MensagemValidacao mensagemValidacao = new MensagemValidacao();
        Transferencia transferencia = new Transferencia(transferenciaDTO, paciente, origem, destino);
        transferenciaRepository.save(transferencia);
        mensagemValidacao.setTipoMensagem(TipoMensagem.SUCESSO);
        mensagemValidacao.setMensagem("Transferência salva com sucesso!! O Paciente: " + paciente.getNome() + " Tem Previsão de Chegada ao Hospital: " + destino.getNome() + " Na seguinte data e hora: " + transferencia.getHorarioChegada());
        return mensagemValidacao; 
    }

    public List<TransferenciaDTOResponse> buscarTodasTransferencias() {
        List<Transferencia> transferencias = transferenciaRepository.findAll();
        return transferencias.stream().map(Transferencia::toTransferenciaDTOResponse).collect(Collectors.toList());
    }

    public TransferenciaDTOResponse buscarTransferenciaPorId(Long id) throws Exception {
        Transferencia transferencia = transferenciaRepository.findById(id)
                .orElseThrow(() -> new Exception("Transferência não encontrada"));
        return transferencia.toTransferenciaDTOResponse();
    }

    public MensagemValidacao apagarTransferencia(Long id) throws Exception {
        MensagemValidacao mensagemValidacao = new MensagemValidacao();
        try {
            transferenciaRepository.deleteById(id);
            mensagemValidacao.setMensagem("Transferencia Excluída com sucesso!!");
        } catch (Exception e) {
            mensagemValidacao.setMensagem("Erro ao excluir transferencia: " + e.getMessage());
            e.printStackTrace();  // Loga a exceção para depuração
        }
        return mensagemValidacao;
    }
}

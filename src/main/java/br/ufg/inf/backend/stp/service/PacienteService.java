package br.ufg.inf.backend.stp.service;

import br.ufg.inf.backend.stp.domain.paciente.Paciente;
import br.ufg.inf.backend.stp.domain.paciente.dto.PacienteDTO;
import br.ufg.inf.backend.stp.domain.paciente.Paciente;
import br.ufg.inf.backend.stp.domain.paciente.dto.CreatePacienteDTO;
import br.ufg.inf.backend.stp.domain.paciente.dto.PacienteDTO;
import br.ufg.inf.backend.stp.infra.MensagemValidacao;
import br.ufg.inf.backend.stp.infra.enumeracao.TipoMensagem;
import br.ufg.inf.backend.stp.repository.impl.PacienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Transactional
    public MensagemValidacao criarPaciente(CreatePacienteDTO createPacienteDTO) throws Exception {
        MensagemValidacao mensagemValidacao = new MensagemValidacao();
        Paciente paciente = new Paciente(createPacienteDTO);
        pacienteRepository.save(paciente);
        mensagemValidacao.setTipoMensagem(TipoMensagem.SUCESSO);
        mensagemValidacao.setMensagem("Paciente salvo com sucesso!!");
        return mensagemValidacao;
    }

    public List<PacienteDTO> buscarTodosPacientes() {
        List<Paciente> pacientes = pacienteRepository.findAll();
        return pacientes.stream().map(Paciente::toPacienteDTO).collect(Collectors.toList());
    }

    public PacienteDTO buscarPacientePorId(Long id) throws Exception {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new Exception("Paciente não encontrado"));
        return paciente.toPacienteDTO();
    }

    public MensagemValidacao alterarPaciente(Long id, PacienteDTO pacienteDTO) throws Exception {
        MensagemValidacao mensagemValidacao = new MensagemValidacao();
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new Exception("Paciente não encontrado"));
        paciente.setNome(pacienteDTO.getNome());
        paciente.setCpf(pacienteDTO.getCpf());
        pacienteRepository.save(paciente);
        mensagemValidacao.setTipoMensagem(TipoMensagem.SUCESSO);
        mensagemValidacao.setMensagem("Paciente Alterado com sucesso!!");
        return mensagemValidacao;
    }

    public MensagemValidacao apagarPaciente(Long id) throws Exception {
        MensagemValidacao mensagemValidacao = new MensagemValidacao();
        try {
            pacienteRepository.deleteById(id);
            mensagemValidacao.setMensagem("Paciente excluído com sucesso!!");
        } catch (Exception e) {
            mensagemValidacao.setMensagem("Erro ao excluir paciente: " + e.getMessage());
            e.printStackTrace(); // Loga a exceção para depuração
        }
        return mensagemValidacao;
    }
}

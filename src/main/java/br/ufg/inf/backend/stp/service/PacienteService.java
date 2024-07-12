package br.ufg.inf.backend.stp.service;

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
import java.util.Optional;
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
        return pacientes.stream().map(Paciente::toDTO).collect(Collectors.toList());
    }

    public PacienteDTO buscarPacientePorId(Long id) throws Exception {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new Exception("Paciente não encontrado"));
        return paciente.toDTO();
    }
}

package br.ufg.inf.backend.stp.service;

import br.ufg.inf.backend.stp.domain.paciente.Paciente;
import br.ufg.inf.backend.stp.domain.prontuario.Prontuario;
import br.ufg.inf.backend.stp.domain.prontuario.dto.ProntuarioDTO;
import br.ufg.inf.backend.stp.infra.MensagemValidacao;
import br.ufg.inf.backend.stp.infra.enumeracao.TipoMensagem;
import br.ufg.inf.backend.stp.repository.impl.PacienteRepository;
import br.ufg.inf.backend.stp.repository.impl.ProntuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProntuarioService {

    @Autowired
    private ProntuarioRepository prontuarioRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Transactional
    public MensagemValidacao criarProntuario(ProntuarioDTO prontuarioDTO) throws Exception {
        MensagemValidacao mensagemValidacao = new MensagemValidacao();

        Paciente paciente = pacienteRepository.findById(prontuarioDTO.getPacienteId())
        .orElseThrow(() -> new Exception("Paciente não encontrado"));

        Prontuario prontuario = new Prontuario(prontuarioDTO, paciente);
        prontuarioRepository.save(prontuario);
        mensagemValidacao.setTipoMensagem(TipoMensagem.SUCESSO);
        mensagemValidacao.setMensagem("Prontuario salva com sucesso!!");
        return mensagemValidacao;
    }

    public List<ProntuarioDTO> buscarTodasProntuarios() {
        List<Prontuario> pacientes = prontuarioRepository.findAll();
        return pacientes.stream().map(Prontuario::toProntuarioDTO).collect(Collectors.toList());
    }

    public ProntuarioDTO buscarProntuarioPorId(Long id) throws Exception {
        Prontuario prontuario = prontuarioRepository.findById(id)
                .orElseThrow(() -> new Exception("Prontuario não encontrado"));
        return prontuario.toProntuarioDTO();
    }

        public MensagemValidacao apagarProntuario(Long id) throws Exception {
        MensagemValidacao mensagemValidacao = new MensagemValidacao();
        try {
            prontuarioRepository.deleteById(id);
            mensagemValidacao.setMensagem("Prontuario Excluído com sucesso!!");
        } catch (Exception e) {
            mensagemValidacao.setMensagem("Erro ao excluir prontuario: " + e.getMessage());
            e.printStackTrace();  // Loga a exceção para depuração
        }
        return mensagemValidacao;
    }
}

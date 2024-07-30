package br.ufg.inf.backend.stp.controller;

import br.ufg.inf.backend.stp.domain.paciente.dto.PacienteDTO;
import br.ufg.inf.backend.stp.domain.paciente.dto.CreatePacienteDTO;
import br.ufg.inf.backend.stp.domain.paciente.dto.PacienteDTO;
import br.ufg.inf.backend.stp.infra.MensagemValidacao;
import br.ufg.inf.backend.stp.service.PacienteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paciente")
@Tag(name = "03. Paciente")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @PostMapping("/criar")
    @Transactional
    public ResponseEntity<MensagemValidacao> createPaciente(@RequestBody CreatePacienteDTO createPacienteDTO)
            throws Exception {
        MensagemValidacao mensagemValidacao = this.pacienteService.criarPaciente(createPacienteDTO);
        mensagemValidacao.setMensagem(null);
        return new ResponseEntity<>(mensagemValidacao, HttpStatus.CREATED);
    }

    @GetMapping("/buscarPacientes")
    public ResponseEntity findAllPacientes() throws Exception {
        List<PacienteDTO> pacientes = this.pacienteService.buscarTodosPacientes();
        return ResponseEntity.ok(pacientes);
    }

    @GetMapping("/buscarPaciente")
    public ResponseEntity findPacienteById(@RequestParam Long id) throws Exception {
        PacienteDTO paciente = this.pacienteService.buscarPacientePorId(id);
        return ResponseEntity.ok(paciente);
    }

    @PutMapping("/alterar/{id}")
    public ResponseEntity<MensagemValidacao> alterarPaciente(@PathVariable Long id, @RequestBody PacienteDTO pacienteDTO) {
        MensagemValidacao mensagemValidacao = new MensagemValidacao();
        try {
            mensagemValidacao = this.pacienteService.alterarPaciente(id, pacienteDTO);
            return new ResponseEntity<>(mensagemValidacao, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            mensagemValidacao.setMensagem("Paciente não encontrada.");
            return new ResponseEntity<>(mensagemValidacao, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            mensagemValidacao.setMensagem("Erro ao alterar paciente: " + e.getMessage());
            return new ResponseEntity<>(mensagemValidacao, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<MensagemValidacao> deletePaciente(@PathVariable Long id) {
        MensagemValidacao mensagemValidacao = new MensagemValidacao();
        try {
            mensagemValidacao = this.pacienteService.apagarPaciente(id);
            return new ResponseEntity<>(mensagemValidacao, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            mensagemValidacao.setMensagem("Paciente não encontrado.");
            return new ResponseEntity<>(mensagemValidacao, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            mensagemValidacao.setMensagem("Erro ao excluir paciente: " + e.getMessage());
            return new ResponseEntity<>(mensagemValidacao, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

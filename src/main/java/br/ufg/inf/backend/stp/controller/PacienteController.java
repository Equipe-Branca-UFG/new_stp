package br.ufg.inf.backend.stp.controller;

import br.ufg.inf.backend.stp.domain.paciente.dto.CreatePacienteDTO;
import br.ufg.inf.backend.stp.domain.paciente.dto.PacienteDTO;
import br.ufg.inf.backend.stp.infra.MensagemValidacao;
import br.ufg.inf.backend.stp.service.PacienteService;
import io.swagger.v3.oas.annotations.tags.Tag;
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
    public ResponseEntity<MensagemValidacao> createPaciente(@RequestBody CreatePacienteDTO createPacienteDTO) throws Exception {
        MensagemValidacao mensagemValidacao = this.pacienteService.criarPaciente(createPacienteDTO);
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
}

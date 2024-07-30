package br.ufg.inf.backend.stp.controller;

import br.ufg.inf.backend.stp.domain.prontuario.dto.ProntuarioDTO;
import br.ufg.inf.backend.stp.infra.MensagemValidacao;
import br.ufg.inf.backend.stp.service.ProntuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prontuario")
@Tag(name = "04. Prontuario")
public class ProntuarioController {

    @Autowired
    private ProntuarioService pronturaioService;

    @PostMapping("/criar")
    @Transactional
    public ResponseEntity<MensagemValidacao> createProntuario(@RequestBody ProntuarioDTO pronturaioDTO)
            throws Exception {
        MensagemValidacao mensagemValidacao = this.pronturaioService.criarProntuario(pronturaioDTO);
        return new ResponseEntity<>(mensagemValidacao, HttpStatus.CREATED);
    }

    @GetMapping("/buscarProntuarios")
    public ResponseEntity findAllProntuarios() throws Exception {
        List<ProntuarioDTO> prontuarios = this.pronturaioService.buscarTodasProntuarios();
        return ResponseEntity.ok(prontuarios);
    }

    @GetMapping("/buscarProntuario")
    public ResponseEntity findProntuarioById(@RequestParam Long id) throws Exception {
        ProntuarioDTO pronturaio = this.pronturaioService.buscarProntuarioPorId(id);
        return ResponseEntity.ok(pronturaio);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<MensagemValidacao> deleteProntario(@PathVariable Long id) {
        MensagemValidacao mensagemValidacao = new MensagemValidacao();
        try {
            mensagemValidacao = this.pronturaioService.apagarProntuario(id);
            return new ResponseEntity<>(mensagemValidacao, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            mensagemValidacao.setMensagem("Prontario não encontrado.");
            return new ResponseEntity<>(mensagemValidacao, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            mensagemValidacao.setMensagem("Erro ao excluir prontuario: " + e.getMessage());
            return new ResponseEntity<>(mensagemValidacao, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

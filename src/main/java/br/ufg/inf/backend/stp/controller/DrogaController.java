package br.ufg.inf.backend.stp.controller;

import br.ufg.inf.backend.stp.domain.droga.dto.DrogaDTO;
import br.ufg.inf.backend.stp.infra.MensagemValidacao;
import br.ufg.inf.backend.stp.service.DrogaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/droga")
@Tag(name = "04. Droga")
public class DrogaController {

    @Autowired
    private DrogaService drogaService;

    @PostMapping("/criar")
    @Transactional
    public ResponseEntity<MensagemValidacao> createDroga(@RequestBody DrogaDTO drogaDTO) throws Exception {
        MensagemValidacao mensagemValidacao = this.drogaService.criarDroga(drogaDTO);
        return new ResponseEntity<>(mensagemValidacao, HttpStatus.CREATED);
    }

    @GetMapping("/buscarDrogas")
    public ResponseEntity findAllDrogas() throws Exception {
        List<DrogaDTO> drogas = this.drogaService.buscarTodasDrogas();
        return ResponseEntity.ok(drogas);
    }

    @GetMapping("/buscarDroga")
    public ResponseEntity findDrogaById(@RequestParam Long id) throws Exception {
        DrogaDTO droga = this.drogaService.buscarDrogaPorId(id);
        return ResponseEntity.ok(droga);
    }

    @PutMapping("/alterar/{id}")
    public ResponseEntity<MensagemValidacao> alterarDroga(@PathVariable Long id, @RequestBody DrogaDTO drogaDTO){
         MensagemValidacao mensagemValidacao = new MensagemValidacao();
        try {
            mensagemValidacao = this.drogaService.alterarDroga(id, drogaDTO);
            return new ResponseEntity<>(mensagemValidacao, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            mensagemValidacao.setMensagem("Droga não encontrada.");
            return new ResponseEntity<>(mensagemValidacao, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            mensagemValidacao.setMensagem("Erro ao alterar droga: " + e.getMessage());
            return new ResponseEntity<>(mensagemValidacao, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<MensagemValidacao> deleteDroga(@PathVariable Long id) {
         MensagemValidacao mensagemValidacao = new MensagemValidacao();
        try {
            mensagemValidacao = this.drogaService.apagarDroga(id);
            return new ResponseEntity<>(mensagemValidacao, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            mensagemValidacao.setMensagem("Droga não encontrada.");
            return new ResponseEntity<>(mensagemValidacao, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            mensagemValidacao.setMensagem("Erro ao excluir droga: " + e.getMessage());
            return new ResponseEntity<>(mensagemValidacao, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

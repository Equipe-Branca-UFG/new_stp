package br.ufg.inf.backend.stp.controller;

import br.ufg.inf.backend.stp.domain.unidadeHospitalar.dto.UnidadeHospitalarDTO;
import br.ufg.inf.backend.stp.domain.unidadeHospitalar.UnidadeHospitalar;
import br.ufg.inf.backend.stp.domain.unidadeHospitalar.dto.UnidadeHospitalarDTO;
import br.ufg.inf.backend.stp.infra.MensagemValidacao;
import br.ufg.inf.backend.stp.service.UnidadeHospitalarService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/unidadeHospitalar")
@Tag(name = "01. Unidade Hospitalar")
public class UnidadeHospitalarController {

    @Autowired
    private UnidadeHospitalarService unidadeHospitalarService;

    @PostMapping("/criar")
    @Transactional
    public ResponseEntity<MensagemValidacao> createUnidadeHospitalar(
            @RequestBody UnidadeHospitalarDTO unidadeHospitalarDTO) throws Exception {
        MensagemValidacao mensagemValidacao = this.unidadeHospitalarService
                .criarUnidadeHospitalar(unidadeHospitalarDTO);
        return new ResponseEntity<>(mensagemValidacao, HttpStatus.CREATED);
    }

    @GetMapping("/buscarUnidades")
    public ResponseEntity findAllUnidades() throws Exception {
        List<UnidadeHospitalarDTO> unidades = this.unidadeHospitalarService.buscarTodasUnidades();
        return ResponseEntity.ok(unidades);
    }

    @GetMapping("/buscarUnidade")
    public ResponseEntity findUnidadeById(@RequestParam Long id) throws Exception {
        UnidadeHospitalarDTO unidade = this.unidadeHospitalarService.buscarUnidadePorId(id);
        return ResponseEntity.ok(unidade);
    }

    @PutMapping("/alterar/{id}")
    public ResponseEntity<MensagemValidacao> alterarUnidadeHospitalar(@PathVariable Long id, @RequestBody UnidadeHospitalarDTO unidadeHospitalarDTO) {
        MensagemValidacao mensagemValidacao = new MensagemValidacao();
        try {
            mensagemValidacao = this.unidadeHospitalarService.alterarUnidadeHospitalar(id, unidadeHospitalarDTO);
            return new ResponseEntity<>(mensagemValidacao, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            mensagemValidacao.setMensagem("Unidade Hospitalar não encontrada.");
            return new ResponseEntity<>(mensagemValidacao, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            mensagemValidacao.setMensagem("Erro ao alterar unidade Hospitalar: " + e.getMessage());
            return new ResponseEntity<>(mensagemValidacao, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<MensagemValidacao> deleteUnidadeHospitalar(@PathVariable Long id) {
        MensagemValidacao mensagemValidacao = new MensagemValidacao();
        try {
            mensagemValidacao = this.unidadeHospitalarService.apagarUnidadeHospitalar(id);
            return new ResponseEntity<>(mensagemValidacao, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            mensagemValidacao.setMensagem("Unidade hospitalar não encontrado.");
            return new ResponseEntity<>(mensagemValidacao, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            mensagemValidacao.setMensagem("Erro ao excluir unidade hospitalar: " + e.getMessage());
            return new ResponseEntity<>(mensagemValidacao, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

package br.ufg.inf.backend.stp.controller;

import br.ufg.inf.backend.stp.domain.unidadeHospitalar.UnidadeHospitalar;
import br.ufg.inf.backend.stp.domain.unidadeHospitalar.dto.UnidadeHospitalarDTO;
import br.ufg.inf.backend.stp.infra.MensagemValidacao;
import br.ufg.inf.backend.stp.service.UnidadeHospitalarService;
import io.swagger.v3.oas.annotations.tags.Tag;
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
    public ResponseEntity<MensagemValidacao> createUnidadeHospitalar(@RequestBody UnidadeHospitalarDTO unidadeHospitalarDTO) throws Exception {
        MensagemValidacao mensagemValidacao = this.unidadeHospitalarService.criarUnidadeHospitalar(unidadeHospitalarDTO);
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
}

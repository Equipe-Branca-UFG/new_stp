package br.ufg.inf.backend.stp.controller;

import br.ufg.inf.backend.stp.domain.transferencia.dto.TransferenciaDTO;
import br.ufg.inf.backend.stp.domain.transferencia.dto.TransferenciaDTOResponse;
import br.ufg.inf.backend.stp.infra.MensagemValidacao;
import br.ufg.inf.backend.stp.service.TransferenciaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transferencia")
@Tag(name = "02. Transferencia")
public class TransferenciaController {

    @Autowired
    private TransferenciaService transferenciaService;

    @PostMapping("/criar")
    @Transactional
    public ResponseEntity<MensagemValidacao> createTransferencia(@RequestBody TransferenciaDTO transferenciaDTO)
            throws Exception {
        MensagemValidacao mensagemValidacao = this.transferenciaService.criarTransferencia(transferenciaDTO);
        return new ResponseEntity<>(mensagemValidacao, HttpStatus.CREATED);
    }

    @GetMapping("/buscarTransferencias")
    public ResponseEntity findAllTransferencias() throws Exception {
        List<TransferenciaDTOResponse> transferencias = this.transferenciaService.buscarTodasTransferencias();
        return ResponseEntity.ok(transferencias);
    }

    @GetMapping("/buscarTransferencia")
    public ResponseEntity findTransferenciaById(@RequestParam Long id) throws Exception {
        TransferenciaDTOResponse transferencia = this.transferenciaService.buscarTransferenciaPorId(id);
        return ResponseEntity.ok(transferencia);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<MensagemValidacao> deleteTransferencia(@PathVariable Long id) {
        MensagemValidacao mensagemValidacao = new MensagemValidacao();
        try {
            mensagemValidacao = this.transferenciaService.apagarTransferencia(id);
            return new ResponseEntity<>(mensagemValidacao, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            mensagemValidacao.setMensagem("Transferencia não encontrado.");
            return new ResponseEntity<>(mensagemValidacao, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            mensagemValidacao.setMensagem("Erro ao excluir transferencia: " + e.getMessage());
            return new ResponseEntity<>(mensagemValidacao, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

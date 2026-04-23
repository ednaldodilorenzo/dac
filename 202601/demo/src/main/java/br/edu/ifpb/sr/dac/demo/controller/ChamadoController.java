package br.edu.ifpb.sr.dac.demo.controller;

import br.edu.ifpb.sr.dac.demo.dto.GetChamadosRespDTO;
import br.edu.ifpb.sr.dac.demo.dto.PostChamadoReqDTO;
import br.edu.ifpb.sr.dac.demo.service.chamado.ChamadoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/v1/chamados")
public class ChamadoController {
    private final ChamadoService chamadoService;

    public ChamadoController(ChamadoService chamadoService) {
        this.chamadoService = chamadoService;
    }

    @PostMapping
    public ResponseEntity<String> postChamado(Principal principal, @RequestBody PostChamadoReqDTO dto) throws URISyntaxException {
        Long idNovoChamado = this.chamadoService.salvar(dto, Long.parseLong(principal.getName()));
        return ResponseEntity.created(new URI("/v1/chamados/"+idNovoChamado)).build();
    }

    @GetMapping("/usuario")
    public ResponseEntity<List<GetChamadosRespDTO>> getAllByUsuario(Principal principal) {
        return ResponseEntity.ok(this.chamadoService.buscarTodosPorUsuario(Long.parseLong(principal.getName())));
    }
}

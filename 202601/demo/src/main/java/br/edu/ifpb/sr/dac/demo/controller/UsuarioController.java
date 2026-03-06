package br.edu.ifpb.sr.dac.demo.controller;

import br.edu.ifpb.sr.dac.demo.dto.GetUsuariosRespDTO;
import br.edu.ifpb.sr.dac.demo.dto.PostUsuarioDTO;
import br.edu.ifpb.sr.dac.demo.service.usuario.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/administrador")
    public ResponseEntity<Boolean> postUsuarioAdm(@RequestBody PostUsuarioDTO dto) {
        this.usuarioService.saveAdm(dto);
        return ResponseEntity.created(URI.create("/1")).body(Boolean.TRUE);
    }

    @GetMapping("/administador")
    public ResponseEntity<List<GetUsuariosRespDTO>> getAllUsuariosAdm() {
        return ResponseEntity.ok(this.usuarioService.listAllAdm());
    }
}

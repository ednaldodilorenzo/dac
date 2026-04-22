package br.edu.ifpb.sr.dac.demo.controller;

import br.edu.ifpb.sr.dac.demo.dto.GetUsuariosRespDTO;
import br.edu.ifpb.sr.dac.demo.dto.PostUsuarioDTO;
import br.edu.ifpb.sr.dac.demo.service.usuario.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.context.annotation.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/v1/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/administrador")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Boolean> postUsuarioAdm(@RequestBody @Valid PostUsuarioDTO dto) {
        this.usuarioService.saveAdm(dto);
        return ResponseEntity.created(URI.create("/1")).body(Boolean.TRUE);
    }

    @GetMapping("/administrador")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<GetUsuariosRespDTO>> getAllUsuariosAdm(
            @PageableDefault(size = 10, sort = "nome") Pageable pageable) {
        return ResponseEntity.ok(this.usuarioService.listAllAdm(pageable));
    }
}

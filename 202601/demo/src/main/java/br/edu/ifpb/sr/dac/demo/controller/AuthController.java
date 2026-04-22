package br.edu.ifpb.sr.dac.demo.controller;

import br.edu.ifpb.sr.dac.demo.dto.LoginReqDTO;
import br.edu.ifpb.sr.dac.demo.dto.LoginRespDTO;
import br.edu.ifpb.sr.dac.demo.dto.PostUsuarioDTO;
import br.edu.ifpb.sr.dac.demo.service.token.TokenService;
import br.edu.ifpb.sr.dac.demo.service.usuario.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UsuarioService usuarioService;

    public AuthController(AuthenticationManager authenticationManager, TokenService tokenService, UsuarioService usuarioService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    public LoginRespDTO login(@RequestBody LoginReqDTO loginReqDTO) {
        Authentication auth = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginReqDTO.username(),
                loginReqDTO.password()
        ));
        String token = this.tokenService.generateToken(auth);
        return new LoginRespDTO(token);
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody PostUsuarioDTO postUsuarioDTO) {
        this.usuarioService.saveUsuario(postUsuarioDTO);
        return ResponseEntity.ok("");
    }

}

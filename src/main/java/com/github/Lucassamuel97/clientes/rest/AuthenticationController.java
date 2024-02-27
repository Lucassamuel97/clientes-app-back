package com.github.Lucassamuel97.clientes.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.Lucassamuel97.clientes.model.entity.User;
import com.github.Lucassamuel97.clientes.model.repository.UserRepository;
import com.github.Lucassamuel97.clientes.rest.dto.AuthenticationDTO;
import com.github.Lucassamuel97.clientes.rest.dto.LoginResponseDTO;
import com.github.Lucassamuel97.clientes.rest.dto.RegisterDTO;
import com.github.Lucassamuel97.clientes.services.TokenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
	
	 @Autowired
	    private AuthenticationManager authenticationManager;
	    @Autowired
	    private UserRepository repository;
	    @Autowired
	    private TokenService tokenService;

	    @PostMapping("/login")
	    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
	        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
	        var auth = this.authenticationManager.authenticate(usernamePassword);

	        var token = tokenService.generateToken((User) auth.getPrincipal());

	        return ResponseEntity.ok(new LoginResponseDTO(token));
	    }

	    @PostMapping("/register")
	    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){
	        if(this.repository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

	        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
	        User newUser = new User(data.login(), encryptedPassword, data.role());

	        this.repository.save(newUser);

	        return ResponseEntity.ok().build();
	    }
}

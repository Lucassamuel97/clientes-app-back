package com.github.Lucassamuel97.clientes.rest.dto;

import com.github.Lucassamuel97.clientes.util.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {

}

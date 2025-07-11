package com.eloskar.restaurante.services;

import com.eloskar.restaurante.DAO.TokensDAO;
import com.eloskar.restaurante.DAO.UsuarioDAO;
import com.eloskar.restaurante.DTO.TokensDTO;
import com.eloskar.restaurante.DTO.UsuarioDTO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TokensService {
    private TokensDAO tokensDAO;

    public TokensService() {
        this.tokensDAO = new TokensDAO();
    }

    public boolean insertarToken(TokensDTO dto){
        dto.setIdUser(dto.getIdUser());
        dto.setToken(dto.getToken());
        dto.setExpiracion(dto.getExpiracion());
        dto.setUsado(dto.isUsado());

        return tokensDAO.insertarToken(dto) > 0;
    }

    public TokensDTO buscarToken(String token){
        return tokensDAO.buscarPorToken(token);
    }

    public boolean marcarTokenUsado(String token){
        return tokensDAO.marcarToken(token) > 0;
    }

    public boolean tokenExpirado(String fechaExpiracion) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSSS");
        LocalDateTime expiracion = LocalDateTime.parse(fechaExpiracion, formatter);
        return expiracion.isBefore(LocalDateTime.now());
    }
}

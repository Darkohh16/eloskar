package com.eloskar.restaurante.services;

import com.eloskar.restaurante.DAO.UsuarioDAO;
import com.eloskar.restaurante.DTO.UsuarioDTO;

import java.util.List;

public class UsuarioService {
    private UsuarioDAO usuarioDAO;

    public UsuarioService() {
        this.usuarioDAO = new UsuarioDAO();
    }

    public boolean insertarU(UsuarioDTO dto){
        dto.setDni(dto.getDni());
        dto.setCel(dto.getCel());
        dto.setNombre(dto.getNombre());
        dto.setCorreo(dto.getCorreo());
        dto.setPassword(dto.getPassword());
        dto.setRol("cliente");//Estado base

        return usuarioDAO.insertU(dto) > 0;
    }

    public boolean actualizarU(UsuarioDTO dto){
        dto.setDni(dto.getDni());
        dto.setCel(dto.getCel());
        dto.setNombre(dto.getNombre());
        dto.setCorreo(dto.getCorreo());
        dto.setRol(dto.getRol());
        dto.setIdUser(dto.getIdUser());
        dto.setPassword(dto.getPassword());

        return usuarioDAO.updateU(dto) > 0;

    }

    public boolean eliminarU(int id){

        return usuarioDAO.deleteU(id) > 0;
    }

    public List<UsuarioDTO>  cargarDatosUsuarios(String filtro){
        if (filtro.isBlank()){
            filtro = "";
        }
        return usuarioDAO.buscarTodosU(filtro);
    }
}


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
        // Validaciones
        if (dto.getDni() == null || dto.getDni().trim().isEmpty()) return false;
        if (dto.getCel() == null || dto.getCel().trim().isEmpty()) return false;
        if (dto.getNombre() == null || dto.getNombre().trim().isEmpty()) return false;
        if (dto.getCorreo() == null || dto.getCorreo().trim().isEmpty()) return false;
        if (dto.getPassword() == null || dto.getPassword().trim().isEmpty()) return false;
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

    public int validarLogin(String correo, String password){

        return usuarioDAO.login(correo, password);
    }

    public String obtenerRol(int idUser){

        return usuarioDAO.buscarPrivilegios(idUser);
    }

    public int obtenerId(String correo){

        return usuarioDAO.buscarCorreo(correo);
    }

    public UsuarioDTO obtenerDatosRecuperacion(int idUser){

        return usuarioDAO.buscarU(idUser);
    }

    public boolean cambiarPass(int idUser, String password){
        return usuarioDAO.updatePass(idUser, password) > 0;
    }

    public boolean actualizarPerfil(UsuarioDTO dto) {
        dto.setIdUser(dto.getIdUser());
        dto.setNombre(dto.getNombre());
        dto.setCorreo(dto.getCorreo());
        dto.setCel(dto.getCel());
        dto.setDni(dto.getDni());
        dto.setPassword(dto.getPassword());
        return usuarioDAO.updatePerfil(dto) > 0;
    }

    public int contarUsuarios() {
        return usuarioDAO.contarUsuarios();
    }
}


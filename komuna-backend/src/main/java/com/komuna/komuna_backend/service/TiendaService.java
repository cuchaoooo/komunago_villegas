package com.komuna.komuna_backend.service;
    
import com.komuna.komuna_backend.dto.TiendaPublicView;
import com.komuna.komuna_backend.dto.TiendaUpdateRequest;
import com.komuna.komuna_backend.dto.VendedorRegistroRequest;
import com.komuna.komuna_backend.dto.VendedorRegistroResponse;
import com.komuna.komuna_backend.entity.Tienda;
import com.komuna.komuna_backend.exception.DuplicateResourceException;
import com.komuna.komuna_backend.exception.InvalidCredentialsException;
import com.komuna.komuna_backend.exception.ResourceNotFoundException;
import com.komuna.komuna_backend.repository.TiendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TiendaService {

    private final TiendaRepository tiendaRepository;
    private static final SecureRandom secureRandom = new SecureRandom();
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();

    @Autowired
    public TiendaService(TiendaRepository tiendaRepository) {
        this.tiendaRepository = tiendaRepository;
    }

    private String generarCodigoUnico() {
        String codigo;
        do {
            byte[] randomBytes = new byte[8]; // 8 bytes = 64 bits, results in about 10-11 chars Base64 URL safe
            secureRandom.nextBytes(randomBytes);
            codigo = base64Encoder.encodeToString(randomBytes).substring(0, 8); // Tomar los primeros 8 caracteres
        } while (tiendaRepository.existsByCodigoUnico(codigo));
        return codigo;
    }

    @Transactional
    public VendedorRegistroResponse registrarVendedor(VendedorRegistroRequest request) {
        if (tiendaRepository.existsByCorreoElectronico(request.getCorreoElectronico())) {
            throw new DuplicateResourceException("El correo electrónico '" + request.getCorreoElectronico() + "' ya está registrado.");
        }

        Tienda tienda = new Tienda();
        tienda.setNombreTienda(request.getNombreTienda());
        tienda.setCorreoElectronico(request.getCorreoElectronico());
        tienda.setTipoNegocio(request.getTipoNegocio());
        tienda.setCodigoUnico(generarCodigoUnico());

        Tienda tiendaGuardada = tiendaRepository.save(tienda);

        return new VendedorRegistroResponse(
                tiendaGuardada.getId(),
                tiendaGuardada.getNombreTienda(),
                tiendaGuardada.getCorreoElectronico(),
                tiendaGuardada.getCodigoUnico(),
                "Vendedor registrado exitosamente. Su código de acceso es: " + tiendaGuardada.getCodigoUnico() + ". Por favor, guárdelo en un lugar seguro."
        );
    }

    @Transactional(readOnly = true)
    public Tienda loginVendedor(String codigoUnico) {
        return tiendaRepository.findByCodigoUnico(codigoUnico)
                .orElseThrow(() -> new InvalidCredentialsException("Código de vendedor inválido."));
    }
    
    @Transactional(readOnly = true)
    public Optional<String> recuperarCodigo(String email) {
        return tiendaRepository.findByCorreoElectronico(email)
                .map(Tienda::getCodigoUnico);
    }

    @Transactional
    public Tienda actualizarTienda(String codigoUnicoVendedor, TiendaUpdateRequest request) {
        Tienda tienda = tiendaRepository.findByCodigoUnico(codigoUnicoVendedor)
                .orElseThrow(() -> new ResourceNotFoundException("Tienda no encontrada con el código proporcionado."));

        if (request.getNombreTienda() != null) tienda.setNombreTienda(request.getNombreTienda());
        if (request.getTipoNegocio() != null) tienda.setTipoNegocio(request.getTipoNegocio());
        if (request.getFotosLocal() != null) tienda.setFotosLocal(request.getFotosLocal());
        if (request.getCupoPersonas() != null) tienda.setCupoPersonas(request.getCupoPersonas());
        if (request.getHorarioAtencion() != null) tienda.setHorarioAtencion(request.getHorarioAtencion());
        if (request.getUbicacionExacta() != null) tienda.setUbicacionExacta(request.getUbicacionExacta());
        if (request.getCartaMenuPrecios() != null) tienda.setCartaMenuPrecios(request.getCartaMenuPrecios());
        if (request.getEnlacesRedesSociales() != null) tienda.setEnlacesRedesSociales(request.getEnlacesRedesSociales());
        
        return tiendaRepository.save(tienda);
    }

    @Transactional(readOnly = true)
    public List<TiendaPublicView> obtenerTodasLasTiendasPublicas() {
        return tiendaRepository.findAll().stream()
                .map(TiendaPublicView::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public TiendaPublicView obtenerTiendaPublicaPorId(Long id) {
        return tiendaRepository.findById(id)
                .map(TiendaPublicView::new)
                .orElseThrow(() -> new ResourceNotFoundException("Tienda no encontrada con ID: " + id));
    }
}
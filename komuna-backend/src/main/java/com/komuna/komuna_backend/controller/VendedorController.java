package com.komuna.komuna_backend.controller;

import com.komuna.komuna_backend.dto.TiendaUpdateRequest;
import com.komuna.komuna_backend.dto.VendedorRegistroRequest;
import com.komuna.komuna_backend.dto.VendedorRegistroResponse;
import com.komuna.komuna_backend.entity.Tienda;
import com.komuna.komuna_backend.service.TiendaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*; 

import java.util.Map;

@RestController
@RequestMapping("/api/vendedores")
public class VendedorController {

    private final TiendaService tiendaService;

    @Autowired
    public VendedorController(TiendaService tiendaService) {
        this.tiendaService = tiendaService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<VendedorRegistroResponse> registrarVendedor(@Valid @RequestBody VendedorRegistroRequest request) {
        VendedorRegistroResponse response = tiendaService.registrarVendedor(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Tienda> loginVendedor(@RequestBody Map<String, String> payload) {
        String codigoUnico = payload.get("codigoUnico");
        if (codigoUnico == null || codigoUnico.trim().isEmpty()){
             return ResponseEntity.badRequest().build();
        }
        Tienda tienda = tiendaService.loginVendedor(codigoUnico);
        return ResponseEntity.ok(tienda);
    }
    
    @GetMapping("/recuperar-codigo")
    public ResponseEntity<?> recuperarCodigo(@RequestParam String email) {
        return tiendaService.recuperarCodigo(email)
                .map(codigo -> ResponseEntity.ok(Map.of("codigoUnico", codigo, "mensaje", "Su código ha sido recuperado.")))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "No se encontró ninguna tienda con el correo electrónico proporcionado.")));
    }

    @PutMapping("/{codigoUnicoVendedor}/tienda")
    public ResponseEntity<Tienda> actualizarTienda(
            @PathVariable String codigoUnicoVendedor,
            @Valid @RequestBody TiendaUpdateRequest request) {
        Tienda tiendaActualizada = tiendaService.actualizarTienda(codigoUnicoVendedor, request);
        return ResponseEntity.ok(tiendaActualizada);
    }
    
    @GetMapping("/{codigoUnicoVendedor}/mi-tienda")
    public ResponseEntity<Tienda> obtenerMiTienda(@PathVariable String codigoUnicoVendedor) {
        Tienda tienda = tiendaService.loginVendedor(codigoUnicoVendedor);
        return ResponseEntity.ok(tienda);
    }

    // --- MÉTODO DE PRUEBA PARA DIAGNÓSTICO ---
    @GetMapping("/test-ping")
    public ResponseEntity<String> testPing() {
        // Este mensaje debería aparecer en la consola de tu backend si el endpoint es alcanzado
        System.out.println("LOG DESDE VENDEDOR CONTROLLER: El endpoint /test-ping fue alcanzado exitosamente."); 
        return ResponseEntity.ok("Pong desde VendedorController!");
    }
}
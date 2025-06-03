package com.komuna.komuna_backend.controller;

import com.komuna.komuna_backend.dto.TiendaPublicView;
import com.komuna.komuna_backend.service.TiendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/visitantes")
public class VisitanteController {

    private final TiendaService tiendaService;

    @Autowired
    public VisitanteController(TiendaService tiendaService) {
        this.tiendaService = tiendaService;
    }

    @GetMapping("/tiendas")
    public ResponseEntity<List<TiendaPublicView>> obtenerTodasLasTiendas() {
        List<TiendaPublicView> tiendas = tiendaService.obtenerTodasLasTiendasPublicas();
        return ResponseEntity.ok(tiendas);
    }

    @GetMapping("/tiendas/{id}")
    public ResponseEntity<TiendaPublicView> obtenerTiendaPorId(@PathVariable Long id) {
        TiendaPublicView tienda = tiendaService.obtenerTiendaPublicaPorId(id);
        return ResponseEntity.ok(tienda);
    }
}
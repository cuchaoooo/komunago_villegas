package com.komuna.komuna_backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.OffsetDateTime;

@Entity
@Table(name = "tiendas")
public class Tienda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "codigo_unico", unique = true, nullable = false, length = 50)
    private String codigoUnico;

    @NotBlank
    @Size(max = 255)
    @Column(name = "nombre_tienda", nullable = false)
    private String nombreTienda;

    @NotBlank
    @Email
    @Size(max = 255)
    @Column(name = "correo_electronico", unique = true, nullable = false)
    private String correoElectronico;

    @Size(max = 100)
    @Column(name = "tipo_negocio")
    private String tipoNegocio;

    @Lob
    @Column(name = "fotos_local", columnDefinition = "TEXT")
    private String fotosLocal; // Puede ser JSON string de URLs

    @Column(name = "cupo_personas")
    private Integer cupoPersonas;

    @Lob
    @Column(name = "horario_atencion", columnDefinition = "TEXT")
    private String horarioAtencion;

    @Lob
    @Column(name = "ubicacion_exacta", columnDefinition = "TEXT")
    private String ubicacionExacta;

    @Lob
    @Column(name = "carta_menu_precios", columnDefinition = "TEXT")
    private String cartaMenuPrecios;

    @Lob
    @Column(name = "enlaces_redes_sociales", columnDefinition = "TEXT")
    private String enlacesRedesSociales; // Puede ser JSON string

    @Column(name = "fecha_registro", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private OffsetDateTime fechaRegistro;

    @Column(name = "ultima_actualizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private OffsetDateTime ultimaActualizacion;

    @PrePersist
    protected void onCreate() {
        fechaRegistro = OffsetDateTime.now();
        ultimaActualizacion = OffsetDateTime.now();
        // La generación real del código único se hace en el servicio TiendaService
        // y el campo 'codigoUnico' ya tiene la anotación @NotBlank.
        // Esta comprobación explícita aquí es redundante.
        /*
        if (codigoUnico == null || codigoUnico.trim().isEmpty()) {
            throw new IllegalStateException("Codigo Unico no puede ser nulo al persistir");
        }
        */
    }

    @PreUpdate
    protected void onUpdate() {
        ultimaActualizacion = OffsetDateTime.now();
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoUnico() {
        return codigoUnico;
    }

    public void setCodigoUnico(String codigoUnico) {
        this.codigoUnico = codigoUnico;
    }

    public String getNombreTienda() {
        return nombreTienda;
    }

    public void setNombreTienda(String nombreTienda) {
        this.nombreTienda = nombreTienda;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getTipoNegocio() {
        return tipoNegocio;
    }

    public void setTipoNegocio(String tipoNegocio) {
        this.tipoNegocio = tipoNegocio;
    }

    public String getFotosLocal() {
        return fotosLocal;
    }

    public void setFotosLocal(String fotosLocal) {
        this.fotosLocal = fotosLocal;
    }

    public Integer getCupoPersonas() {
        return cupoPersonas;
    }

    public void setCupoPersonas(Integer cupoPersonas) {
        this.cupoPersonas = cupoPersonas;
    }

    public String getHorarioAtencion() {
        return horarioAtencion;
    }

    public void setHorarioAtencion(String horarioAtencion) {
        this.horarioAtencion = horarioAtencion;
    }

    public String getUbicacionExacta() {
        return ubicacionExacta;
    }

    public void setUbicacionExacta(String ubicacionExacta) {
        this.ubicacionExacta = ubicacionExacta;
    }

    public String getCartaMenuPrecios() {
        return cartaMenuPrecios;
    }

    public void setCartaMenuPrecios(String cartaMenuPrecios) {
        this.cartaMenuPrecios = cartaMenuPrecios;
    }

    public String getEnlacesRedesSociales() {
        return enlacesRedesSociales;
    }

    public void setEnlacesRedesSociales(String enlacesRedesSociales) {
        this.enlacesRedesSociales = enlacesRedesSociales;
    }

    public OffsetDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(OffsetDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public OffsetDateTime getUltimaActualizacion() {
        return ultimaActualizacion;
    }

    public void setUltimaActualizacion(OffsetDateTime ultimaActualizacion) {
        this.ultimaActualizacion = ultimaActualizacion;
    }
}
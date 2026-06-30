package com.feliperaac.gitcommitmirror.model; // 👈 Atualizado com o seu feliperaac

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "promocoes")
public class Promocao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String productId;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false, length = 1000)
    private String linkAfiliado;

    private LocalDateTime dataEnvio;

    public Promocao() {}

    public Promocao(String productId, String titulo, String linkAfiliado) {
        this.productId = productId;
        this.titulo = titulo;
        this.linkAfiliado = linkAfiliado;
        this.dataEnvio = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getLinkAfiliado() { return linkAfiliado; }
    public void setLinkAfiliado(String linkAfiliado) { this.linkAfiliado = linkAfiliado; }
    public LocalDateTime getDataEnvio() { return dataEnvio; }
}
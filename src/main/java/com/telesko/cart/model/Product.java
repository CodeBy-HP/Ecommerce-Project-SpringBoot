package com.telesko.cart.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increments the ID
    private int id;

    private String name;
    private String description;
    private String brand;
    private BigDecimal price; // Strictly for financial precision
    private String category;

    // Formats the raw timestamp into readable JSON (e.g., 14-05-2023)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate releaseDate;

    private boolean productAvailable;
    private int quantity;

    private String imageName;
    private String imageType;

    @Lob
    private byte[] imageData;
}
package com.jdc.myfourapi.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "ingredients")
@Getter
@Setter
public class IngredientEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "cod_ingredient")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codIngredient;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name_ingredient")
    private String nameIngredient;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "price_ingredient")
    private int priceIngredient;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "calories_ingredient")
    private float caloriesIngredient;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "description_ingredient")
    private String descriptionIngredient;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "existence_ingredient")
    private float existenceIngredient;
}

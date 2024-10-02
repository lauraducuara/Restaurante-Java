package com.jdc.myfourapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PortionDto {

    private Long codPortion;
    private String namePortion;
    private float caloriesPortion;
    private Long codWine;
    private Long codIngredient;

}

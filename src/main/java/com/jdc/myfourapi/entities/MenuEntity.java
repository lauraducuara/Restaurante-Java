package com.jdc.myfourapi.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "menus")
@Getter
@Setter
public class MenuEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "cod_menu")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codMenu;

    @NotNull
    @Column(name = "main_code")
    private int mainCode;

    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "name_menu")
    private String nameMenu;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "price_menu")
    private int priceMenu;
}

package com.jdc.myfourapi.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "wine")
@Getter
@Setter
public class WineEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_wine")
    private Long codWine;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "tipe_wine")
    private int tipeWine;

    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "name_wine")
    private String nameWine;

    @NotNull
    @Column(name = "date_wine")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date dateWine;
}

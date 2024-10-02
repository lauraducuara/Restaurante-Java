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
@Table(name = "coocker")
@Getter
@Setter
public class CoockerEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_coocker")
    private Long codCoocker;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "identification_coocker")
    private String identificationCoocker;

    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "names_coocker")
    private String namesCoocker;

    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "first_last_name")
    private String firstLastName;

    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "second_last_name")
    private String secondLastName;

    @NotNull
    @Column(name = "date_born_coocker")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date dateBornCoocker;
}

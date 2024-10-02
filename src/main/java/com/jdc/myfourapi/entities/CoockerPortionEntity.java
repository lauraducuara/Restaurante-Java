package com.jdc.myfourapi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jdc.myfourapi.entities.pks.CoockerPortionEntityPk;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;

@Entity
@Table(name = "coocker_portion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(CoockerPortionEntityPk.class) // Specify the correct class here
public class CoockerPortionEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name ="cod_coocker")
    private Long codCoocker;

    @Id
    @Column(name = "cod_portion")
    private Long codPortion;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cod_coocker", referencedColumnName = "cod_coocker", insertable = false, updatable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private CoockerEntity coockerEntity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cod_portion", referencedColumnName = "cod_portion", insertable = false, updatable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PortionEntity portionEntity;
}

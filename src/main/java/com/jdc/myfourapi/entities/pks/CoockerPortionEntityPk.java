package com.jdc.myfourapi.entities.pks;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CoockerPortionEntityPk implements Serializable {

    private Long codCoocker;
    private Long codPortion;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoockerPortionEntityPk that = (CoockerPortionEntityPk) o;
        return Objects.equals(codCoocker, that.codCoocker) && Objects.equals(codPortion, that.codPortion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codCoocker, codPortion);
    }
}

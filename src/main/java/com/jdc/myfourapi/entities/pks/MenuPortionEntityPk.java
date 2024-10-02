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

public class MenuPortionEntityPk implements Serializable {
    private Long codMenu;
    private Long codPortion;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuPortionEntityPk that = (MenuPortionEntityPk) o;
        return Objects.equals(codMenu, that.codMenu) && Objects.equals(codPortion, that.codPortion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codMenu, codPortion);
    }
}

package com.fengx.mytest.other.jhzhongzhi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Greens {
    /**
     * 名字
     */
    private String name;
    /**
     * 属性a
     */
    private Integer a;
    /**
     * 属性b
     */
    private Integer b;
    /**
     * 属性c
     */
    private Integer c;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Greens other = (Greens) obj;
        return other.name.equals(this.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

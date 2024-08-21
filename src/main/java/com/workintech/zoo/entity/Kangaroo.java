package com.workintech.zoo.entity;

import lombok.*;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Component
public class Kangaroo {
    private long id;
    private String name;
    private double height;
    private double weight;
    private String gender;
    private boolean isAggressive ;

    public boolean getIsAggressive() {
        return isAggressive;
    }

    public void setIsAggressive(boolean isAggressive) {
        this.isAggressive = isAggressive;
    }


}

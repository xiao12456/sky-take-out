package com.sky.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ShoppingCartDTO implements Serializable {

    private static final long serialVersionUID = -6633769045620565464L;
    private Long dishId;
    private Long setmealId;
    private String dishFlavor;

}

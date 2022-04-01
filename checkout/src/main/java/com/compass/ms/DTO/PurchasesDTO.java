package com.compass.ms.DTO;

import com.compass.ms.entity.Cart;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchasesDTO {

    private Long id;
    private Long user_id;
    private Long payment_id;
    private List<Cart> cart = new ArrayList<>();
}

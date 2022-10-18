package com.ecommerce.eshoping.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateOperationRequestDTO {
    public Long id;
    public String userName;
    public int age;
}

package com.ecommerce.eshoping.dto;

import com.ecommerce.eshoping.models.CrudDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetOperationResponseDTO {
    public boolean IsSuccess;
    public String message;
    public List<CrudDetail> data;
}

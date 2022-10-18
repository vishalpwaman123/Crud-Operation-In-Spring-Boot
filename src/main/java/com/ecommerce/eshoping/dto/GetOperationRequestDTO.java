package com.ecommerce.eshoping.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetOperationRequestDTO {
    public Long pageNumber;
    public Long numberOfRecordPerPage;
}

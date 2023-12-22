package com.ecommerce.genAI.dto.perfume;

import com.ecommerce.genAI.enums.SearchPerfume;
import lombok.Data;

@Data
public class SearchTypeRequest {
    private SearchPerfume searchType;
    private String text;
}

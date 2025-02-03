package com.example.partnershistory.entity;

import lombok.Data;
import java.time.LocalDate;

@Data
public class History {
    private Integer history_sale_id;
    private String partnerName;
    private String productName;
    private LocalDate sale_date;
    private Integer product_count;

    public History(Integer history_sale_id, String partnerName, String productName, LocalDate sale_date, Integer product_count) {
        this.history_sale_id = history_sale_id;
        this.partnerName = partnerName;
        this.productName = productName;
        this.sale_date = sale_date;
        this.product_count = product_count;
    }
}

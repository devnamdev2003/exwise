package com.exwise.exwise.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
public class ExpenseDTO {

    private Integer expenseId;
    private BigDecimal amount;
    private Integer categoryId;
    private String subcategory;
    private LocalDate date;
    private LocalTime time;
    private String note;
    private String paymentMode;
    private String location;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long user_id;
}

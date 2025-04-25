package com.exwise.exwise.service;

import com.exwise.exwise.dto.ExpenseDTO;

import java.util.List;

public interface ExpenseService {
    ExpenseDTO createExpense(ExpenseDTO dto);
    ExpenseDTO getExpenseById(Integer id);
    List<ExpenseDTO> getAllExpenses();
    ExpenseDTO updateExpense(Integer id, ExpenseDTO dto);
    void deleteExpense(Integer id);
}

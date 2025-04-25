package com.exwise.exwise.service.impl;

import com.exwise.exwise.dto.ExpenseDTO;
import com.exwise.exwise.entity.Category;
import com.exwise.exwise.entity.Expense;
import com.exwise.exwise.repository.ExpenseRepository;
import com.exwise.exwise.repository.CategoryRepository;
import com.exwise.exwise.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private ExpenseDTO mapToDTO(Expense expense) {
        ExpenseDTO dto = new ExpenseDTO();
        dto.setExpenseId(expense.getExpenseId());
        dto.setAmount(expense.getAmount());
        dto.setCategoryId(expense.getCategory() != null ? expense.getCategory().getCategoryId() : null);
        dto.setSubcategory(expense.getSubcategory());
        dto.setDate(expense.getDate());
        dto.setTime(expense.getTime());
        dto.setNote(expense.getNote());
        dto.setPaymentMode(expense.getPaymentMode());
        dto.setLocation(expense.getLocation());
        dto.setCreatedAt(expense.getCreatedAt());
        dto.setUpdatedAt(expense.getUpdatedAt());
        return dto;
    }

    private Expense mapToEntity(ExpenseDTO dto) {
        Expense expense = new Expense();
        expense.setExpenseId(dto.getExpenseId());
        expense.setAmount(dto.getAmount());
        if (dto.getCategoryId() != null) {
            Optional<Category> category = categoryRepository.findById(dto.getCategoryId());
            category.ifPresent(expense::setCategory);
        }
        expense.setSubcategory(dto.getSubcategory());
        expense.setDate(dto.getDate());
        expense.setTime(dto.getTime());
        expense.setNote(dto.getNote());
        expense.setPaymentMode(dto.getPaymentMode());
        expense.setLocation(dto.getLocation());
        expense.setCreatedAt(dto.getCreatedAt());
        expense.setUpdatedAt(dto.getUpdatedAt());
        return expense;
    }

    @Override
    public ExpenseDTO createExpense(ExpenseDTO dto) {
        Expense expense = mapToEntity(dto);
        return mapToDTO(expenseRepository.save(expense));
    }

    @Override
    public ExpenseDTO getExpenseById(Integer id) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));
        return mapToDTO(expense);
    }

    @Override
    public List<ExpenseDTO> getAllExpenses() {
        return expenseRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ExpenseDTO updateExpense(Integer id, ExpenseDTO dto) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        Expense updated = mapToEntity(dto);
        updated.setExpenseId(id);
        return mapToDTO(expenseRepository.save(updated));
    }

    @Override
    public void deleteExpense(Integer id) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));
        expenseRepository.delete(expense);
    }
}

package com.exwise.exwise.controller;

import com.exwise.exwise.dto.ExpenseDTO;
import com.exwise.exwise.dto.response.ApiResponse;
import com.exwise.exwise.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @PostMapping
    public ResponseEntity<ExpenseDTO> create(@RequestBody ExpenseDTO dto) {
        return ResponseEntity.ok(expenseService.createExpense(dto));
    }

    @GetMapping
    public ResponseEntity<List<ExpenseDTO>> getAll() {
        return ResponseEntity.ok(expenseService.getAllExpenses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ExpenseDTO>> getById(@PathVariable Integer id) {
        ExpenseDTO dto = expenseService.getExpenseById(id);
        ApiResponse<ExpenseDTO> response = new ApiResponse<>("success", 200, "Expense fetched", dto, false);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExpenseDTO> update(@PathVariable Integer id, @RequestBody ExpenseDTO dto) {
        return ResponseEntity.ok(expenseService.updateExpense(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        expenseService.deleteExpense(id);
        return ResponseEntity.noContent().build();
    }
}

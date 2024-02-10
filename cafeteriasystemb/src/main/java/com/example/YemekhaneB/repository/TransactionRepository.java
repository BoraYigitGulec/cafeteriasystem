package com.example.YemekhaneB.repository;

import com.example.YemekhaneB.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
}

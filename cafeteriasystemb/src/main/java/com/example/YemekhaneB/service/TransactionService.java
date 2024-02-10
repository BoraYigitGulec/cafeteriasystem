package com.example.YemekhaneB.service;

import com.example.YemekhaneB.api.PurchaseRequest;
import com.example.YemekhaneB.model.Transaction;
import com.example.YemekhaneB.model.User;
import org.springframework.stereotype.Service;
import com.example.YemekhaneB.repository.TransactionRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    private final TransactionRepository TransactionRepository;
    private final userService userService;
    private final WorkplaceService WorkplaceService;
    TransactionService(TransactionRepository TransactionRepository, userService userService
            ,WorkplaceService WorkplaceService){
        this.TransactionRepository = TransactionRepository;
        this.userService = userService;
        this.WorkplaceService = WorkplaceService;
    }
    public List<Transaction> getTransactions() {
        return TransactionRepository.findAll();
    }

    public void saveTransaction(Transaction newTransaction){
        newTransaction.SetDate(LocalDateTime.now());
        if(userService.changeBalance(newTransaction)){
            TransactionRepository.save(newTransaction);
        }
    }
    public Long getRevenue(){
        LocalDateTime currentDate = LocalDateTime.now();
        List<Transaction> allTransactions = getTransactions();
        LocalDate currentLocalDate = currentDate.toLocalDate();

        // Filter transactions that occurred on the same day as currentDate
        List<Transaction> sameDayTransactions = allTransactions.stream()
                .filter(transaction -> transaction.getDate().toLocalDate().isEqual(currentLocalDate))
                .collect(Collectors.toList());
        sameDayTransactions = sameDayTransactions.stream()
                .filter(transaction -> transaction.getTransactiontype().equalsIgnoreCase("add"))
                .collect(Collectors.toList());
        Long totalRevenue = sameDayTransactions.stream()
                .mapToLong(Transaction::getAmount) // Assuming you have an amount property in Transaction
                .sum();
        return totalRevenue;
    }
    public Long getpurchasecount(){
        LocalDateTime currentDate = LocalDateTime.now();
        List<Transaction> allTransactions = getTransactions();
        LocalDate currentLocalDate = currentDate.toLocalDate();

        // Filter transactions that occurred on the same day as currentDate
        List<Transaction> sameDayTransactions = allTransactions.stream()
                .filter(transaction -> transaction.getDate().toLocalDate().isEqual(currentLocalDate))
                .collect(Collectors.toList());
        sameDayTransactions = sameDayTransactions.stream()
                .filter(transaction -> transaction.getTransactiontype().equalsIgnoreCase("purchase"))
                .collect(Collectors.toList());
        int pCount= sameDayTransactions.size();
        Long Lpcount = Long.valueOf(pCount);
        return Lpcount;
    }
    public void savepurchaseTransaction(PurchaseRequest PurchaseRequest){
        if(PurchaseRequest.getPurchaseid() == 3 || PurchaseRequest.getPurchaseid() == 5) {
            Transaction newtransaction = new Transaction();
            newtransaction.SetDate(LocalDateTime.now());
            newtransaction.setAmount(PurchaseRequest.getAmount());
            newtransaction.setTransactionType("Purchase");
            newtransaction.setUser(userService.getUserByUsername(PurchaseRequest.getUsername()));
            if (userService.changeBalance(newtransaction) && userService.purchasetitleId(PurchaseRequest.getPurchaseid()
                    , userService.getUserByUsername(PurchaseRequest.getUsername()))) {
                TransactionRepository.save(newtransaction);
            }
        }
    };
}

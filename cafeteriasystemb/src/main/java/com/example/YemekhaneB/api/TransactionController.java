package com.example.YemekhaneB.api;

import com.example.YemekhaneB.model.FoodTable;
import com.example.YemekhaneB.model.Transaction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.YemekhaneB.service.TransactionService;
import com.example.YemekhaneB.service.userService;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/transactions")
public class TransactionController {
    private final TransactionService TransactionService;
    private final userService userService;
    TransactionController(TransactionService TransactionService,userService userService){
        this.TransactionService = TransactionService;
        this.userService = userService;
    }
    @GetMapping("/admin")
    public ResponseEntity<List<Transaction>> getTransactions(@RequestParam(required = false) String userid) {
        List<Transaction> allTransactions = TransactionService.getTransactions();
        List<Transaction> sortedTransactions = allTransactions.stream()
                .sorted(Comparator.comparing(Transaction::getDate).reversed())
                .collect(Collectors.toList());
        if (userid != null) {
            sortedTransactions = sortedTransactions.stream()
                    .filter(transaction -> transaction.getuserid().toString().contains(userid))
                    // burda inte çevirmediğim için problem olursa kontrol et
                    .collect(Collectors.toList());
        }

        return new ResponseEntity<>(sortedTransactions, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<ApiResponse> createTransaction(@RequestBody Transaction newTransaction){
        TransactionService.saveTransaction(newTransaction);
        return new ResponseEntity<>(new ApiResponse("Saved"),HttpStatus.CREATED);
    }
    @GetMapping("/admin/revenue") // http://localhost:8080/api/transactions/admin/revenue
    public ResponseEntity<Long>getRevenue(){
        return new ResponseEntity<>(TransactionService.getRevenue(),HttpStatus.OK);
    }
    @GetMapping("/admin/pcount") // http://localhost:8080/api/transactions/pcount
    public ResponseEntity<Long>getpurchasecount(){
        return new ResponseEntity<>(TransactionService.getpurchasecount(),HttpStatus.OK);
    }
    @GetMapping("/{username}")
    ResponseEntity<List<Transaction>>  getpersonalTransactions(@PathVariable  String username){
        List<Transaction> allTransactions = TransactionService.getTransactions();
        List<Transaction> sortedTransactions = allTransactions.stream()
                .sorted(Comparator.comparing(Transaction::getDate).reversed())
                .collect(Collectors.toList());
        var userid1 = userService.getUserByUsername(username).getId();
        String userid = String.valueOf(userid1);
        if (userid != null) {
            sortedTransactions = sortedTransactions.stream()
                    .filter(transaction -> transaction.getuserid().toString().contains(userid))
                    // burda inte çevirmediğim için problem olursa kontrol et
                    .collect(Collectors.toList());
        }
        return new ResponseEntity<>(sortedTransactions, HttpStatus.OK);
    }
    @PostMapping("/purchase")
    public ResponseEntity<ApiResponse> createpurchaseTransaction(@RequestBody PurchaseRequest PurchaseRequest){
        TransactionService.savepurchaseTransaction(PurchaseRequest);
        return new ResponseEntity<>(new ApiResponse("Saved"),HttpStatus.CREATED);
    }
}

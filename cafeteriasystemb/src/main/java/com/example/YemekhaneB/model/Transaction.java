package com.example.YemekhaneB.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonProperty("date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;
    @JsonProperty("transactiontype")
    private String transactiontype;
    @JsonProperty("amount")
    private Long amount;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid", referencedColumnName = "id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)

    public Long getId(){return id;}
    public LocalDateTime getDate(){return date;}
    public String getTransactiontype(){return transactiontype;}
    public  Long getAmount(){return amount;}
    public Long getuserid(){return user.getId();}

    public void SetDate(LocalDateTime date){this.date = date;}
    public void setTransactionType(String transactiontype){this.transactiontype = transactiontype;}

    public void setAmount (Long Amount){this.amount = Amount;}
    public void setUser( User user){this.user = user;}
    public void setWorkplace(Workplace workplace) {
        this.workplace = workplace;
    }


}

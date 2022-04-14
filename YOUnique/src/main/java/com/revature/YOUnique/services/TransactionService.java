package com.revature.YOUnique.services;

import com.revature.YOUnique.daos.TransactionDAO;


public class TransactionService {
   public final TransactionDAO transactionDAO;


    public TransactionService(TransactionDAO transactionDAO) {
        this.transactionDAO = transactionDAO;
    }

    public TransactionDAO getTransactionDAO() {
        return transactionDAO;
    }
}

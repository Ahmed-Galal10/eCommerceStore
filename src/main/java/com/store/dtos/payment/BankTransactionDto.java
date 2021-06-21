package com.store.dtos.payment;

public class BankTransactionDto {
    private CreditCardAuthDto srcCardAuthDto;
    private String distCreditCardNumber;
    //1- seller (bank account)
    //2- exchange money with balance
    private Double amount;

    public BankTransactionDto() {
    }

    public BankTransactionDto(CreditCardAuthDto srcCardAuthDto, String distCreditCardNumber, Double amount) {
        this.srcCardAuthDto = srcCardAuthDto;
        this.distCreditCardNumber = distCreditCardNumber;
        this.amount = amount;
    }

    public CreditCardAuthDto getSrcCardAuthDto() {
        return srcCardAuthDto;
    }

    public void setSrcCardAuthDto(CreditCardAuthDto srcCardAuthDto) {
        this.srcCardAuthDto = srcCardAuthDto;
    }

    public String getDistCreditCardNumber() {
        return distCreditCardNumber;
    }

    public void setDistCreditCardNumber(String distCreditCardNumber) {
        this.distCreditCardNumber = distCreditCardNumber;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}

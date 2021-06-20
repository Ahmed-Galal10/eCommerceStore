package com.store.service.impl;

import com.store.dtos.payment.BankTransactionDto;
import com.store.dtos.payment.CreditCardAuthDto;
import com.store.dtos.payment.CreditCardDto;
import com.store.exceptions.BankException;
import com.store.model.BankAccount;
import com.store.repository.BankRepo;
import com.store.service.BankService;
import com.store.util.mappers.CreditCardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BankServiceImpl implements BankService {
    @Autowired
    private BankRepo bankRepo;
    @Autowired
    private CreditCardMapper cardMapper;

    @Override
    public CreditCardDto validateCreditCard(CreditCardAuthDto creditCardAuthDto) throws BankException {
        BankAccount bankAccount = bankRepo.findByCreditCardNumber(creditCardAuthDto.getCreditCardNumber());
        System.out.println(bankAccount);
        if (bankAccount != null) {
            if (bankAccount.getCvv().equals(creditCardAuthDto.getCvv())) {
                return cardMapper.toDto(bankAccount);
            } else {
                System.out.println("Bad CVV");
                throw new BankException("Bad CVV", 1403);
            }
        } else {
            System.out.println("CARD NOT FOUND");
            throw new BankException("CARD NOT FOUND", 1404);
        }
    }

    @Override
    public Boolean doBankTransaction(BankTransactionDto bankTransactionDto) throws BankException {

        BankAccount distAccount = bankRepo.findByCreditCardNumber(bankTransactionDto.getDistCreditCardNumber());
        if (this.withDraw(bankTransactionDto.getSrcCardAuthDto(), bankTransactionDto.getAmount())) {
            if (this.deposit(distAccount, bankTransactionDto.getAmount())) {
                return true;
            } else {
                throw new BankException("Depositing Failed", 1414);
            }
        } else {
            throw new BankException("Withdrawing Failed", 1415);
        }


    }

    private Boolean withDraw(CreditCardAuthDto cardAuthDto, Double amount) throws BankException {
        try {
            CreditCardDto srcCard = this.validateCreditCard(cardAuthDto);
            Double updatedBalance = srcCard.getBalance() - amount;
            if (updatedBalance > 0) {
                BankAccount srcAccount = bankRepo.findByCreditCardNumber(srcCard.getCreditCardNumber());
                srcAccount.setBalance(updatedBalance);
                bankRepo.save(srcAccount);
            } else {
                throw new BankException("Insufficient Balance", 1410);
            }
            return true;
        } catch (BankException e) {
            throw new BankException(e.getMessage() + " WithDrawing Failed", e.getErrorCode());
        } catch (Exception e) {
            return false;
        }

    }

    private Boolean deposit(BankAccount account, Double amount) {
        try {
            Double updatedBalance = account.getBalance() + amount;
            account.setBalance(updatedBalance);
            bankRepo.save(account);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}

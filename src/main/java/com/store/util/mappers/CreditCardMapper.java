package com.store.util.mappers;

import com.store.dtos.payment.CreditCardDto;
import com.store.model.BankAccount;
import org.springframework.stereotype.Component;

@Component
public class CreditCardMapper extends EntityDtoMapper<BankAccount, CreditCardDto> {
    @Override
    public CreditCardDto toDto(BankAccount entity) {
        CreditCardDto creditCardDto = new CreditCardDto(entity.getId(),entity.getBalance(),
                entity.getCreditCardNumber());
        return creditCardDto;
    }

    @Override
    public BankAccount toEntity(CreditCardDto dto) {
        BankAccount bankAccount = new BankAccount(dto.getId(), dto.getBalance(),dto.getCreditCardNumber(), dto.getCvv());
        return bankAccount;
    }
}

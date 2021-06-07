package com.store.util.mappers;

import com.store.dtos.seller.SellerDto;
import com.store.model.Seller;
import org.springframework.stereotype.Service;

@Service
public class SellerMapper extends EntityDtoMapper<Seller, SellerDto> {
    @Override
    public SellerDto toDto(Seller entity) {
        SellerDto sellerDto = new SellerDto();
        //todo implement this method
        sellerDto.setBalance(entity.getBalance());
        sellerDto.setAddress(entity.getAddress());
        sellerDto.setName(entity.getName());
        sellerDto.setEmail(entity.getEmail());
        sellerDto.setImage(entity.getImage());
        sellerDto.setPhone(entity.getPhone());
        sellerDto.setRegDate(entity.getRegDate());
        sellerDto.setEmailVerified(entity.getIsEmailVerified());
        sellerDto.setDeleted(entity.getIsDeleted());
        sellerDto.setId(entity.getId());
        sellerDto.setEmailVerified(entity.getIsEmailVerified());

        return sellerDto;
    }

    @Override
    public Seller toEntity(SellerDto dto) {
        Seller seller = new Seller();
        //todo implement this method

        return seller;
    }
}

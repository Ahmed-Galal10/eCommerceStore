package com.store.util.mappers;

import com.store.dtos.seller.SellerDto;
import com.store.model.Seller;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
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
        sellerDto.setProductsCount(entity.getProducts().size());
        Integer soldItems = entity.getSoldItems().stream()
                .map(soldItem -> soldItem.getOrderItem().getQuantity())
                .reduce(Integer::sum)
                .orElse(0);
        sellerDto.setSoldItemsCount(soldItems);

        return sellerDto;
    }

    @Override
    public Seller toEntity(SellerDto dto) {
        Seller seller = new Seller();
        //todo implement this method
        seller.setId(dto.getId());
        seller.setBalance(dto.getBalance());
        seller.setEmail(dto.getEmail());
        seller.setAddress(dto.getAddress());
        seller.setName(dto.getName());
        seller.setImage(dto.getImage());
        return seller;
    }
}

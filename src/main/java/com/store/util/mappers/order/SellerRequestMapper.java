package com.store.util.mappers.order;

import com.store.dtos.seller.SellerRequestDto;
import com.store.model.Seller;
import com.store.util.mappers.EntityDtoMapper;

public class SellerRequestMapper extends EntityDtoMapper<Seller, SellerRequestDto> {

    @Override
    public SellerRequestDto toDto(Seller entity) {

        SellerRequestDto sellerRequestDto = new SellerRequestDto();

        sellerRequestDto.setId(entity.getId());
        sellerRequestDto.setName(entity.getName());
        sellerRequestDto.setAddress(entity.getAddress());
        sellerRequestDto.setEmail(entity.getEmail());
        sellerRequestDto.setPhone(entity.getPhone());
        sellerRequestDto.setPassword(entity.getPassword());

        return sellerRequestDto;
    }

    @Override
    public Seller toEntity(SellerRequestDto dto) {

        Seller seller = new Seller();

        seller.setId(dto.getId());
        seller.setName(dto.getName());
        seller.setAddress(dto.getAddress());
        seller.setEmail(dto.getEmail());
        seller.setPhone(dto.getPhone());
        seller.setPassword(dto.getPassword());

        return seller;
    }
}

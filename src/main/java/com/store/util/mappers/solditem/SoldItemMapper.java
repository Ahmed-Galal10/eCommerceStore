package com.store.util.mappers.solditem;

import com.store.dtos.solditem.SoldItemDto;
import com.store.model.OrderItem;
import com.store.util.mappers.EntityDtoMapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SoldItemMapper extends EntityDtoMapper<OrderItem, SoldItemDto> {

    @Override
    public SoldItemDto toDto(OrderItem entity) {

        SoldItemDto soldItemDto = new SoldItemDto();

        soldItemDto.setProductId( entity.getProduct().getId() );
        soldItemDto.setSoldQuantity( entity.getQuantity() );
        soldItemDto.setName( entity.getProduct().getName() );
        soldItemDto.setOrderId( entity.getOrder().getId() );
        soldItemDto.setPrice( entity.getUnitPrice() );
        soldItemDto.setSellerId( entity.getProduct().getUser().getId() );
        soldItemDto.setSellerName( entity.getProduct().getUser().getName() );


        try {
            Date soldDate = entity.getOrder().getDate();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String date = dateFormat.format(soldDate);
            soldItemDto.setSoldDate( dateFormat.parse(date) );
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  soldItemDto;
    }

    @Override
    public OrderItem toEntity(SoldItemDto dto) {
        return null;
    }
}

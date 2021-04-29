package ru.abenefic.spring.shop.storage.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class StorageDto {
    private long id;

    private String title;
    private String location;
    private List<Long> productItems;
}

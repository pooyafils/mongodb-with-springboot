package com.example.mongo.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;
@Setter
@Getter
public class BaseEntity {
    @MongoId(FieldType.OBJECT_ID)
    private String id;
    private Date createdAt;

    private Date updatedAt;
}

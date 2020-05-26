package com.dbr.generator.gen.server.db.model;

import lombok.*;

@Data
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class PrimaryKey {

    private String columnName;
    private String keySequence;
    private String pkName;

}

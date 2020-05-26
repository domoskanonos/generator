package com.dbr.generator.gen.server.db.model;

import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Database {

    private String driverClazz;

    private String url;

    private String username;

    private String password;

    private String schema;

}

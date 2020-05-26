package com.dbr.generator.gen.server.springboot.scheduling.model;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Scheduling {

    private Integer id;

    private String clazzName;

    private String packageName;

    private String cron;

}

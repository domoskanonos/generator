package com.dbr.generator.result.example.dto;

import lombok.*;

@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ExampleDTO {

    private Integer id;

    private String name;

    private String username;

    private String password;

    private byte[] userImg;


}
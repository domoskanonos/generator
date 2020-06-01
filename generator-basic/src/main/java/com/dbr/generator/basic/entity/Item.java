package com.dbr.generator.basic.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "ITEM")
@Data
@ToString
@Builder(toBuilder = true)
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;

    @Column(name = "TEMPLATE_PATH", nullable = false)
    private String templatePath;

    @Column(name = "FILE_PATH", nullable = false)
    private String filePath;

    @Column(name = "JAVA_CLAZZ_NAME", nullable = false)
    private String javaClazzName;

    @Column(name = "JAVA_ID_CLAZZ_NAME", nullable = false)
    private String javaIdClazzName;



}
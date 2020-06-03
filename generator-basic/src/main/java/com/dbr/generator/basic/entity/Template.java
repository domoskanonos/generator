package com.dbr.generator.basic.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TEMPLATE")
@Data
@ToString
@Builder(toBuilder = true)
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Template {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "TEMPLATE_PATH", nullable = false)
    private String templatePath;

}

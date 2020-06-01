package com.dbr.generator.basic.entity;

import com.dbr.generator.basic.enumeration.PropertyTypeEnum;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "PROPERTY_ITEM")
@Data
@ToString
@Builder(toBuilder = true)
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "PROPERTY_TYPE", nullable = false)
    @Enumerated
    private PropertyTypeEnum propertyType;

    @Column(name = "ID_PROPERTY", nullable = false)
    private Boolean idProperty;

    @Column(name = "SEARCHABLE", nullable = false)
    private Boolean searchable;

    @Column(name = "NULLABLE", nullable = false)
    private Boolean nullable;

    @Column(name = "USE_J_P_A_ID_CLAZZ", nullable = false)
    private Boolean useJPAIdClazz;

    @Column(name = "LENGTH", nullable = false)
    private Integer length;

}
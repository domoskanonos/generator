package com.dbr.generator.basic.entity;

import com.dbr.generator.basic.enumeration.TypeEnum;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "PROPERTY")
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
    private TypeEnum propertyType;

    @Column(name = "ID_PROPERTY")
    private Boolean idProperty = false;

    @Column(name = "SEARCHABLE")
    private Boolean searchable = false;

    @Column(name = "NULLABLE")
    private Boolean nullable = true;

    @Column(name = "USE_JPA_ID_CLAZZ")
    private Boolean useJPAIdClazz = false;

    @Column(name = "LENGTH")
    private Integer length = 0;

}
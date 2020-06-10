package com.dbr.generator.basic.entity;

import com.dbr.generator.basic.enumeration.PropertyTypeEnum;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "PROPERTY")
@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class PropertyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "DEACTIVATED")
    private Boolean deactivated;

    @Column(name = "PROPERTY_TYPE", nullable = false)
    @Enumerated
    private PropertyTypeEnum propertyType;

    @Column(name = "PROPERTY_TYPE_NAME")
    private String propertyTypeName;

    @Column(name = "ID_PROPERTY")
    private Boolean idProperty = false;

    @Column(name = "MAIN_PROPERTY")
    private Boolean mainProperty = true;

    @Column(name = "NULLABLE")
    private Boolean nullable = true;

    @Column(name = "USE_JPA_ID_CLAZZ")
    private Boolean useJPAIdClazz = false;

    @Column(name = "LENGTH")
    private Integer length = 0;

}
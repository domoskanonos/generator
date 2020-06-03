package com.dbr.generator.basic.entity;

import com.dbr.generator.basic.enumeration.TypeEnum;
import com.dbr.generator.basic.merger.TemplateEnum;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    @Column(name = "NAME")
    private String name;

    //@Column(name = "JAVA_CLAZZ_NAME", nullable = false)
    //private String javaClazzName;

    //@Column(name = "JAVA_ID_CLAZZ_NAME")
    //private String javaIdClazzName;

    @Enumerated(EnumType.STRING)
    @Column(name = "ID_TYPE")
    private TypeEnum idTypeEnum = TypeEnum.TYPE_LONG;

    @Enumerated(EnumType.STRING)
    @Column(name = "TEMPLATE")
    private TemplateEnum template;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "ITEM_PROPERTY", joinColumns = @JoinColumn(name = "ITEM_ID", nullable = false, updatable = false, referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "PROPERTY_ID", nullable = false, updatable = false, referencedColumnName = "ID"))
    private Set<Property> properties = new HashSet<>();


}
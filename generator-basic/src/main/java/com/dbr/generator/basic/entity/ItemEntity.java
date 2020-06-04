package com.dbr.generator.basic.entity;

import com.dbr.generator.basic.enumeration.TypeEnum;
import com.dbr.generator.basic.enumeration.ItemTemplateEnum;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ITEM")
@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;

    @ManyToOne
    private ProjectEntity projectEntity;

    @Column(name = "NAME")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "ID_TYPE")
    private TypeEnum idTypeEnum = TypeEnum.LONG;

    @ElementCollection(targetClass = ItemTemplateEnum.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "ITEM_TEMPLATE", joinColumns = @JoinColumn(name = "ITEM_ID", referencedColumnName = "ID"))
    @Enumerated(EnumType.STRING)
    private Set<ItemTemplateEnum> template = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "ITEM_PROPERTY", joinColumns = @JoinColumn(name = "ITEM_ID", nullable = false, updatable = false, referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "PROPERTY_ID", nullable = false, updatable = false, referencedColumnName = "ID"))
    private Set<PropertyEntity> properties = new HashSet<>();


}
package com.dbr.generator.basic.entity;

import com.dbr.generator.basic.enumeration.Template;
import com.dbr.generator.basic.enumeration.PropertyType;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "ITEM")
@Data
@ToString
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

    @Column(name = "DEACTIVATED")
    private Boolean deactivated;

    @Enumerated(EnumType.STRING)
    @Column(name = "ID_TYPE")
    private PropertyType idPropertyType = PropertyType.LONG;

    @ElementCollection(targetClass = Template.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "ITEM_TEMPLATE", joinColumns = @JoinColumn(name = "ITEM_ID", referencedColumnName = "ID"))
    @Enumerated(EnumType.STRING)
    private Set<Template> template = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(name = "ITEM_PROPERTY", joinColumns = @JoinColumn(name = "ITEM_ID", nullable = false, updatable = false, referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "PROPERTY_ID", nullable = false, updatable = false, referencedColumnName = "ID"))
    private List<Property> properties = new ArrayList<>();

    public void addProperty(Property property) {
        if (properties == null) {
            properties = new ArrayList<>();
        }
        properties.add(property);
    }
}
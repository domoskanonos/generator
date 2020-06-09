package com.dbr.generator.basic.entity;

import com.dbr.generator.basic.enumeration.TemplateEnum;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "PROJECT")
@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "PROJECT_ITEM", joinColumns = @JoinColumn(name = "PROJECT_ID", nullable = false, updatable = false, referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "ITEM_ID", nullable = false, updatable = false, referencedColumnName = "ID"))
    @Fetch(value = FetchMode.SUBSELECT)
    private List<ItemEntity> items = new ArrayList<>();

    @ElementCollection(targetClass = TemplateEnum.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "PROJECT_TEMPLATE", joinColumns = @JoinColumn(name = "PROJECT_ID", referencedColumnName = "ID"))
    @Enumerated(EnumType.STRING)
    private Set<TemplateEnum> template = new HashSet<>();

    @Column(name = "NAME")
    private String technicalDescriptor;

    @Column(name = "NAMESPACE")
    private String namespase;

    public void addItem(ItemEntity item) {
        if (items == null) {
            items = new ArrayList<>();
        }
        items.add(item);
    }
}

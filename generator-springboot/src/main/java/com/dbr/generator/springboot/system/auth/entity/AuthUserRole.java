package com.dbr.generator.springboot.system.auth.entity;

import com.dbr.generator.springboot.system.auth.enumeration.AuthRoleEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "AUTH_USER_ROLE")
@Data
@ToString(exclude = { "privileges" })
@NoArgsConstructor
@EqualsAndHashCode
public class AuthUserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;

    @Enumerated
    @Column(name = "NAME", unique = true, nullable = false)
    private AuthRoleEnum name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "ROLES_PRIVILEGES", joinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "PRIVILEGE_ID", referencedColumnName = "ID"))
    private Collection<AuthUserPrivilege> privileges;

    public AuthUserRole(AuthRoleEnum name) {
        super();
        this.name = name;
    }

}

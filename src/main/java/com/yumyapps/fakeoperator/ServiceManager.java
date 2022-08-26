package com.yumyapps.fakeoperator;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "service_manager")
public class ServiceManager {
    @Id
    @SequenceGenerator(name = "SERVICE_MANAGER_GENERATOR",
            sequenceName = "SERVICE_MANAGER_SEQ",
            allocationSize = 30)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SERVICE_MANAGER_GENERATOR")
    @Column(name = "service_manager_id", nullable = false)
    private Long id;


    @Column(name = "login_id")
    private String loginId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "login_password")
    private String loginPassword;


    @Column(name = "family_name")
    private String familyName;


    @Column(name = "given_name")
    private String givenName;

    @Column(name = "fk_role_id")
    private Integer fkRoleId;

    @Column(name = "is_invalid")
    private Boolean isInvalid;

    @Column(name = "is_not_locked")
    private Boolean isNotLocked;

    @Column(name = "valid_start_date")
    private Date validStartDate;

    @Column(name = "expiration_date")
    private Date expirationDate;

    @Column(name = "regist_date")
    private Date registerDate;

    @Column(name = "regist_user")
    private Long registerUser;

    @Column(name = "modify_date")
    private Date modifyDate;

    @Column(name = "modify_user")
    private Long modifyUser;

    @Column(name = "delete_code")
    private Integer deleteCode;

    @Column(name = "delete_date")
    private Date deleteDate;

    @Column(name = "delete_user")
    private Long deleteUser;

    @Column(name = "login_authorites")
    private String[] authorities;

//    @ManyToMany(mappedBy = "serviceManagers",
//            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
//    private Set<Service> services = new LinkedHashSet<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ServiceManager that = (ServiceManager) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

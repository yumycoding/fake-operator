package com.yumyapps.fakeoperator.employee;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Address> addresses;

    public void addAddress(Address address) {
        if (address.isNew()) {
            getAddresses().add(address);
            address.setEmployee(this);
        }
    }

    public void removeAddress(Address address) {
        addresses.remove(address);
        address.setEmployee(null);
    }

}

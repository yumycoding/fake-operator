package com.yumyapps.fakeoperator.employee;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;
    private String city;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Employee employee;


    @JsonIgnore
    public boolean isNew() {
        return this.id == null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(id, address.id) && Objects.equals(street, address.street) && Objects.equals(city, address.city) && Objects.equals(employee, address.employee);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

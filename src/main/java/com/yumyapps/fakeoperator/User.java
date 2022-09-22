package com.yumyapps.fakeoperator;

import lombok.*;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_name")
    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_meta_data", joinColumns = {@JoinColumn(name = "user_id")})
    private Map<String, String> metaData = new HashMap<>();
}

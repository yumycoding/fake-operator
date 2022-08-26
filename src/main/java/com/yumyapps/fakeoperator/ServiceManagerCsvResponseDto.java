package com.yumyapps.fakeoperator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceManagerCsvResponseDto implements Serializable {
    private Long id;
    private String loginId;
    private String loginPassword;
    private String familyName;
    private String givenName;
    private Integer fkRoleId;
    private Boolean isInvalid;
    private Boolean isNotLocked;
    private Date validStartDate;
    private Date expirationDate;
    private Date registerDate;
    private Long registerUser;
    private Date modifyDate;
    private Long modifyUser;
    private Integer deleteCode;
    private Date deleteDate;
    private Long deleteUser;
    private String[] authorities;
}

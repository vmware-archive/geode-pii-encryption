package io.pivotal.pde.geode.encryption.model;

import io.pivotal.pde.geode.encryption.annotation.EnableEncryption;

import java.util.Date;

public class CreditCard  {


    private String ccardId;

    private CreditCardType ccardType;

    @EnableEncryption
    private String firstName;
    @EnableEncryption
    private String lastName;

    @EnableEncryption
    private String ccardNumber;
    @EnableEncryption
    private Long expiryDate;
    @EnableEncryption
    private String cvvCode;

    private Boolean primary;

    public String getCcardId() {
        return ccardId;
    }

    public void setCcardId(String ccardId) {
        this.ccardId = ccardId;
    }

    public CreditCardType getCcardType() {
        return ccardType;
    }

    public void setCcardType(CreditCardType ccardType) {
        this.ccardType = ccardType;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCcardNumber() {
        return ccardNumber;
    }

    public void setCcardNumber(String ccardNumber) {
        this.ccardNumber = ccardNumber;
    }

    public Long getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Long expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCvvCode() {
        return cvvCode;
    }

    public void setCvvCode(String cvvCode) {
        this.cvvCode = cvvCode;
    }

    public Boolean getPrimary() {
        return primary;
    }

    public void setPrimary(Boolean primary) {
        this.primary = primary;
    }
}

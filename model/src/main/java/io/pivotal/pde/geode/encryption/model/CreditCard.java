package io.pivotal.pde.geode.encryption.model;

import io.pivotal.pde.geode.encryption.annotation.EnableEncryption;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CreditCard that = (CreditCard) o;

        if (getCcardId() != null ? !getCcardId().equals(that.getCcardId()) : that.getCcardId() != null) return false;
        if (getCcardType() != that.getCcardType()) return false;
        if (getFirstName() != null ? !getFirstName().equals(that.getFirstName()) : that.getFirstName() != null)
            return false;
        if (getLastName() != null ? !getLastName().equals(that.getLastName()) : that.getLastName() != null)
            return false;
        if (getCcardNumber() != null ? !getCcardNumber().equals(that.getCcardNumber()) : that.getCcardNumber() != null)
            return false;
        if (getExpiryDate() != null ? !getExpiryDate().equals(that.getExpiryDate()) : that.getExpiryDate() != null)
            return false;
        if (getCvvCode() != null ? !getCvvCode().equals(that.getCvvCode()) : that.getCvvCode() != null) return false;
        return getPrimary() != null ? getPrimary().equals(that.getPrimary()) : that.getPrimary() == null;
    }

    @Override
    public int hashCode() {
        int result = getCcardId() != null ? getCcardId().hashCode() : 0;
        result = 31 * result + (getCcardType() != null ? getCcardType().hashCode() : 0);
        result = 31 * result + (getFirstName() != null ? getFirstName().hashCode() : 0);
        result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);
        result = 31 * result + (getCcardNumber() != null ? getCcardNumber().hashCode() : 0);
        result = 31 * result + (getExpiryDate() != null ? getExpiryDate().hashCode() : 0);
        result = 31 * result + (getCvvCode() != null ? getCvvCode().hashCode() : 0);
        result = 31 * result + (getPrimary() != null ? getPrimary().hashCode() : 0);
        return result;
    }



}

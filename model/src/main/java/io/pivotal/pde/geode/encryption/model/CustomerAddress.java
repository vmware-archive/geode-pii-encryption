package io.pivotal.pde.geode.encryption.model;

import io.pivotal.pde.geode.encryption.annotation.EnableEncryption;

public class CustomerAddress {

    private String addressId;

    @EnableEncryption
    private String addressLine1;
    @EnableEncryption
    private String addressLine2;
    @EnableEncryption
    private String city;
    @EnableEncryption
    private String state;
    @EnableEncryption
    private String zip;

    private boolean primary;

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public boolean isPrimary() {
        return primary;
    }

    public void setPrimary(boolean primary) {
        this.primary = primary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomerAddress that = (CustomerAddress) o;

        if (isPrimary() != that.isPrimary()) return false;
        if (getAddressId() != null ? !getAddressId().equals(that.getAddressId()) : that.getAddressId() != null)
            return false;
        if (getAddressLine1() != null ? !getAddressLine1().equals(that.getAddressLine1()) : that.getAddressLine1() != null)
            return false;
        if (getAddressLine2() != null ? !getAddressLine2().equals(that.getAddressLine2()) : that.getAddressLine2() != null)
            return false;
        if (getCity() != null ? !getCity().equals(that.getCity()) : that.getCity() != null) return false;
        if (getState() != null ? !getState().equals(that.getState()) : that.getState() != null) return false;
        return getZip() != null ? getZip().equals(that.getZip()) : that.getZip() == null;
    }

    @Override
    public int hashCode() {
        int result = getAddressId() != null ? getAddressId().hashCode() : 0;
        result = 31 * result + (getAddressLine1() != null ? getAddressLine1().hashCode() : 0);
        result = 31 * result + (getAddressLine2() != null ? getAddressLine2().hashCode() : 0);
        result = 31 * result + (getCity() != null ? getCity().hashCode() : 0);
        result = 31 * result + (getState() != null ? getState().hashCode() : 0);
        result = 31 * result + (getZip() != null ? getZip().hashCode() : 0);
        result = 31 * result + (isPrimary() ? 1 : 0);
        return result;
    }
}

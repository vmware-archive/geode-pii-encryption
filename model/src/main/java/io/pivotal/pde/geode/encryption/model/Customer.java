package io.pivotal.pde.geode.encryption.model;

import io.pivotal.pde.geode.encryption.annotation.EnableEncryption;
import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.mapping.annotation.Region;

import java.util.ArrayList;
import java.util.List;

@Region (value = "Customer")
public class Customer  {

    @Id
    protected String id;

    // Name
    @EnableEncryption
    protected String firstName;
    protected String middleName;
    @EnableEncryption
    protected String lastName;
    @EnableEncryption
    protected String userName;

    protected String gender;
    protected Long createdDate;
    protected Long lastUpdatedDate;

    // Addresses
    private List<CustomerAddress> addresses = new ArrayList<CustomerAddress>();

    // Credit cards
    private List<CreditCard> creditCards = new ArrayList<CreditCard>();


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public Long getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Long lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public List<CustomerAddress> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<CustomerAddress> addresses) {
        this.addresses = addresses;
    }

    public List<CreditCard> getCreditCards() {
        return creditCards;
    }

    public void setCreditCards(List<CreditCard> creditCards) {
        this.creditCards = creditCards;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", gender='" + gender + '\'' +
                ", createdDate=" + createdDate +
                ", lastUpdatedDate=" + lastUpdatedDate +
                ", addresses=" + addresses +
                ", creditCards=" + creditCards +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (getId() != null ? !getId().equals(customer.getId()) : customer.getId() != null) return false;
        if (getFirstName() != null ? !getFirstName().equals(customer.getFirstName()) : customer.getFirstName() != null)
            return false;
        if (getMiddleName() != null ? !getMiddleName().equals(customer.getMiddleName()) : customer.getMiddleName() != null)
            return false;
        if (getLastName() != null ? !getLastName().equals(customer.getLastName()) : customer.getLastName() != null)
            return false;
        if (getUserName() != null ? !getUserName().equals(customer.getUserName()) : customer.getUserName() != null)
            return false;
        if (getGender() != null ? !getGender().equals(customer.getGender()) : customer.getGender() != null)
            return false;
        if (getCreatedDate() != null ? !getCreatedDate().equals(customer.getCreatedDate()) : customer.getCreatedDate() != null)
            return false;
        if (getLastUpdatedDate() != null ? !getLastUpdatedDate().equals(customer.getLastUpdatedDate()) : customer.getLastUpdatedDate() != null)
            return false;
        if (getAddresses() != null ? !getAddresses().equals(customer.getAddresses()) : customer.getAddresses() != null)
            return false;
        return getCreditCards() != null ? getCreditCards().equals(customer.getCreditCards()) : customer.getCreditCards() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getFirstName() != null ? getFirstName().hashCode() : 0);
        result = 31 * result + (getMiddleName() != null ? getMiddleName().hashCode() : 0);
        result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);
        result = 31 * result + (getUserName() != null ? getUserName().hashCode() : 0);
        result = 31 * result + (getGender() != null ? getGender().hashCode() : 0);
        result = 31 * result + (getCreatedDate() != null ? getCreatedDate().hashCode() : 0);
        result = 31 * result + (getLastUpdatedDate() != null ? getLastUpdatedDate().hashCode() : 0);
        result = 31 * result + (getAddresses() != null ? getAddresses().hashCode() : 0);
        result = 31 * result + (getCreditCards() != null ? getCreditCards().hashCode() : 0);
        return result;
    }
}

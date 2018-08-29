package io.pivotal.pde.geode.encryption.model;

public enum CreditCardType {


    VISA("Visa", 1L),
    MASTERCARD("MasterCard", 2L),
    DISCOVER("Discover",3L),
    AMEX("Amex", 4L);

    private String value;
    private Long id;

    CreditCardType(String value,Long id) {
        this.id = id;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

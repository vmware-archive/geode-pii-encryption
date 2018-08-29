package io.pivotal.pde.geode;

public enum CipherType {

    AES("AES-256 Implementation"),
    RSA("RSA Implementation"),
    DES("DES Implementation");

    private String description;

    CipherType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

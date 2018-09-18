package io.pivotal.pde.geode.service;

import io.pivotal.pde.geode.encryption.model.CreditCard;
import io.pivotal.pde.geode.encryption.model.Customer;

import java.util.List;


public interface IGeodeService {

        public abstract void putCustomerData(Customer customer);

        public abstract Customer getCustomerData(String key);

        public List<Object> queryAllCustomers();

        List<Object> getCreditCardByFirstName(String firstName);

}

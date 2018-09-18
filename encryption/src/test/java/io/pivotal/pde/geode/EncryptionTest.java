package io.pivotal.pde.geode;


import io.pivotal.pde.geode.encryption.DataUtil;
import io.pivotal.pde.geode.encryption.model.CreditCard;
import io.pivotal.pde.geode.encryption.model.Customer;
import io.pivotal.pde.geode.encryption.model.CustomerAddress;
import io.pivotal.pde.geode.service.IGeodeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes ={EncryptionTest.class, io.pivotal.pde.geode.service.GeodeServiceImpl.class, io.pivotal.pde.geode.encryption.common.AppCryptoServiceImpl.class } )
@SpringBootTest
public class EncryptionTest {

    @Autowired
    IGeodeService geodeService;

    /**
     * Test if the Customer record is after storing
     *
     */
    @Test
    public void testEncryptDecryptCustomer() {

        Customer customer = DataUtil.createRandomCustomer();
        String key=customer.getId();
        String firstName="test1-firstName";
        String lastName="test2LastName";
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        // Address
        String addressLine1 = "128, Main street";
        String addressLine2="Apt 404";
        String state="Tx";
        String zip="75056";
        CustomerAddress address=customer.getAddresses().get(0);
        address.setAddressLine1(addressLine1);
        address.setAddressLine2(addressLine2);
        address.setState(state);
        address.setState(state);
        address.setZip(zip);
        // Credit Card
        CreditCard creditCard=customer.getCreditCards().get(0);
        String creditCardNumber="1236541236541234";
        String cvv="123";
        creditCard.setCcardNumber(creditCardNumber);
        creditCard.setCvvCode(cvv);
        geodeService.putCustomerData(customer);
        Customer decryptedCustomer=geodeService.getCustomerData(key);

        CustomerAddress decryptedCustomerAddresses=customer.getAddresses().get(0);
        CreditCard decryptedCreditCard=customer.getCreditCards().get(0);

        // Assert Customer PII fields
        assertThat(key, equalTo(decryptedCustomer.getId()));
        assertThat(firstName, equalTo(decryptedCustomer.getFirstName()));
        assertThat(lastName, equalTo(decryptedCustomer.getLastName()));

        // Assert Address PII fields
        assertThat(addressLine1, equalTo(decryptedCustomerAddresses.getAddressLine1()));
        assertThat(addressLine2, equalTo(decryptedCustomerAddresses.getAddressLine2()));
        assertThat(state, equalTo(decryptedCustomerAddresses.getState()));
        assertThat(zip, equalTo(decryptedCustomerAddresses.getZip()));

        // Assert Credit card fields
        assertThat(creditCardNumber, equalTo(decryptedCreditCard.getCcardNumber()));
        assertThat(cvv, equalTo(decryptedCreditCard.getCvvCode()));

    }


    /**
     *  Test OQL query
     */
    @Test
    public void testQueryByFirstName() {

        Customer customer = DataUtil.createRandomCustomer();
        String key=customer.getId();
        String firstName="zcfirstName";
        String lastName="ccLastName";
        customer.setFirstName(firstName);
        customer.setLastName(lastName);

        String addressLine1 = "123, Main street";
        String addressLine2="Apt 101";
        String state="Tx";
        String zip="75056";
        // Address
        CustomerAddress address=customer.getAddresses().get(0);
        address.setAddressLine1(addressLine1);
        address.setAddressLine2(addressLine2);
        address.setState(state);
        address.setState(state);
        address.setZip(zip);

        // Credit Card
        CreditCard creditCard=customer.getCreditCards().get(0);
        String creditCardNumber="4236541236541234";
        String cvv="956";
        creditCard.setCcardNumber(creditCardNumber);
        creditCard.setCvvCode(cvv);
        geodeService.putCustomerData(customer);
        // call the OQL query
        List<?> queryResults= geodeService.getCreditCardByFirstName(firstName);
        Object retObject=queryResults.get(0);
        List retCreditCards= (List) retObject;
        CreditCard retCreditCard=(CreditCard) retCreditCards.get(0);
        // Assert Credit card
        assertThat(creditCardNumber, equalTo(retCreditCard.getCcardNumber()));
        assertThat(cvv, equalTo(retCreditCard.getCvvCode()));


    }
    
}

/*

query --query=" select id, c.addresses[0].addressLine1  from /Customer c "

query --query=" select id, c.creditCards[0].ccardNumber, c.creditCards[0].cvvCode, c.addresses from /Customer c "

 */
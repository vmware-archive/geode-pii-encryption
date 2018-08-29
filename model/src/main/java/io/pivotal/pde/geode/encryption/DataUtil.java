package io.pivotal.pde.geode.encryption;

import io.pivotal.pde.geode.encryption.model.CreditCard;
import io.pivotal.pde.geode.encryption.model.CreditCardType;
import io.pivotal.pde.geode.encryption.model.Customer;
import io.pivotal.pde.geode.encryption.model.CustomerAddress;

import java.util.*;

public class DataUtil {

    private static Random random = new Random();


    public static Customer generateRandomCustomer(){

        Customer customer=new Customer();
        customer.setId(UUID.randomUUID().toString());
        customer.setFirstName("fName-"+randomChars(5 + new Random().nextInt(5)));
        customer.setLastName("lName-"+randomChars(5 + new Random().nextInt(5)));
        customer.setMiddleName("mName-"+randomChars(5 + new Random().nextInt(5)));
        customer.setDisplayName("dName-"+randomChars(5 + new Random().nextInt(5)));
        customer.setGender(random.nextBoolean()? "M" : "F");
        customer.setCreatedDate(new Date().getTime());
        customer.setLastUpdatedDate(new Date().getTime());

        // Address
        List<CustomerAddress> addresses=new ArrayList<CustomerAddress>();
        for (int i=0;i < 1+random.nextInt(3); i++){
            CustomerAddress customerAddress=new CustomerAddress();
            customerAddress.setAddressLine1("aLine1-"+randomChars(5 + new Random().nextInt(5)));
            customerAddress.setAddressLine2("aLine2-"+randomChars(5 + new Random().nextInt(5)));
            customerAddress.setCity("city-"+randomChars(5 + new Random().nextInt(5)));
            customerAddress.setState(randomChars(2).toUpperCase());
            addresses.add(customerAddress);
        }
        customer.setAddresses(addresses);

        // Credit cards
        List<CreditCard> creditCards=new ArrayList<CreditCard>();
        for (int i=0;i < 1+random.nextInt(3); i++) {
            CreditCard creditCard=new CreditCard();
            creditCard.setCcardNumber(randomChars(16));
            creditCard.setCcardType(random.nextBoolean() ? CreditCardType.VISA: CreditCardType.MASTERCARD);
            creditCard.setCvvCode(randomChars(3));
            creditCard.setExpiryDate(new Date(2+random.nextInt()).getTime());
            creditCards.add(creditCard);
        }
        customer.setCreditCards(creditCards);
        return customer;

    }

    private static String randomChars(int length) {
        StringBuilder rtn = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int c = random.nextInt(26) + 97;
            rtn.append((char) c);
        }
        String result = rtn.toString();
        return Character.toUpperCase(result.charAt(0)) + result.substring(1);
    }
}

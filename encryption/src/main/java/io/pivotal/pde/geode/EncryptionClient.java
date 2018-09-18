package io.pivotal.pde.geode;

import io.pivotal.pde.geode.encryption.DataUtil;
import io.pivotal.pde.geode.encryption.model.Customer;
import io.pivotal.pde.geode.service.IGeodeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EncryptionClient {
    public static void main(String[] args) {
        SpringApplication.run(EncryptionClient.class, args);
    }


    @Bean
    CommandLineRunner runClientApp(final IGeodeService geodeService) {
        return new CommandLineRunner() {
            @Override
            public void run(String... arg0) throws Exception {

                Customer customer1 = DataUtil.createRandomCustomer();
                Customer customer2 = DataUtil.createRandomCustomer();

                geodeService.putCustomerData(customer1);
                geodeService.putCustomerData(customer2);

                geodeService.queryAllCustomers();

            }

        };
    }
}


/*

start locator
start server --cache-xml-file=/opt/gemfire/config/cache.xml

query --query="select id, c.firstName, c.gender, c.creditCards[0].ccardNumber, c.creditCards[0].cvvCode, c.addresses[0].primary from /Customer c where c.id='3b77aabc-de0e-4dbc-b2a2-bfc1da0caaaf'"

 */
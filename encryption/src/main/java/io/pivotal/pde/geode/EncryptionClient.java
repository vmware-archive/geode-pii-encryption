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

                Customer customer1 = DataUtil.generateRandomCustomer();
                Customer customer2 = DataUtil.generateRandomCustomer();

                geodeService.putCustomerData(customer1);
                geodeService.putCustomerData(customer2);
/*
                // Bulk loading customer data
                for (int i=0;i<10;i++) {
                    Customer customer = DataUtil.generateRandomCustomer();
                    geodeService.putCustomerData(customer);
                }
*/

            geodeService.queryAllCreditCards();
            }

        };
    }
}


/*

start locator
start server --cache-xml-file=/opt/gemfire/config/cache.xml


 */
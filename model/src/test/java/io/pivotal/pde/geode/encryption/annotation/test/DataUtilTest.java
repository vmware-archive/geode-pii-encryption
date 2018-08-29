package io.pivotal.pde.geode.encryption.annotation.test;


import io.pivotal.pde.geode.encryption.DataUtil;
import io.pivotal.pde.geode.encryption.model.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
public class DataUtilTest {

    @Test
    public void TestCustomer() {

        Customer customer=DataUtil.generateRandomCustomer();

        assertTrue(customer.getId() != null ? true : false);
        assertThat(customer.getFirstName(), equalTo(customer.getFirstName()));
    }

}
package io.pivotal.pde.geode.service;

import io.pivotal.pde.geode.CipherType;
import io.pivotal.pde.geode.CryptoUtil;
import io.pivotal.pde.geode.EncyptionPdxSerializer;
import io.pivotal.pde.geode.ICryptoService;
import io.pivotal.pde.geode.encryption.common.IAppCryptoService;
import io.pivotal.pde.geode.encryption.model.CreditCard;
import io.pivotal.pde.geode.encryption.model.Customer;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;
import org.apache.geode.cache.query.*;
import org.apache.geode.pdx.PdxInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Properties;

@Service
public class GeodeServiceImpl implements IGeodeService {

    private static ClientCache cache=null;
    private Region<String, Customer> customerRegion=null;
    private QueryService qs=null;
    QueryService localQS=null;
    private ICryptoService cryptorService;

    @Autowired
    private IAppCryptoService appCryptoService;



    public GeodeServiceImpl() {
        // Create a client cache
        EncyptionPdxSerializer pdxSerializer=new  EncyptionPdxSerializer();

        Properties props=new Properties();
        props.setProperty("classes", "io.pivotal.pde.geode.encryption.model.*");
        pdxSerializer.init(props);

        cache = new ClientCacheFactory()
                .set("cache-xml-file", "client-cache.xml")
                .setPdxSerializer(pdxSerializer)
                .setPdxPersistent(true)
                .create();
        customerRegion=cache.getRegion("/Customer");
        qs=cache.getQueryService();
        localQS = cache.getLocalQueryService();
        cryptorService= CryptoUtil.getCipher(CipherType.AES, "0123456789012345".getBytes(), "01234567890123456789012345678901".getBytes());

    }

    /**
     *
     * @param customer
     */
    @Override
    public void putCustomerData(Customer customer) {
        customerRegion.put(customer.getId(), customer);
    }

    /**
     *
     * @param key
     * @return
     */
    @Override
    public Customer getCustomerData(String key) {
        return customerRegion.get(key);
    }

    /**
     *
     * @return
     * @throws FunctionDomainException
     * @throws TypeMismatchException
     * @throws NameResolutionException
     * @throws QueryInvocationTargetException
     */
    @Override
    public List<Object> queryAllCreditCards() {
        List<Object> dataList= Collections.emptyList();
        try {

            Query query = qs.newQuery("select * from /Customer ");
            SelectResults<Object> results = (SelectResults<Object>) query.execute();

            dataList = results.asList();


            for (Object obj : dataList) {
                if (obj instanceof Customer) {
                    Customer gmData = (Customer) obj;
                    System.out.println("Customer Object :  " + gmData.getId() + ", " + gmData.getFirstName() + " ");
                } else if (obj instanceof PdxInstance) {
                    PdxInstance gmPdxData = (PdxInstance) obj;
                    System.out.println("Customer Object :  " + gmPdxData.getField("id") + ", " + gmPdxData.getField("firstName"));
                } else
                    System.out.println("Object type :" + obj.getClass() + "" + " and value :" + obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
            }
        return dataList;
    }

    public List<Object> getCreditCardByFirstName(String firstName){

        List<Object> dataList= Collections.emptyList();
        String encryptedVal=appCryptoService.encrypt(firstName);
        Query query = qs.newQuery("select c.creditCards from /Customer c where c.firstName= '"+encryptedVal+"'");

        try {
            SelectResults<Object> results = (SelectResults<Object>) query.execute();
            dataList = results.asList();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return dataList;
    }


}
package uz.laboratory.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.security.PrivateKey;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;

    public void registerCustomer(CustomerPayload customerPayload) {
        Customer customer = Customer.builder()
                .firsName(customerPayload.getFirsName())
                .lastName(customerPayload.getLastName())
                .email(customerPayload.getEmail())
                .build();
        customerRepository.saveAndFlush(customer);
        FraudCheckHistoryPayload fraudCheckHistoryPayload = restTemplate.getForObject(
                "http://FRAUD/api/v1/fraud-check/{customerId}", FraudCheckHistoryPayload.class,
                customer.getId()
        );
        if (fraudCheckHistoryPayload.isFraudster) {
            throw new IllegalStateException("fraudster");
        }

    }
}

package uz.laboratory.customer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/customers")

public class CustomerController {
private final CustomerService customerService;
    @PostMapping
    public void registerCustomer(@RequestBody CustomerPayload customerPayload){
        log.info("new customer registration{}",customerPayload);
        customerService.registerCustomer(customerPayload);
    }

}

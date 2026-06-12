package ma.bankcore.compte_service.feign;

import ma.bankcore.compte_service.dto.ClientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
    name = "client-service",
    url = "${client-service.url:http://localhost:8081}"
)
public interface ClientFeignClient {

    @GetMapping("/api/v1/clients/{id}")
    ClientResponse getClientById(@PathVariable Long id);
}
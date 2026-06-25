package ma.bankcore.compte_service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import ma.bankcore.compte_service.dto.NotificationRequest;

@FeignClient(
    name = "notification-service",
    url = "${notification-service.url:http://localhost:8083}"
)

public interface NotificationFeignClient {
	@PostMapping("/api/v1/notifications/envoyer")
	void envoyerNotification(@RequestBody NotificationRequest request);
}

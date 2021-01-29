package it.valeriovaudi.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import software.amazon.awssdk.regions.internal.util.EC2MetadataUtils;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}

@Controller
class IndexController {

    @GetMapping("/index")
    public void index(Model model){
        model.addAttribute("amiId", EC2MetadataUtils.getAmiId());
        model.addAttribute("region", EC2MetadataUtils.getEC2InstanceRegion());
        model.addAttribute("zone", EC2MetadataUtils.getAvailabilityZone());
        model.addAttribute("instanceId", EC2MetadataUtils.getInstanceInfo().getInstanceId());
        model.addAttribute("privateId", EC2MetadataUtils.getInstanceInfo().getPrivateIp());
    }
}
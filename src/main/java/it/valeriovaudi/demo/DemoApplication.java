package it.valeriovaudi.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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

    private final static Logger logger = LoggerFactory.getLogger(IndexController.class);
    public static final String NO_VALE = "no vale";

    private final String imageUrl;

    IndexController(@Value("${imageUrl}") String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @GetMapping("/index")
    public void index(Model model) {
        model.addAttribute("imageUrl", imageUrl);
        model.addAttribute("amiId", amiId());
        model.addAttribute("region", ec2InstanceRegion());
        model.addAttribute("zone", availabilityZone());
        model.addAttribute("instanceId", instanceId());
        model.addAttribute("privateId", privateIp());
    }

    private String privateIp() {
        String privateIp = NO_VALE;
        try {
            privateIp = EC2MetadataUtils.getInstanceInfo().getPrivateIp();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return privateIp;
    }

    private String instanceId() {
        String instanceId = NO_VALE;
        try {
            instanceId = EC2MetadataUtils.getInstanceInfo().getInstanceId();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return instanceId;
    }

    private String availabilityZone() {
        String availabilityZone = NO_VALE;
        try {
            availabilityZone = EC2MetadataUtils.getAvailabilityZone();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return availabilityZone;
    }

    private String ec2InstanceRegion() {
        String ec2InstanceRegion = NO_VALE;
        try {
            ec2InstanceRegion = EC2MetadataUtils.getEC2InstanceRegion();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return ec2InstanceRegion;
    }

    private String amiId() {
        String amiId = NO_VALE;
        try {
            amiId = EC2MetadataUtils.getAmiId();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return amiId;
    }
}
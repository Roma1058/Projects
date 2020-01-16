package roman.kononenko.busterminal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import roman.kononenko.busterminal.entity.Stop;
import roman.kononenko.busterminal.repository.AddressRepository;
import roman.kononenko.busterminal.repository.StopRepository;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import java.util.Scanner;

@SpringBootApplication
public class BusterminalApplication {

    @Autowired
    private StopRepository stopRepository;

    @Autowired
    private AddressRepository addressRepository;

    @PostConstruct
    private void init()
    {
       /* Scanner sc = new Scanner(System.in);
        boolean out = false;

        while(out != true)
        {
            System.out.println("1 - Create");
            System.out.println("2 - Show");
            System.out.println("3 - Exit");

            switch (sc.nextInt())
            {
                case 1:
                    System.out.print("Enter name: ");
                    Stop stop = new Stop();
                    stop.setPlaceName(sc.next());
                    stopRepository.save(stop);
                    break;
                case 2:
                    System.out.println("All:");
                    stopRepository.findAll().forEach(i -> System.out.println(i.getId() + ". " + i.getPlaceName()));
                    break;

                case 3:
                    out = true;
                    break;
            }
        }*/

      /* addressRepository.findAll().forEach(a -> System.out.println(a.getId() + ". " + a.getCountry() + "-"
                                            + a.getCity() + "-" + a.getStreet() + "_" + a.getHouseNum()));

        System.out.print("Choose: ");
        Scanner sc = new Scanner(System.in);
       stopRepository.findAllByAddress_Id(sc.nextLong()).forEach(e -> System.out.println(e.getPlaceName()));*/
    }

    public static void main(String[] args) {
        SpringApplication.run(BusterminalApplication.class, args);
    }

    @Bean
    public ServletWebServerFactory servletContainer() {
        // Enable SSL Trafic
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint securityConstraint = new SecurityConstraint();
                securityConstraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                securityConstraint.addCollection(collection);
                context.addConstraint(securityConstraint);
            }
        };

        // Add HTTP to HTTPS redirect
        tomcat.addAdditionalTomcatConnectors(httpToHttpsRedirectConnector());

        return tomcat;
    }

    /*
    We need to redirect from HTTP to HTTPS. Without SSL, this application used
    port 8090. With SSL it will use port 8443. So, any request for 8090 needs to be
    redirected to HTTPS on 8443.
     */
    private Connector httpToHttpsRedirectConnector() {
        Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
        connector.setScheme("http");
        connector.setPort(8090);
        connector.setSecure(false);
        connector.setRedirectPort(8443);
        return connector;
    }

}

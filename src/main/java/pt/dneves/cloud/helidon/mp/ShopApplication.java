package pt.dneves.cloud.helidon.mp;

import java.util.Set;
import java.util.logging.Logger;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import io.helidon.common.CollectionsHelper;
import io.helidon.microprofile.server.Server;
import pt.dneves.cloud.helidon.mp.endpoint.ProductEndpoint;

@ApplicationPath("/shop")
public class ShopApplication extends Application {

	private static final Logger logger = Logger.getLogger(ShopApplication.class.getName());
	
	
    @Override
    public Set<Class<?>> getClasses() {
        return CollectionsHelper.setOf(ProductEndpoint.class);
    }

    public static void main(String[] args) {
    	
        Server webServer = Server.builder()
                .addApplication(ShopApplication.class)
                .port(9088)
                .build();
        
        webServer.start();
        
        
        logger.info("Server started at: http://localhost:" + webServer.port());
        
    }
    
}

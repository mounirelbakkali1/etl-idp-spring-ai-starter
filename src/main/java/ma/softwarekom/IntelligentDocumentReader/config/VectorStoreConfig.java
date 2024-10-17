package ma.softwarekom.IntelligentDocumentReader.config;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VectorStoreConfig {

    @Bean
    public Driver driver() {
        return GraphDatabase.driver("neo4j://localhost:7687",
                AuthTokens.basic("admin", "MyStrongPassword"));
    }
}

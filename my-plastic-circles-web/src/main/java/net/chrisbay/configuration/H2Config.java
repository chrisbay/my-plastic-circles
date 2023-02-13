package net.chrisbay.configuration;

import org.h2.tools.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.sql.SQLException;
import java.util.Set;

@Configuration
public class H2Config implements ServletContainerInitializer {

    @Bean(initMethod="start", destroyMethod="stop")
    @DependsOn("h2WebServer")
    public Server h2Server() {
        Server server = null;
        try {
            server = Server.createTcpServer( "-tcp", "-tcpAllowOthers", "-tcpPort", "9092" );
        } catch(SQLException e ) {
            e.printStackTrace();
        }
        return server;
    }

    @Bean(initMethod="start", destroyMethod="stop")
    public Server h2WebServer() {
        Server webServer = null;
        try {
            webServer = Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return webServer;
    }

    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {

    }
}

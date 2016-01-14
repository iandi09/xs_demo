package de.innovas.xsdemo.webapp;

import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.InstanceManager;
import org.apache.tomcat.SimpleInstanceManager;
import org.eclipse.jetty.apache.jsp.JettyJasperInitializer;
import org.eclipse.jetty.plus.annotation.ContainerInitializer;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.SecureRequestCustomizer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.webapp.WebAppContext;

public class EmbeddedJettyMain {

  public static void main( String[] args ) throws Exception {

    Server server = new Server();

    // Creating the web application context
    WebAppContext webapp = new WebAppContext();
    webapp.setResourceBase( "src/main/webapp" );
    webapp.setContextPath( "/" );
    webapp.setAttribute( "org.eclipse.jetty.containerInitializers", jspInitializers() );
    webapp.setAttribute( InstanceManager.class.getName(), new SimpleInstanceManager() );

    server.setHandler( webapp );

    // HTTP Configuration
    // HttpConfiguration http = new HttpConfiguration();
    // http.addCustomizer( new SecureRequestCustomizer() );

    // Configuration for HTTPS redirect
    // http.setSecurePort( 8443 );
    // http.setSecureScheme( "https" );
    ServerConnector connector = new ServerConnector( server );
    // connector.addConnectionFactory( new HttpConnectionFactory( http ) );
    // Setting HTTP port
    connector.setPort( 8080 );

    // HTTPS configuration
    HttpConfiguration https = new HttpConfiguration();
    https.addCustomizer( new SecureRequestCustomizer() );

    // Configuring SSL
    // SslContextFactory sslContextFactory = new SslContextFactory();

    // Defining keystore path and passwords
    // sslContextFactory.setKeyStorePath( EmbeddedJettyMain.class.getResource( "keystore" ).toExternalForm() );
    // sslContextFactory.setKeyStorePassword( "javacodegeeks" );
    // sslContextFactory.setKeyManagerPassword( "javacodegeeks" );

    // Configuring the connector
    // ServerConnector sslConnector = new ServerConnector( server, new SslConnectionFactory( sslContextFactory,
    // "http/1.1" ), new HttpConnectionFactory( https ) );
    // sslConnector.setPort( 8443 );

    // Setting HTTP and HTTPS connectors
    server.setConnectors( new Connector[] { connector } );

    // Starting the Server
    server.start();
    server.join();

  }

  private static List< ContainerInitializer > jspInitializers() {
    JettyJasperInitializer sci = new JettyJasperInitializer();
    ContainerInitializer initializer = new ContainerInitializer( sci, null );
    List< ContainerInitializer > initializers = new ArrayList< ContainerInitializer >();
    initializers.add( initializer );
    return initializers;
  }
}

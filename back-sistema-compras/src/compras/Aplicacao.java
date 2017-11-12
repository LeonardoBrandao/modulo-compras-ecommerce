package compras;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import java.util.EnumSet;
import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import org.eclipse.jetty.servlets.CrossOriginFilter;

public class Aplicacao extends Application<Configuracao> {

    public static void main(String[] args) throws Exception {
        String[] param = {"server", "conf/server_config.yml"};
        new Aplicacao().run(param);
    }

    @Override
    public void run(Configuracao t, Environment e) throws
            Exception {
        final FilterRegistration.Dynamic cors
                = e.servlets().addFilter("CORS", CrossOriginFilter.class);
        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "X-RequestedWith,Content-Type,Accept,Origin");
        cors.setInitParameter("allowedMethods",
                "OPTIONS,GET,PUT,POST,DELETE,HEAD");
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
        final Recurso recurso = new Recurso(t.getUrl(),
                t.getDriverJdbc(),
                t.getUser(), t.getPassword());
        e.jersey().register(recurso);
    }
}

package compras;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class Aplicacao extends Application<Configuracao> {

    public static void main(String[] args) throws Exception {
        String[] param = {"server", "conf/server_config.yml"};
        new Aplicacao().run(param);
    }

    @Override
    public void run(Configuracao t, Environment e) throws Exception {
        final Recurso recurso = new Recurso(t.getUrl(),
                t.getDriverJdbc(),
                t.getUser(),
                t.getPassword());
        e.jersey().register(recurso);
    }
}

package compras;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Configuracao extends io.dropwizard.Configuration{

  @JsonProperty private String url;
  @JsonProperty private String driverJdbc;
  @JsonProperty private String user;
  @JsonProperty private String password;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDriverJdbc() {
        return driverJdbc;
    }

    public void setDriverJdbc(String driverJdbc) {
        this.driverJdbc = driverJdbc;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }  
    
}

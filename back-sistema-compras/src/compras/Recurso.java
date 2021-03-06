package compras;

import dao.ComprasDaoException;
import dao.ConexaoException;
import dao.ConexaoInterface;
import dao.ConexaoJavaDb;
import dao.ProdutoDaoInterface;
import ch.qos.logback.core.status.Status;
import dao.ProdutoDaoRelacional;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/compras")
public class Recurso {

    private String url;
    private String driverJdbc;
    private String user;
    private String password;
    private ProdutoDaoInterface produtoDao;

    public Recurso(String url, String driverJdbc, String user, String password) throws ComprasDaoException {
        this.url = url;
        this.driverJdbc = driverJdbc;
        this.user = user;
        this.password = password;
        ConexaoInterface conexao = new ConexaoJavaDb(url, driverJdbc, user, password);
        try {
            produtoDao = new ProdutoDaoRelacional(conexao);
        } catch (ConexaoException | ComprasDaoException ex) {
            Logger.getLogger(Recurso.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @GET
    @Path("/produtos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listaProdutos() {
        List<Produto> produtos;
        try {
            produtos = produtoDao.obterTodos();
            return Response.ok(produtos).build();
        } catch (ComprasDaoException ex) {
            Logger.getLogger(Recurso.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(Status.ERROR).build();
    }

    @GET
    @Path("/produto/cod/{codigo}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response pesquisarCodProdutos(@PathParam("codigo") String codigo) {
        Produto produto;
        try {
            produto = produtoDao.buscarCod(Integer.parseInt(codigo));
            return Response.ok(produto).build();
        } catch (ComprasDaoException ex) {
            Logger.getLogger(Recurso.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(Status.ERROR).build();
    }
    
    @GET
    @Path("/produto/{nome}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response pesquisarProdutosNome(@PathParam("nome") String nome) {
        Produto produto;
        try {
            produto = produtoDao.buscarNome(nome);
            return Response.ok(produto).build();
        } catch (ComprasDaoException ex) {
            Logger.getLogger(Recurso.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(Status.ERROR).build();
    }

    @POST
    @Path("/produto")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response adicionarProduto(Produto p){
        try {
            produtoDao.inserir(p);
            return Response.ok(p).build();
        } catch (ComprasDaoException ex) {
            Logger.getLogger(Recurso.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(Status.ERROR).build();
    }
}

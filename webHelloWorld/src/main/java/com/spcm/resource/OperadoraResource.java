package com.spcm.resource;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.spcm.Operadora;
import com.spcm.bo.OperadoraBO;
import com.spcm.to.OperadoraTO;

@Path("operadoras")
public class OperadoraResource {

	private static final Gson gsonTrace = new GsonBuilder().setDateFormat("dd/MM/yyyy HH:mm:ss").create();

	@Inject
	OperadoraBO boOperadora;

	@POST
	@Path("/getAllOperadoras")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Operadora> getAllOperadora() throws Exception {

		return boOperadora.getAllOperadora();
	}

	@POST
	@Path("/insertOperadora")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public OperadoraTO saveOperadora(String OperadoraTO) throws Exception {

		OperadoraTO retorno = boOperadora.saveOperadora(OperadoraTO);
		getJsonFromObjeto(retorno);
		return retorno;
	}

	@POST
	@Path("/updateOperadora")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public OperadoraTO updateOperadora(String OperadoraTO) throws Exception {

		OperadoraTO retorno = boOperadora.updateOperadora(OperadoraTO);
		getJsonFromObjeto(retorno);
		return retorno;
	}

	@POST
	@Path("/deleteOperadora/{idOperadora}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public OperadoraTO deleteOperadora(@PathParam("idOperadora") Integer idOperadora) throws Exception {

		OperadoraTO operadoraTO = new OperadoraTO();
		try {
			OperadoraTO retorno = boOperadora.deleteOperadora(idOperadora);
			operadoraTO.setMensagem(retorno.getMensagem());
			getJsonFromObjeto(retorno);

		} catch (Exception e) {
			operadoraTO.setMensagem("Falha ao deletar Operadora, Contate o administrador!" + e.getMessage());
		}
		return operadoraTO;
	}

	@POST
	@Path("/getFindByIdOperadora/{idOperadora}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Operadora getFindByIdOperadora(@PathParam("idOperadora") Integer idOperadora) throws Exception {

		return boOperadora.getFindByIdOperadora(idOperadora);
	}

	private String getJsonFromObjeto(Object object) {
		String retorno = "";
		try {
			retorno = gsonTrace.toJson(object);
		} catch (Exception e) {
			retorno = "Ocorreu um erro durante o processo de serializacao do objeto '" + Object.class.getName()
					+ "' para json. ERRO: " + e.getMessage();
		}
		return retorno;
	}

}

package com.spcm.bo;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.spcm.Operadora;
import com.spcm.dao.OperadoraDAO;
import com.spcm.to.OperadoraTO;

public class OperadoraBO {

	@Inject
	protected EntityManager em;

	@Inject
	OperadoraDAO operadoraDAO;

	/**
	 * Valida os campos do cadastro e deserializa json
	 * 
	 * @author jonata
	 * @param formularioTO
	 * @return { OperadoraTO }
	 */

	public OperadoraTO validaFormulario(String formularioTO) {

		OperadoraTO operadoraTO = new OperadoraTO();
		try {
			Type listOfA = new TypeToken<OperadoraTO>() {
			}.getType();
			Gson g = new Gson();
			OperadoraTO result = g.fromJson(formularioTO, listOfA);

			operadoraTO.setIdOperadora(result.getIdOperadora());
			operadoraTO.setName(result.getName());
			operadoraTO.setCode(result.getCode());
			operadoraTO.setCategory(result.getCategory());
			operadoraTO.setPrice(result.getPrice());

			if (result.getName().isEmpty()) {
				operadoraTO.setName("Nome é obrigatório");
				return operadoraTO;
			} else if (result.getCode().isEmpty()) {
				operadoraTO.setName("Codigo é obrigatório");
				return operadoraTO;
			} else if (result.getCategory().isEmpty()) {
				operadoraTO.setCategory("Categoria é obrigatório");
				return operadoraTO;
			}

		} catch (Exception e) {
			operadoraTO.setMensagem("Falha na validação do formulario de operadora!" + e.getMessage());
		}

		return operadoraTO;
	}

	/**
	 * Realiza a desestruturação TO para objeto mapeado deixando pronto para
	 * inserção
	 * 
	 * @author jonata
	 * @param formularioTO
	 * @return { OperadoraTO }
	 * @throws Exception
	 */
	public OperadoraTO saveOperadora(String formularioTO) throws Exception {

		OperadoraTO operadoraTO = new OperadoraTO();
		try {

			operadoraTO = validaFormulario(formularioTO);
			Operadora operadora = new Operadora();

			operadora.setName(operadoraTO.getName());
			operadora.setCode(operadoraTO.getCode());
			operadora.setPrice(operadoraTO.getPrice());
			operadora.setCategory(operadoraTO.getCategory());

			operadora = operadoraDAO.saveOperadora(operadora);
		} catch (Exception e) {
			operadoraTO.setMensagem("Falha na inserção da operadora!" + e.getMessage());
		}
		return operadoraTO;
	}

	/**
	 * Realiza a desestruturação TO para objeto mapeado deixando pronto para
	 * atualização
	 * 
	 * @author jonata
	 * @param formularioTO
	 * @return { OperadoraTO }
	 * @throws Exception
	 */

	public OperadoraTO updateOperadora(String formularioTO) throws Exception {

		OperadoraTO operadoraTO = new OperadoraTO();
		try {

			operadoraTO = validaFormulario(formularioTO);
			Operadora operadora = new Operadora();

			operadora.setIdOperadora(operadoraTO.getIdOperadora());
			operadora.setName(operadoraTO.getName());
			operadora.setCode(operadoraTO.getCode());
			operadora.setPrice(operadoraTO.getPrice());
			operadora.setCategory(operadoraTO.getCategory());

			operadora = operadoraDAO.updateOperadora(operadora);
		} catch (Exception e) {
			operadoraTO.setMensagem("Falha na atualização da operadora!" + e.getMessage());
		}
		return operadoraTO;

	}

	/**
	 * Obtem todas as Operadoras cadastradas
	 * 
	 * @author jonata
	 * @return { Lista de Operadoras }
	 * @throws Exception
	 */
	public List<Operadora> getAllOperadora() throws Exception {

		Operadora operadora = new Operadora();
		List<Operadora> listaOperadoras = new ArrayList<Operadora>();
		try {
			listaOperadoras = operadoraDAO.getAllOperadoras();

		} catch (Exception e) {
			operadora.setMensagem("Falha ao lista contatos, contate o administrador!");
		}
		return listaOperadoras;

	}

	/**
	 * Realiza a exclusao de uma operadora selecionada
	 * 
	 * @author jonata
	 * @param idOperadora
	 * @return { mensagem }
	 */
	public OperadoraTO deleteOperadora(Integer idOperadora) {

		OperadoraTO operadoraTO = new OperadoraTO();
		try {
			operadoraDAO.deleteOperadora(idOperadora);
			operadoraTO.setMensagem("Excluido com sucesso!");
		} catch (Exception e) {
			operadoraTO.setMensagem("Falha ao excluir operadora, contate o administrador!");
		}

		return operadoraTO;
	}

	/**
	 * Obtem uma operadora selecionada
	 * 
	 * @author jonata
	 * @param idOperadora
	 * @return { Operadora }
	 * @throws Exception
	 */
	public Operadora getFindByIdOperadora(Integer idOperadora) throws Exception {

		Operadora operadora = new Operadora();
		try {
			operadora = operadoraDAO.getFindByIdOperadora(idOperadora);

		} catch (Exception e) {
			operadora.setMensagem("Falha ao listar Operadora por ID, contate o administrador!");
		}
		return operadora;

	}
}

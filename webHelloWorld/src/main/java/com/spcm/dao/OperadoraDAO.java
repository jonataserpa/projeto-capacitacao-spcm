package com.spcm.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.spcm.Operadora;
import com.spcm.to.OperadoraTO;

@Singleton
@Model
public class OperadoraDAO implements Serializable {

	@Inject
	protected EntityManager em;

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getRootLogger();
	private static final String TAG = "<OperadoraDAO>";

	/**
	 * Insere Operadora na base de dados
	 * 
	 * @author jonata
	 * @param object
	 * @return { Operadora }
	 * @throws Exception
	 */
	public Object persist(Object object) throws Exception {
		try {
			em.getTransaction().begin();
			em.persist(object);
			em.flush();
			em.clear();
			em.getTransaction().commit();
		} catch (Exception e) {
			logger.error(TAG + e.getMessage());
			em.getTransaction().rollback();
			throw e;
		}
		return object;
	}

	/**
	 * Atualiza Operadora
	 * 
	 * @author jonata
	 * @param object
	 * @return { Operadora }
	 * @throws Exception
	 */
	public Object merge(Object object) throws Exception {
		try {
			em.getTransaction().begin();
			em.merge(object);
			em.flush();
			em.clear();
			em.getTransaction().commit();
		} catch (Exception e) {
			logger.error(TAG + e.getMessage());
			em.getTransaction().rollback();
			throw e;
		}
		return object;
	}

	/**
	 * Metodo que chama a persistencia
	 * 
	 * @author jonata
	 * @param operadora
	 * @return { Operadora }
	 * @throws Exception
	 */
	public Operadora saveOperadora(Operadora operadora) throws Exception {

		try {
			persist(operadora);

		} catch (Exception e) {
			logger.error(TAG + e.getMessage());
		}
		return operadora;

	}

	/**
	 * Metodo que chama o merge
	 * 
	 * @author jonata
	 * @param operadora
	 * @return { Operadora }
	 * @throws Exception
	 */

	public Operadora updateOperadora(Operadora operadora) throws Exception {

		try {
			merge(operadora);

		} catch (Exception e) {
			logger.error(TAG + e.getMessage());
		}
		return operadora;

	}

	/**
	 * Realiza a exclusao de Operadora
	 * 
	 * @author jonata
	 * @param idContact
	 * @return { Mensagem }
	 */
	public OperadoraTO deleteOperadora(Integer idContact) {

		OperadoraTO operadoraTO = new OperadoraTO();
		try {
			em.getTransaction().begin();
			Operadora operadora = em.find(Operadora.class, idContact);
			em.remove(operadora);
			em.getTransaction().commit();
			operadoraTO.setMensagem("Operadora excluida com sucesso!");
		} catch (Exception e) {
			em.getTransaction().rollback();
			operadoraTO.setMensagem("Erro ao excluir a operadora!");
		}

		return operadoraTO;
	}

	/**
	 * Obtem todas operadoras cadastradas
	 * 
	 * @author jonata
	 * @return { Operadoras }
	 * @throws Exception
	 */
	public List<Operadora> getAllOperadoras() throws Exception {

		List<Operadora> listaOperadoras = new ArrayList<Operadora>();
		try {
			em.getTransaction().begin();

			TypedQuery<Operadora> query = em.createQuery("SELECT o FROM Operadora o", Operadora.class);
			listaOperadoras = query.getResultList();

			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw e;
		}
		return listaOperadoras;

	}

	/**
	 * Obtem a operadora selecionada
	 * 
	 * @author jonata
	 * @param idOperadora
	 * @return { Operadora }
	 */
	public Operadora getFindByIdOperadora(Integer idOperadora) {

		Operadora operadora;
		try {
			em.getTransaction().begin();
			operadora = em.find(Operadora.class, idOperadora);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw e;
		} finally {
			em.close();
		}

		return operadora;
	}

}

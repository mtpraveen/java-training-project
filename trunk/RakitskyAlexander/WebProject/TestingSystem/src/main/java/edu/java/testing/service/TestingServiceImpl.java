/**
 * 
 */
package edu.java.testing.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.java.testing.dao.TestingDAO;
import edu.java.testingSystem.domain.Testing;

/**
 * @author Администратор
 *
 */
@Service
public class TestingServiceImpl implements TestingService {
	@Autowired
    private TestingDAO testingDAO;
	/**
	 * @see edu.java.testing.service.TestingService#addTesting(edu.java.testingSystem.domain.Testing)
	 */
	@Transactional
	public void addTesting(Testing tesitng) {
		testingDAO.addTesting(tesitng);
	}

	/**
	 * @see edu.java.testing.service.TestingService#removeTesting(java.lang.Integer)
	 */
	@Transactional
	public void removeTesting(Integer id) {
		testingDAO.removeTesting(id);
	}

	/**
	 * @see edu.java.testing.service.TestingService#listTesting()
	 */
	@Transactional
	public List<Testing> listTesting() {
		return testingDAO.listTesting();
	}
	
}

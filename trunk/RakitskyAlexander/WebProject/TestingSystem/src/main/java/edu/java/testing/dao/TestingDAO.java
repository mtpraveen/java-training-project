/**
 * 
 */
package edu.java.testing.dao;

import java.util.List;

import edu.java.testingSystem.domain.Testing;

/**
 * @author �������������
 *
 */
public interface TestingDAO {
	public void addTesting(Testing tesitng);
	public void removeTesting(Integer id);
	public List<Testing> listTesting();
}
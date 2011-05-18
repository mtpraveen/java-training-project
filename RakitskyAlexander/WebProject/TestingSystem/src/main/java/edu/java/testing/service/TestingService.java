/**
 * 
 */
package edu.java.testing.service;

import java.util.List;

import edu.java.testingSystem.domain.Testing;
import edu.java.testingSystem.domain.User;

/**
 * @author Администратор
 *
 */
public interface TestingService {
	public void addTesting(Testing tesitng);
	public void removeTesting(Integer id);
	public List<Testing> listTesting();
	public List<Testing> listTestingForUser(User user);
}

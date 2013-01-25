package epam.course.webproject.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import epam.course.webproject.domain.User;
import epam.course.webproject.repository.UserRepository;
import epam.course.webproject.service.UserService;

@Service("userService")
@Repository
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional(readOnly=true)
	public User findById(String login) {
		return userRepository.findOne(login);
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}
	
	
	@Transactional(readOnly=true)
	public List<User> findAll() {
//		List<Section> sections=(List<Section>) sectionRepository.findAll();
//		return sections;
//		new ArrayList<Section>((Collection<? extends Section>) sectionRepository.findAll());
		return Lists.newArrayList(userRepository.findAll());
	}

	@Override
	public void remove(String login) {
		userRepository.delete(login);
		
	}

}

package by.epam.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.epam.blog.dao.TopicRepository;
import by.epam.blog.model.Topic;

/**
 * @author Dmitry_Goncharov
 *
 */
@Service
@Transactional
public class TopicServiceImpl {
	@Autowired
	private TopicRepository topicRepository;
	
	public Topic findTopicById(Long topic) {
		return topicRepository.findOne(topic);
	}
	public Long saveTopic(Topic topic) {
		return topicRepository.save(topic).getId();
	}
}

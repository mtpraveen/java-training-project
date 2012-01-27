package com.epam.mvc3.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.mvc3.model.Tag;
import com.epam.mvc3.model.Topic;
import com.epam.mvc3.repository.TopicRepository;

@Service
@Transactional
public class TopicService {
	@Autowired
	private TopicRepository topicRepository;
	
	public int saveTopic(Topic topic){
		return topicRepository.save(topic).getId();
		
	}
	
	public Iterable<Topic> findAllTopics(){
		return topicRepository.findAll();
		
	}
	
	public Topic findTopicByID(Long id){
		return topicRepository.findOne(id);
	}
	
	public List<Topic> findTopicsByTag(Tag tag){
		return topicRepository.findAllByTopicTag(tag);
	}

}

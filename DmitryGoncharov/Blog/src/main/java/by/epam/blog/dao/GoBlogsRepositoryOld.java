package by.epam.blog.dao;

import java.util.Collection;
import java.util.HashMap;

import by.epam.blog.model.Blog;
import by.epam.blog.model.Topic;
import by.epam.blog.model.User;

/**
 * @author Dmitry_Goncharov
 *
 */
public class GoBlogsRepositoryOld implements BlogsRepositoryOld {
	private HashMap<Long, Blog> blogs;

	public GoBlogsRepositoryOld(){
		init();
	}
	private void init(){
		/*
		User user1 = new User();
		User user2 = new User();
		User user3 = new User();
		user1.setName("Dmitry");
		user2.setName("Maksim");
		user3.setName("Daniil");
		blogs=new HashMap<Long, Blog>();
		Blog blog1 = new Blog();
		Blog blog2 = new Blog();
		Blog blog3 = new Blog();
		
		blog1.setId(1L);
		blog1.setAuthor(user1);
		blog1.getTopics().add(new Topic("������ ������","������ � ������ ������ ��� ��� ���!!!"));
		blog1.getTopics().add(new Topic("������ ������","������� ���������� � ������� ��������� �����. �� ����������� �������� ������������� ��������� ������� � Picasa, ����� � ���� ���� ������ ��������� � ���� �������� � Picasa ��� ���� �������� �� Google Docs. ��� ����� ������������ ��� �� �����, ��� � � ��������� �������. ��������� ����������."));
		
		blog2.setId(2L);
		blog2.setAuthor(user2);
		blog2.getTopics().add(new Topic("��� �������","� ������� �� ������ ������ ��������������, ����� �������� ������ ����� ���������� ����������� ������� ���������� AdSense, ������ ������, ����� ����� ����������� � ���� ��������� � �������� ftp-�������. ����� � ���, ��� �������� �� ���� ����� ��������. ��, ��� �� ������ ������ ������ �������, ��� ����� ����� �������� ��������������� ������� ����� � ������������ �������� (��� ���� �������� ��������� ����, ����������� ����� ����������������, �������� �����, ��������� ��� ���� ��������-������). ��������� ������������ ����� �������� ����. ����� ���������� � ����� ����������� �����, ������ �������� ���������������� �� ��� ������, � �� ������ �� ������� ��������, � ������� �� ��."));
		blog2.getTopics().add(new Topic("��� Java","����������� ���� ������� � �������� ��������� ����� � ������ � �����; ������ �� ������ � ��������� ������, ����������� ��������� � ����."));
		blog2.getTopics().add(new Topic("��������","����� �� SEO ����������� ����� �������. SEO �������, ������ � ������ ���������� ���������� �����������"));
		
		blog3.setId(3L);
		blog3.getTopics().add(new Topic("�������","��� ������-�� ������� ��� ���������� ����� ������� ��� ��� ������ � ���� ������. ������ �� ���� �������"));
		blog3.setAuthor(user3);
		
		
		blogs.put(1L, blog1);
		blogs.put(2L, blog2);
		blogs.put(3L, blog3);
		/**/
	}
	
	public Blog createBlog() {
		// TODO Auto-generated method stub
		return null;
	}

	public Blog findBlogById(long id) {
		return blogs.get(id);
	}

	public Blog createBlog(String subject, User user) {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection<Blog> findAllBlogs() {
		// TODO Auto-generated method stub
		return blogs.values();
	}

	public void deleteBlog(long id) {
		// TODO Auto-generated method stub
		
	}
}

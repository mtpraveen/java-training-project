package edu.java.auction.dao;

import java.util.List;

import edu.java.auction.domain.Tovar;

public interface TovarDAO {

	public void addTovar(Tovar tovar);

	public List<Tovar> listTovar();

	public void removeTovar(Integer id);
	public void payTovar(Integer id);

	public List<Tovar> listFindTovar(String name);
}
package edu.java.auction.service;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.java.auction.dao.TovarDAO;
import edu.java.auction.domain.Tovar;
 
 
@Service
public class TovarServiceImpl implements TovarService {
 
    @Autowired
    private TovarDAO tovarDAO;
 
    @Transactional
    public void addTovar(Tovar tovar) {
    	tovarDAO.addTovar(tovar);
    }
 
    @Transactional
    public List<Tovar> listTovar() {
 
        return tovarDAO.listTovar();
    }
 
    @Transactional
    public void removeTovar(Integer id) {
    	tovarDAO.removeTovar(id);
    }
    
    
    @Transactional
    public void payTovar(Integer id) {
    	tovarDAO.payTovar(id);
    }
    
 
  
    @Transactional
	public List<Tovar> listFindTovar(String name){
    	return tovarDAO.listFindTovar(name);
    }
}

package epam.com.votemanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import epam.com.votemanager.dao.VariantDAO;
import epam.com.votemanager.domain.Variant;


@Service
public class VariantService implements IVariantService {

	@Autowired
	private VariantDAO variantDAO;
	
	@Transactional
	public void addVariant(Variant variant) {
		variantDAO.addVariant(variant);
	}

	@Transactional
	public List<Variant> listVariants() {
		return variantDAO.listVariants();
	}

	@Transactional
	public void removeVariant(Integer id) {
		variantDAO.removeVariant(id);
	}

}

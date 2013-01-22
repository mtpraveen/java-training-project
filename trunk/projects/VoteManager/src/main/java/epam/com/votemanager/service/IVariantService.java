package epam.com.votemanager.service;

import java.util.List;

import epam.com.votemanager.domain.Variant;


public interface IVariantService {

    public void addVariant(Variant variant);

    public List<Variant> listVariants();

    public void removeVariant(Integer id);
	
}

package epam.com.votemanager.dao;

import java.util.List;

import epam.com.votemanager.domain.Variant;

public interface IVariant {

	public void addVariant(Variant variant);

	public List<Variant> listVariants();

	public void removeVariant(Integer id);

}

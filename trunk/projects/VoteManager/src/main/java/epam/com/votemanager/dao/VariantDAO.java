package epam.com.votemanager.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import epam.com.votemanager.domain.Variant;


@Repository
public class VariantDAO implements IVariant{

    @Autowired
    private SessionFactory sessionFactory;
	
	@Override
	public void addVariant(Variant variant) {
		sessionFactory.getCurrentSession().save(variant);
	}

	@SuppressWarnings("unchecked")
	public List<Variant> listVariants() {
        return sessionFactory.getCurrentSession().createQuery("from Variant")
                .list();
	}

	@Override
	public void removeVariant(Integer id) {
        Variant variant = (Variant) sessionFactory.getCurrentSession().load(
        		Variant.class, id);
        if (null != variant) {
            sessionFactory.getCurrentSession().delete(variant);
        }
	}

}

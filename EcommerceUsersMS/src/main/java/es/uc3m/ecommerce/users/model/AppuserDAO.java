package es.uc3m.ecommerce.users.model;

import org.springframework.data.repository.CrudRepository;

import es.uc3m.ecommerce.users.model.Appuser;

public interface AppuserDAO extends CrudRepository<Appuser, Long>{

	public Appuser findByUserId(int userId);
	
}

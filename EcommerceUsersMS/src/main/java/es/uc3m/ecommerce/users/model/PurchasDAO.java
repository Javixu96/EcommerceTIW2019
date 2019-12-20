package es.uc3m.ecommerce.users.model;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PurchasDAO extends CrudRepository<Purchas, Long>{

	@Query("SELECT DISTINCT p.confirmationCode FROM Purchas p where p.appuser.userId = :userId")
	public List<Integer> findAllConfirmationCode(int userId);
	
	public List<Purchas> findByConfirmationCode(int confirmationCode);
}

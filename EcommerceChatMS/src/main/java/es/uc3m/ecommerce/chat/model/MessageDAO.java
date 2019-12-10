package es.uc3m.ecommerce.chat.model;

import org.springframework.data.repository.CrudRepository;



public interface MessageDAO extends CrudRepository<Message, Long>{
	
}

package es.uc3m.ecommerce.chat;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.uc3m.ecommerce.chat.model.Message;
import es.uc3m.ecommerce.chat.model.MessageDAO;



@RestController
@CrossOrigin
public class ChatMsController {
	
	
	@Autowired
	MessageDAO msgDAO;
	
	@RequestMapping(method = RequestMethod.POST, value="/message")
	public ResponseEntity<Message> saveMessage(@RequestBody Message msg) {
		return new ResponseEntity<Message>(msgDAO.save(msg),HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/message/{receiverId}")
	public ResponseEntity<List<Message>> readAllMessage(@PathVariable int receiverId) {
		List<Message>  allMessage= (List<Message>) msgDAO.findAll();
		List<Message>  myList=new ArrayList<Message>();
		for(Message myMessage:allMessage) {
			if(myMessage.getReceiver().getUserId()==receiverId) {
				myList.add(myMessage);
			}
		}
		
		return new ResponseEntity<List<Message>>(allMessage,HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/message/{receiverId}/{senderId}")
	public ResponseEntity<List<Message>> readMessage(@PathVariable int receiverId,@PathVariable int senderId) {
		List<Message>  allMessage= (List<Message>) msgDAO.findAll();
		List<Message>  myList=new ArrayList<Message>();
		for(Message myMessage:allMessage) {
			if(myMessage.getReceiver().getUserId()==receiverId && myMessage.getSender().getUserId()==senderId ) {
				myList.add(myMessage);
			}
		}
		
		return new ResponseEntity<List<Message>>(allMessage,HttpStatus.OK);
	}

}

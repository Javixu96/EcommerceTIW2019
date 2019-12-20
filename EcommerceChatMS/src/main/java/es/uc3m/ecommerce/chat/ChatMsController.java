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
	
	//insertar un mensaje
	@RequestMapping(method = RequestMethod.POST, value="/message")
	public ResponseEntity<Message> saveMessage(@RequestBody Message msg) {
		return new ResponseEntity<Message>(msgDAO.save(msg),HttpStatus.CREATED);
	}
	
	//obtener los mensajes no leido para un usuario determinado--> para bandeja de entrada de los mensajes
	@RequestMapping(method = RequestMethod.GET, value="/message/{receiverId}")
	public ResponseEntity<List<Message>> readAllMessage(@PathVariable int receiverId) {
		List<Message>  allMessage= (List<Message>) msgDAO.findAll();
		List<Message>  myList=new ArrayList<Message>();
		
		//filtrar todos los mensajes
		for(Message myMessage:allMessage) {
			if((myMessage.getReceiver().getUserId()==receiverId && myMessage.getIsRead()==0)) {
				myList.add(myMessage);
			}
		}
		if (myList.isEmpty()){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<List<Message>>(myList,HttpStatus.OK);	
		}
	}
	
	//obtener los mensajes no leido para un usuario determinado y un emisor determinado
	@RequestMapping(method = RequestMethod.GET, value="/message/{receiverId}/{senderId}")
	public ResponseEntity<List<Message>> readMessage(@PathVariable int receiverId,@PathVariable int senderId) {
		List<Message>  allMessage= (List<Message>) msgDAO.findAll();
		List<Message>  myList=new ArrayList<Message>();
		for(Message myMessage:allMessage) {
			if(myMessage.getReceiver().getUserId()==receiverId && myMessage.getSender().getUserId()==senderId && myMessage.getIsRead()==0) {
				myList.add(myMessage);
			}
		}
		if (myList.isEmpty()){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<List<Message>>(myList,HttpStatus.OK);	
		}
	}
	
	//leer un mensaje -->cambiar su flag isRead a 1
	@RequestMapping(method = RequestMethod.PUT, value="/message")
	public ResponseEntity<List<Message>> updateMessage(@RequestBody List<Message> MessagesToUpdate) {
		for(Message msg:MessagesToUpdate) {
			msg.setIsRead(1);
			msgDAO.save(msg);
		}
		return new ResponseEntity<List<Message>>(MessagesToUpdate,HttpStatus.OK);
	}

}

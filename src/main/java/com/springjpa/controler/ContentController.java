package com.springjpa.controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springjpa.model.Content;
import com.springjpa.model.ContentDTO;
import com.springjpa.model.Korisnik;
import com.springjpa.repository.ContentRepository;
import com.springjpa.repository.UserRepository;


@RestController
@RequestMapping(path="/content")
public class ContentController {
	@Autowired
	ContentRepository contentRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<String> saveContent(@RequestBody ContentDTO content){
		
		Korisnik owner = userRepo.findByUsername(content.getOwner());
		if (owner==null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unknown user");
		
		Content c = new Content(content.getFileName(), content.getDatatype(), owner, content.getContent());
		contentRepo.save(c);
		return ResponseEntity.ok("Done");
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<Object> getContentByUser(@RequestParam(name = "user") String username){
		
		Korisnik owner = userRepo.findByUsername(username);
		if (owner==null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unknown user");
		
		
		List<Content> list = contentRepo.findByOwnerOrderByIdDesc(owner);
		
		for (Content c : list)
			c.setContent(null);
		
		if (list.size()==0)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Jos uvijek niste dodali nijedan fajl");
		
				
		return ResponseEntity.ok(list);
	}
}

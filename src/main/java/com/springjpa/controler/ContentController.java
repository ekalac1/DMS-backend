package com.springjpa.controler;

import java.util.ArrayList;
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
import com.springjpa.model.SharedDocument;
import com.springjpa.repository.ContentRepository;
import com.springjpa.repository.SharedDocumentRepository;
import com.springjpa.repository.UserRepository;

@RestController
@RequestMapping(path="/content")
public class ContentController {
	@Autowired
	ContentRepository contentRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	SharedDocumentRepository shareRepo;
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<String> saveContent(@RequestBody ContentDTO content){
		
		Korisnik owner = userRepo.findByUsername(content.getOwner());
		if (owner==null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unknown user");
		
		Content c = new Content(content.getFileName(), content.getDatatype(), owner, content.getContent());
		contentRepo.save(c);
		return ResponseEntity.ok("Done");
	}
	
	@RequestMapping(value = "/sharedocument", method = RequestMethod.POST)
	public ResponseEntity<String> saveShare(@RequestParam(name = "user") int user_id, 
			@RequestParam(name = "document") int document_id,
			@RequestParam(name = "type") String type){	
		
		List<SharedDocument> myShared = shareRepo.findByOwnerOrderByIdDesc(userRepo.findByIdKorisnika(user_id));
		int i = 0;
		for (SharedDocument x : myShared) {
			if(x.getDocument().getId() == document_id) {
				i = 1;
			}
		}
		
		if(i == 0) {
			SharedDocument d = new SharedDocument(userRepo.findByIdKorisnika(user_id), contentRepo.findByIdDokumenta(document_id), type);
			shareRepo.save(d);
			return ResponseEntity.ok("Done");
		}
		else {
			return ResponseEntity.ok("Already Exists");
		}
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<Object> getContentByUser(@RequestParam(name = "user") String username){
		
		Korisnik owner = userRepo.findByUsername(username);
		if (owner==null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unknown user");
		
		List<Content> list = contentRepo.findByOwnerOrderByIdDokumentaDesc(owner);

		for (Content c : list)
			c.setContent(null);
		
		if (list.size()==0)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Jos uvijek niste dodali nijedan fajl");
		
				
		return ResponseEntity.ok(list);
	}
	
	@RequestMapping(value = "workspace", method = RequestMethod.GET)
	public ResponseEntity<Object> getWorkSpaceByUser(@RequestParam(name = "user") String username){
		
		Korisnik owner = userRepo.findByUsername(username);
		if (owner==null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unknown user");
		
		List<Content> list = new ArrayList<Content>();
		List<SharedDocument> shared = new ArrayList<SharedDocument>();
		
		if (owner.getRole().equals(Korisnik.roleAdmin)){
			list = contentRepo.findAll();
		} else {
			//list = contentRepo.findByOwnerOrderByIdDesc(owner);
			shared = shareRepo.findByOwnerOrderByIdDesc(owner);
			for (SharedDocument x : shared) {
				list.add(contentRepo.findByIdDokumenta(x.getDocument().getId()));
			}
		}
		
		for (Content c : list)
			c.setContent(null);
		
		if (list.size()==0)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Jos uvijek niste dodali nijedan fajl");
		
				
		return ResponseEntity.ok(list);
	}
	
	@RequestMapping(value = "workspace", method = RequestMethod.DELETE)
	public ResponseEntity<Object> getWorkSpaceByUser(@RequestParam(name = "user") String username, @RequestParam(name = "document") int document_id){
	
		Korisnik owner = userRepo.findByUsername(username);
		if (owner==null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unknown user");
		}
			
		Content content = contentRepo.findOne(document_id);
		if (content == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unknown document");
		}
		
		contentRepo.delete(document_id);
		return ResponseEntity.ok(contentRepo.findOne(document_id)==null);
	}
	
	@RequestMapping(value = "workspace", method = RequestMethod.POST)
	public ResponseEntity<Object> getRenameDocument(@RequestParam(name = "user") String username, 
			@RequestParam(name = "document") int document_id,
			@RequestParam(name = "name") String document_name){
		
		Korisnik owner = userRepo.findByUsername(username);
		if (owner==null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unknown user");
		}
			
		Content content = contentRepo.findOne(document_id);
		if (content == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unknown document");
		}
		
		content.setFileName(document_name);
		contentRepo.save(content);
	
		return ResponseEntity.ok("Promjenili ste ime dokumenta");
	}
	
	@RequestMapping(value = "show", method = RequestMethod.GET)
	public ResponseEntity<Object> getContentById(@RequestParam(name = "id") int contentId){
		
		Content content = contentRepo.findByIdDokumenta(contentId);
		if ( content == null ){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unknown document");
		}
				
		return ResponseEntity.ok(content);
	}

}

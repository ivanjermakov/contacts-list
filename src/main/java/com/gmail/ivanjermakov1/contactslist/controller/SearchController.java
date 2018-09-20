package com.gmail.ivanjermakov1.contactslist.controller;

import com.gmail.ivanjermakov1.contactslist.entity.Contact;
import com.gmail.ivanjermakov1.contactslist.entity.Search;
import com.gmail.ivanjermakov1.contactslist.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Set;

@RestController
public class SearchController {
	
	private final SearchService searchService;
	
	@Autowired
	public SearchController(SearchService searchService) {
		this.searchService = searchService;
	}
	
	@RequestMapping("/search/init")
	@GetMapping
	public Search init() {
		return new Search();
	}
	
	@RequestMapping("/search/")
	@PostMapping
	public Set<Contact> search(@RequestBody Search search) throws SQLException {
		return searchService.search(search);
	}
	
}

package com.biaf.controller;

import org.python.core.PyByteArray;
import org.python.core.PyFunction;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.biaf.dto.SearchResultDto;
import com.biaf.service.SearchService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping(value="/ko")
public class SearchController {
	private final SearchService searchService;
	private static PythonInterpreter interpreter;

	@GetMapping(value="/search")
	public String search(@RequestParam("search") String searchs, Model model){
		interpreter = new PythonInterpreter();
		interpreter.execfile("src/main/resources/python/Search.py");
		
		PyFunction searchstrfunc = interpreter.get("searchstr", PyFunction.class);
		
		try {
			byte[] searchbytes = searchs.getBytes("utf-8");
			
			PyByteArray pystr = new PyByteArray(searchbytes);
			
			PyObject pyobj = searchstrfunc.__call__(pystr);
			
			SearchResultDto searchresultDto = searchService.searchf(pyobj);
			
			model.addAttribute("searchv", searchs);
			model.addAttribute("searchDto", searchresultDto);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/main/Search";
	}
	
}

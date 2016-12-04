package org.bpam_youth.controller;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.bpam_youth.entities.LeagueEntity;
import org.bpam_youth.repository.LeagueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/bpam/home")
public class LeagueController {
	
	@Autowired
	LeagueRepository leagueRepository;

	@RequestMapping(value="list", method=RequestMethod.GET)
	public String getHomeZipCodesList(Model model,
                                      @RequestParam(value = "jsonList") String jsonList) {
        //public String getGetHome(Model model) {
		//model.addAttribute("league", new LeagueEntity());
        //String list = jsonList;
        String testList = "{\n" +
                "    \"zip_codes\": [\n" +
                "        \"55107\",\n" +
                "        \"55119\",\n" +
                "        \"55133\",\n" +
                "        \"55144\",\n" +
                "        \"55145\",\n" +
                "        \"55146\",\n" +
                "        \"55164\",\n" +
                "        \"55165\",\n" +
                "        \"55166\",\n" +
                "        \"55168\",\n" +
                "        \"55170\",\n" +
                "        \"55171\",\n" +
                "        \"55172\",\n" +
                "        \"55175\",\n" +
                "        \"55188\",\n" +
                "        \"55169\",\n" +
                "        \"55101\",\n" +
                "        \"55155\",\n" +
                "        \"55106\",\n" +
                "        \"55130\"\n" +
                "    ]\n" +
                "}";

//        Type listType = new TypeToken<String>() {}.getType();
//        String yourList = new Gson().fromJson(jsonList, listType);
        List<String> items = Arrays.asList(jsonList.split("\\s*,\\s*"));


        List<LeagueEntity> leagues =
                leagueRepository.findAllByZipCode(items);

        System.out.println("get request: get");
		return "home";
	}

	@RequestMapping(method=RequestMethod.GET)
	public String getHome(Model model) {
		//model.addAttribute("league", new LeagueEntity());
        System.out.println("get request: post");
		return "home";
	}

	@RequestMapping(value="search/{zip}", method=RequestMethod.GET)
	public String searchLeague(Model model, @PathVariable String zip) {
		//model.addAttribute("league", new LeagueEntity());
		System.out.println("searching with zip "+zip);
		return "home";
	}

	@RequestMapping(value="/{leagueId}", method=RequestMethod.GET)
	public String getLeague(Model model, @PathVariable Long leagueId) {
		LeagueEntity league = leagueRepository.findById(leagueId);
		model.addAttribute("league", league);
		System.out.println("go to league page for id::- "+leagueId);
	return "league";
	}

}

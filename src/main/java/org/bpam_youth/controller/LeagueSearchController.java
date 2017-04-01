package org.bpam_youth.controller;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.bpam_youth.entities.LeagueEntity;
import org.bpam_youth.entities.LeagueSearchFormBean;
import org.bpam_youth.repository.LeagueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/search")
public class LeagueSearchController {
	
	@Autowired
	LeagueRepository leagueRepository;

	@RequestMapping(
            value = "list",
            method = RequestMethod.GET)
	public @ResponseBody List<LeagueEntity> getZipCodesList(
            Model model,
            HttpServletRequest request,
            //LeagueSearchFormBean formBean,
            @RequestParam(value = "jsonList") String jsonList) {

        List<String> zipCodeList = new ArrayList<String>(
                Arrays.asList(jsonList.split("\\s*,\\s*")));
        List leagues =
                leagueRepository.findByZipIsIn(zipCodeList);
        System.out.println("get request: get/list2");
		return leagues;
	}

	@RequestMapping(method=RequestMethod.GET)
	public String getHome(Model model) {
		//model.addAttribute("league", new LeagueEntity());
        System.out.println("get request: get");
		return "search";
	}

	@RequestMapping(value="/{leagueId}", method=RequestMethod.GET)
	public String getLeague(Model model, @PathVariable Long leagueId) {

		LeagueEntity league = leagueRepository.findById(leagueId);
		model.addAttribute("league", league);
		System.out.println("go to league page for id:: "+leagueId);
	return "league";
	}

    @ModelAttribute("leagues2")
         public List<LeagueEntity> getAllLeagues() {
        List<LeagueEntity> list = leagueRepository.findAllByOrderByNameAsc();
        return list;
    }
}

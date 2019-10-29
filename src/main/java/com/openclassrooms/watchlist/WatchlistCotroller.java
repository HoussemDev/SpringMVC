package com.openclassrooms.watchlist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WatchlistCotroller {

	private List<WatchlistItem> watchlistitems = new ArrayList<WatchlistItem>();
	private static int index = 1;
	
	@GetMapping("/watchlist")
	public ModelAndView getWatchlist() {
		watchlistitems.clear();
		watchlistitems.add(new WatchlistItem("Lion King", "8.5", "high", "Good comment",index++));
		watchlistitems.add(new WatchlistItem("Test 2", "10", "high2", "good comment 2", index++));
		watchlistitems.add(new WatchlistItem("Test 3", "10", "high3", "good comment 3", index++));
		watchlistitems.add(new WatchlistItem("Test 4", "10", "high4", "good comment 4", index++));

		String viewName ="watchlist";
		Map<String,Object> model = new HashMap<String, Object>();
		model.put("watchlistitems", watchlistitems);
		model.put("numberOfMovies", watchlistitems.size());
		return new ModelAndView(viewName, model);
		
	}
}

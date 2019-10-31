package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import domain.WatchlistItem;

@Controller
public class WatchlistCotroller {

	private List<WatchlistItem> watchlistitems = new ArrayList<WatchlistItem>();
	private static int index = 1;

	@GetMapping("/watchlistItemForm")
	public ModelAndView showWatchlistItemForm(@RequestParam(required = false) Integer id) {

		String viewName = "watchlistItemForm";

		Map<String,Object> model = new HashMap<String,Object>();

		WatchlistItem watchlistItem = findWatchListItemById(id);
		if(watchlistItem == null) {
			model.put("watchlistItem", new WatchlistItem());

		}else {
			model.put("watchlistItem", watchlistItem );
		}

		return new ModelAndView(viewName,model); 
	}

	private WatchlistItem findWatchListItemById(Integer id) {
		for (WatchlistItem watchlistItem : watchlistitems) {
			Integer a = watchlistItem.getId();
			if (a.equals(id)) {
				return watchlistItem;
			}
		}
		return null;
	}

	@PostMapping("/watchlistItemForm")
	public ModelAndView submitWatchListItemForm(@Valid WatchlistItem watchlistItem, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return new ModelAndView("watchlistItemForm");
		}
		WatchlistItem existingItem = findWatchListItemById(watchlistItem.getId());
		if(existingItem == null) {
			if(itemAleadyExists(watchlistItem.getTitle())) {
				bindingResult.rejectValue("title","", "This title already exists on your watchlist");
				return new ModelAndView("watchlistItemForm");

			}
			watchlistItem.setId(index++);
			watchlistitems.add(watchlistItem);
		}else {
			existingItem.setComment(watchlistItem.getComment());
			existingItem.setRating(watchlistItem.getRating());
			existingItem.setPriority(watchlistItem.getPriority());
			existingItem.setTitle(watchlistItem.getTitle());

			
			
		}
		
	
		RedirectView redirect = new RedirectView();
		redirect.setUrl("watchlist");
		return new ModelAndView(redirect);
	}

	@GetMapping("/watchlist")
	public ModelAndView getWatchlist() {


		String viewName ="watchlist";
		Map<String,Object> model = new HashMap<String, Object>();
		model.put("watchlistitems", watchlistitems);
		model.put("numberOfMovies", watchlistitems.size());
		return new ModelAndView(viewName, model);

	}
	
	private boolean itemAleadyExists(String title) {
		for (WatchlistItem watchlistItem : watchlistitems) {
			if(watchlistItem.getTitle().equals(title)) {
				return true;
			}
		}
		return false;

	
	}
}

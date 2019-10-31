package service;


import repository.WatchlistRepository;

import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import domain.WatchlistItem;
import exception.DuplicateTitleException;


public class WatchlistService {
	
	WatchlistRepository watchlistRepository = new WatchlistRepository();
	
	public List<WatchlistItem> getWatchlistItems(){
		return watchlistRepository.getList();
	}

	public int getWatchlistItemsSize(){
		return watchlistRepository.getList().size();
	}
	
	public WatchlistItem findWatchlistItembyID(Integer id){
		return watchlistRepository.findById(id);
	}
	
	public void AddOrUpdateWatchlistItem(WatchlistItem watchlistItem) throws DuplicateTitleException {
		
		
		WatchlistItem existingItem = findWatchlistItembyID(watchlistItem.getId());
		if(existingItem == null) {
			if(watchlistRepository.findByTitle(watchlistItem.getTitle())!=null) {
			throw new DuplicateTitleException();

			}
		
			watchlistRepository.addItem(watchlistItem);
		}else {
			existingItem.setComment(watchlistItem.getComment());
			existingItem.setRating(watchlistItem.getRating());
			existingItem.setPriority(watchlistItem.getPriority());
			existingItem.setTitle(watchlistItem.getTitle());
		
			
		}
	}
}

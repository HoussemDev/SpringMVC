package repository;

import java.util.ArrayList;
import java.util.List;

import domain.WatchlistItem;

public class WatchlistRepository {

	private List<WatchlistItem> watchlistitems = new ArrayList<WatchlistItem>();
	private static int index = 1;

	public List<WatchlistItem> getList(){
		return watchlistitems;
	}

	public void addItem(WatchlistItem watchlistItem) {
		watchlistItem.setId(index++);
		watchlistitems.add(watchlistItem);
	}

	public WatchlistItem findById(Integer id) {

		for (WatchlistItem watchlistItem : watchlistitems) {
			Integer a = watchlistItem.getId();
			if (a.equals(id)) {
				return watchlistItem;
			}
		}
		return null;

	}
	
	public WatchlistItem findByTitle(String title) {
		
		for (WatchlistItem watchlistItem : watchlistitems) {
			String a = watchlistItem.getTitle();
			if (a.equals(title)) {
				return watchlistItem;
			}
		}
		return null;
	}
	
}

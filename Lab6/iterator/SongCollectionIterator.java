package designpatterns.iterator;

import java.util.ArrayList;
import java.util.Iterator;

public class SongCollectionIterator implements Iterator<Song> {

	private ArrayList<Song> songs;
	private int indexKey;

	public SongCollectionIterator(ArrayList<Song> s) {
		this.songs = s;
		indexKey = 0;
	}

	@Override
	public boolean hasNext() {
		return this.indexKey < this.songs.size();
	}

	@Override
	public Song next() {
		Song r = this.songs.get(indexKey);
		indexKey++;
		return r;
		
		// the following one-liner also works
		//return this.songs.get(indexKey++);
	}
}

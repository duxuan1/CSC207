package designpatterns.iterator;

import java.util.ArrayList;
import java.util.Iterator;

public class SongCollection implements Iterable<Song> {

	ArrayList<Song> songs;

	public SongCollection() {
		songs = new ArrayList<Song>();
	}
	
	public void add(String name, String artist) {
		songs.add(new Song(name, artist));
	}

	@Override
	public Iterator<Song> iterator() {
		return new SongCollectionIterator(songs);
	}
}

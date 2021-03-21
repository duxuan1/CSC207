package designpatterns.iterator;

import java.util.Iterator;

public class SongCollectionMain {

	public static void main(String[] args) {

		SongCollection mySongs = new SongCollection();

		mySongs.add("Twinkle, Twinkle, Little Star", "Jane Taylor");
		mySongs.add("Old MacDonald Had a Farm", "Ronald McDonald");
		mySongs.add("Itsy Bitsy Spider", "Spiderman");
		mySongs.add("Wheels on the Bus", "Bus Driver");

		Iterator<Song> it = mySongs.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
		
		// the above is the same as:
		for (Song s : mySongs) {
			System.out.println(s);
		}
	}
}

package comparators;

import java.util.Comparator;

public class SoundtrackNameComparator implements Comparator<String> {
    public int compare(String soundtrackName1, String soundtrackName2) {
        if (soundtrackName1.length() > soundtrackName2.length()) return 1;
        else if (soundtrackName1 == soundtrackName2) return 0;
        else return -1;
    }
}

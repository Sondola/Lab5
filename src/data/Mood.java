package data;

import javax.xml.bind.annotation.XmlType;

@XmlType (name = "mood")
public enum Mood {
    SADNESS,
    SORROW,
    LONGING,
    CALM,
    RAGE,
    NULL;

    public static void printMood() {
        for (Mood m : Mood.values()) {
            if (m == NULL) break;
            System.out.println(m.toString());
        }
    }

    @Override
    public String toString() {
        switch(this) {
            case SADNESS:
                return "SADNESS";
            case SORROW:
                return "SORROW";
            case LONGING:
                return "LONGING";
            case CALM:
                return "CALM";
            case RAGE:
                return "RAGE";
            default:
                return "";
        }
    }
}

package example.cineclubtvhighlightsjson.functional;

import java.util.HashMap;
import java.util.Map;

public class HtmlHelper {
    private static Map<String,String> HTML_CHARACTERS = new HashMap<>();

    static{
        HTML_CHARACTERS.put( "&auml;", "ä" );
        HTML_CHARACTERS.put( "&ouml;", "ö" );
        HTML_CHARACTERS.put( "&uuml;", "ü" );
        HTML_CHARACTERS.put( "&Auml;", "Ä" );
        HTML_CHARACTERS.put( "&Ouml;", "Ö" );
        HTML_CHARACTERS.put( "&Uuml;", "Ü" );
        HTML_CHARACTERS.put( "&szlig;", "ß" );
        HTML_CHARACTERS.put( "&apos;", "’" );
    }

    public static String replaceHtmlCharacters( String s ){
        for (Map.Entry<String,String> entry : HTML_CHARACTERS.entrySet() ){

            String htmlCharacterSequence = entry.getKey();
            String character = entry.getValue();
            s = s.replace( htmlCharacterSequence, character );

        }
        return s;
    }
}

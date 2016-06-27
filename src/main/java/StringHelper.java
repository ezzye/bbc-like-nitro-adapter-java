/**
 * Created by errolelliott on 25/06/2016.
 */
public final class StringHelper {
    private static final String ABBREVIATE_SUFFIX = "...";

    public StringHelper() {
    }

    public static String abbreviate(String s, int maxLength){
        // if s is not null then return
        return s != null
                //if true then if length of string is longer than max length then
                && s.length() > maxLength?
                //true: then this
                    //if max length is less than or equal to 3 then
                    (maxLength <= "...".length()?
                            //true: truncate first max length characters of string
                            s.substring(0, maxLength):
                            //false: truncate but include three dots in max length string
                            s.substring(0, maxLength - "...".length()) + "..."):
                //false: don't change string
                s;
    }
}

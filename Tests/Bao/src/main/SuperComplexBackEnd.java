import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static java.util.Arrays.sort;

public class SuperComplexBackEnd {
    private ArrayList <String> emails=new ArrayList();
    private HashMap <String, HashMap <String, Integer>> tags=new HashMap();

    public void addAllEmails(String[] emails){ //Used for set up tests
        this.emails.addAll(Arrays.asList(emails));
    }

    public String addEmail(String email) {
        if (emailCheck(email).equals("OK")) {
            emails.add(email);
            return "OK";
        }
        return emailCheck(email);
    }

    public boolean containsEmail(String email) {
        return emails.contains(email);
    }

    public void addTag(String name, String tag) {
        if (!tags.containsKey(name))
            tags.put(name, new HashMap<>());
        if (tags.get(name).containsKey(tag))
            tags.get(name).put(tag, tags.get(name).get(tag) + 1);
        else
            tags.get(name).put(tag, 0);
    }

    public void setTagCount(String name, String tag, int count) {
        if (!tags.containsKey(name))
            tags.put(name, new HashMap<>());
        tags.get(name).put(tag, count);
    }

    public ArrayList <String> getTopThreeTags(String name) {
        if (!tags.containsKey(name))
            return null;
        int[] tmpArr=new int[tags.get(name).size()];
        int index=0;
        for (Integer i : tags.get(name).values()) {
            tmpArr[index++]=i;
        }
        sort(tmpArr);
        ArrayList <String> topThreeTags=new ArrayList();
        for (int i=index-1; i>=Math.max(index-3, 0); i--) {
            for (String s : tags.get(name).keySet()) {
                if (tags.get(name).get(s) == tmpArr[i] && !topThreeTags.contains(s)) {
                    topThreeTags.add(s);
                    break;
                }
            }
        }
        if (topThreeTags.isEmpty())
            return null;
        return topThreeTags;
    }

    public String emailCheck(String email) {
        if (email.endsWith("@setu.ie"))
        {
            if (!containsEmail(email))
                return "OK";
            return "Email already exists";
        }
        return "Email does not belong to SETU";
    }

    public void clearEmails() {
        emails.clear();
    }

    public void clearTags() {
        tags.clear();
    }
}

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetInfo {
    public GetInfo() {
    }

    public static String getHTML(String urlName) {
        ArrayList result = new ArrayList();

        String str;
        try {
            URLConnection resultString = (new URL(urlName)).openConnection();
            Scanner str1 = new Scanner(resultString.getInputStream());

            while(str1.hasNext()) {
                result.add(str1.next());
            }
        } catch (MalformedURLException var5) {
            System.out.println("Malformed Url: " + urlName);
            str = "error detected";
            return str;
        } catch (IOException var6) {
            var6.printStackTrace();
            str = "error detected";
            return str;
        }

        String resultString1 = "";

        String s;
        for(Iterator str2 = result.iterator(); str2.hasNext(); resultString1 = resultString1 + s + "\t") {
            s = (String)str2.next();
        }

        return resultString1;
    }

    public static Map<String, String> getUrls(String url) {
        TreeMap result = new TreeMap();
        String html = getHTML(url);
        String patt = "[A-Za-z0-9.]{0,2}\\.html\">\\s[A-Za-z0-9.\\s,\\(\\)\\-\'\\\']+<";
        Pattern pattern = Pattern.compile(patt);
        Matcher match = pattern.matcher(html);

        while(match.find()) {
            String str = match.group();
            str = str.replace("\t", "");
            str = str.replace(" ", "");
            str = str.replace(",", "");
            int i = str.indexOf("\"");
            String realUrl = "https://www.cia.gov/library/publications/the-world-factbook/geos/print_" + str.substring(0, i);
            result.put(str.substring(i + 2, str.length() - 1), realUrl);
        }

        return result;
    }

    public static Map<String, List<Country>> createCountries(Map<String, String> input) {
        TreeMap output = new TreeMap();
        Iterator var2 = input.keySet().iterator();

        while(var2.hasNext()) {
            String temp = (String)var2.next();
            String html2 = getHTML((String)input.get(temp));
            String patt2 = "printHeader\t[A-Za-z0-9._\\s>\"&\\(\\)\\-]+::";
            Pattern pattern2 = Pattern.compile(patt2);
            Matcher match2 = pattern2.matcher(html2);

            while(match2.find()) {
                String str = match2.group();
                str = str.replace("\t", "");
                str = str.replace(" ", "");
                int i = str.indexOf(">");
                String region = str.substring(i + 1, str.length() - 2);
                Country c = new Country(temp, region, (String)input.get(temp));
                if(output.containsKey(region)) {
                    List copy = (List)output.get(region);
                    copy.add(c);
                    output.put(region, copy);
                } else {
                    ArrayList copy1 = new ArrayList();
                    copy1.add(c);
                    output.put(region, copy1);
                }
            }
        }

        return output;
    }

    public static List<String> questionOne(String region, String keyword, Map<String, List<Country>> temp) {
        ArrayList output = new ArrayList();
        Iterator var4 = ((List)temp.get(region)).iterator();

        while(var4.hasNext()) {
            Country c = (Country)var4.next();
            String html = getHTML(c.getLinkName());
            Pattern pattern = Pattern.compile(keyword);
            Matcher match = pattern.matcher(html);
            if(match.find()) {
                output.add(c.getCountryName());
            }
        }

        return output;
    }

    public static List<String> questionTwo(String keyword, Map<String, List<Country>> temp) {
        ArrayList output = new ArrayList();
        Iterator var3 = temp.keySet().iterator();

        label35:
        while(var3.hasNext()) {
            String s = (String)var3.next();
            Iterator var5 = ((List)temp.get(s)).iterator();

            while(true) {
                while(true) {
                    Country c;
                    Matcher match;
                    do {
                        if(!var5.hasNext()) {
                            continue label35;
                        }

                        c = (Country)var5.next();
                        String html = getHTML(c.getLinkName());
                        String patt = "Flag\tdescription.*\\s" + keyword + "(s)?\\s.*National\tsymbol";
                        Pattern pattern = Pattern.compile(patt, 32);
                        match = pattern.matcher(html);
                    } while(!match.find());

                    String line2 = match.group();
                    int i = line2.indexOf("note:");
                    int j = line2.indexOf("star");
                    if(i != -1 && i > j) {
                        output.add(c.getCountryName());
                    } else if(i == -1) {
                        output.add(c.getCountryName());
                    }
                }
            }
        }

        Collections.sort(output);
        return output;
    }

    public static String questionThree(String region, Map<String, List<Country>> temp) {
        TreeMap populations = new TreeMap();
        Iterator var3 = ((List)temp.get(region)).iterator();

        while(var3.hasNext()) {
            Country c = (Country)var3.next();
            String html = getHTML(c.getLinkName());
            String patt = "Population:.*([0-9,]{0,3},)?([0-9,]{0,3},)?[0-9,]{0,3}\t\\([A-Za-z0-9.\t\\-\\s]+\\).*Nationality";
            Pattern pattern = Pattern.compile(patt, 32);
            Matcher match = pattern.matcher(html);
            if(match.find()) {
                String patt2 = "[0-9,]{5,13}";
                Pattern pattern2 = Pattern.compile(patt2, 32);
                Matcher match2 = pattern2.matcher(match.group());
                if(match2.find()) {
                    String pop = match2.group();
                    pop = pop.replace(",", "");
                    populations.put(Integer.valueOf(Integer.parseInt(pop)), c.getCountryName());
                }
            }
        }

        return (String)populations.get(populations.firstKey());
    }

    public static List<String> questionFour(String region, Map<String, List<Country>> temp) {
        ArrayList output = new ArrayList();
        Iterator var3 = ((List)temp.get(region)).iterator();

        while(var3.hasNext()) {
            Country c = (Country)var3.next();
            String html = getHTML(c.getLinkName());
            String patt = "Area:.*total:.*([0-9,.]+)\tsq\tkm.*land.*Area\t-\tcomparative";
            Pattern pattern = Pattern.compile(patt);
            Matcher match = pattern.matcher(html);
            if(match.find()) {
                String str = match.group();
                str = str.trim();
                str = str.replaceAll("\\_data>ca\\.", "_data>");
                str = str.replaceAll("\t", "");
                str = str.replaceAll("lessthan", "");
                Pattern patt2 = Pattern.compile("class=category_data>([0-9,]{0,3}(,|\\.))?([0-9,]{0,3}(,|\\.))?([0-9,]{0,3})", 32);
                Matcher matcher = patt2.matcher(str);
                if(matcher.find()) {
                    String area = matcher.group();
                    int i = area.indexOf("data");
                    String realArea = area.substring(i + 5);
                    realArea = realArea.replace(",", "");
                    double a = Double.parseDouble(realArea);
                    if(a < 119280.0D) {
                        output.add(c.getCountryName());
                    }
                }
            }
        }

        return output;
    }

    public static void questionFive(int number) {
        ArrayList output = new ArrayList();
        TreeMap years = new TreeMap();
        String html = getHTML("https://www.cia.gov/library/publications/the-world-factbook/appendix/appendix-b.html");
        String patt = "<SPAN\tclass=\"category\"\tstyle=\"padding:\t3px;\tdisplay:\tblock;\">[a-zA-Z\\(\\)\\.\\s\t\\-\\,\\/]+</SPAN>\t<DIV\tclass";
        Pattern pattern = Pattern.compile(patt);
        Matcher match = pattern.matcher(html);

        while(match.find()) {
            String count = match.group();
            int i = count.indexOf(">");
            int i1 = count.indexOf("</SPAN>");
            String organization = count.substring(i + 1, i1);
            int s = html.indexOf(count);
            int p = html.indexOf("aim</SPAN>", s);
            boolean hasDate = true;
            String line2 = html.substring(s, p);
            int checkIndex = line2.indexOf("<SPAN\tclass=\"category\"");
            int checkIndex2 = line2.lastIndexOf("<SPAN\tclass=\"category\"");
            if(checkIndex != checkIndex2) {
                hasDate = false;
            }

            if(hasDate) {
                String patt2 = "established</SPAN>\t-\t(\\d{1,2}\t)?([A-Za-z]+\t)?\\d{4}";
                Pattern pattern2 = Pattern.compile(patt2);
                Matcher match2 = pattern2.matcher(line2);
                String date = "";
                if(match2.find()) {
                    date = match2.group();
                    String realDate = date.substring(date.length() - 4, date.length());
                    Integer established = Integer.valueOf(Integer.parseInt(realDate));
                    ArrayList org;
                    if(years.containsKey(established)) {
                        org = (ArrayList)years.get(established);
                        org.add(organization);
                        years.put(established, org);
                    } else {
                        org = new ArrayList();
                        org.add(organization);
                        years.put(established, org);
                    }
                }

                output.add(organization);
            }
        }

        int var24 = 0;
        Iterator var25 = years.keySet().iterator();

        while(var25.hasNext()) {
            Integer var26 = (Integer)var25.next();
            Iterator var27 = ((ArrayList)years.get(var26)).iterator();

            while(var27.hasNext()) {
                String var28 = (String)var27.next();
                if(var24 < number) {
                    System.out.println("year founded: " + var26 + " : " + var28);
                    ++var24;
                }
            }
        }

    }

    public static void questionSix(Map<String, List<Country>> temp, int more, int less) {
        TreeMap lessThan = new TreeMap();
        TreeMap greaterThan = new TreeMap();
        Iterator var5 = temp.keySet().iterator();

        while(var5.hasNext()) {
            String s = (String)var5.next();
            Iterator var7 = ((List)temp.get(s)).iterator();

            while(var7.hasNext()) {
                Country c = (Country)var7.next();
                String html = getHTML(c.getLinkName());
                String patt = "Religions:(.*)";
                Pattern pattern = Pattern.compile(patt, 32);
                Matcher match = pattern.matcher(html);
                if(match.find()) {
                    String country = c.getCountryName();
                    String str = match.group();
                    int i1 = str.indexOf("</div>\t<div\tid=\'field\'");
                    String linetemp = str.substring(0, i1);
                    String patt2 = "(class=category_data>[A-Za-z0-9\\.\\,\\(\\)\\s\\t;\\-]+%)";
                    Pattern pattern2 = Pattern.compile(patt2, 32);
                    Matcher match2 = pattern2.matcher(linetemp);
                    if(match2.find()) {
                        String line = match2.group(1);
                        int j = line.indexOf("%");
                        int i = line.indexOf("data");
                        String line2 = line.substring(i + 5, j);
                        int q = line2.lastIndexOf("\t");
                        String religion = line2.substring(0, q);
                        String patt4 = "\\d{1,3}";
                        Pattern pattern3 = Pattern.compile(patt4, 32);
                        Matcher match3 = pattern3.matcher(line);
                        if(match3.find()) {
                            Double percent = Double.valueOf(Double.parseDouble(match3.group()));
                            if(percent.doubleValue() > (double)more) {
                                greaterThan.put(country, religion);
                            }

                            if(percent.doubleValue() < (double)less) {
                                lessThan.put(country, religion);
                            }
                        }
                    }
                }
            }
        }

        System.out.println("less than: " + less + "%");
        System.out.println(lessThan.keySet());
        System.out.println("more than: " + more + "%");
        System.out.println(greaterThan.keySet());
    }

    public static List<String> questionSeven(Map<String, List<Country>> temp) {
        ArrayList output = new ArrayList();
        Iterator var2 = temp.keySet().iterator();

        while(var2.hasNext()) {
            String s = (String)var2.next();
            Iterator var4 = ((List)temp.get(s)).iterator();

            while(var4.hasNext()) {
                Country c = (Country)var4.next();
                String html = getHTML(c.getLinkName());
                String patt = "0\tkm\t\\(landlocked\\)";
                Pattern pattern = Pattern.compile(patt);
                Matcher match = pattern.matcher(html);
                String patt2 = "border\tcountries\t\\(1\\):";
                Pattern pattern2 = Pattern.compile(patt2);
                Matcher match2 = pattern2.matcher(html);
                if(match.find() && match2.find()) {
                    output.add(c.getCountryName());
                }
            }
        }

        return output;
    }

    public static void questionEight(Map<String, List<Country>> temp) {
        TreeMap output = new TreeMap();
        Iterator x = temp.keySet().iterator();

        while(x.hasNext()) {
            String s = (String)x.next();
            Iterator var4 = ((List)temp.get(s)).iterator();

            while(var4.hasNext()) {
                Country c = (Country)var4.next();
                String html = getHTML(c.getLinkName());
                String patt = "Coastline:</a><a.*class=category_data>([0-9,]{1,7})\tkm.*Maritime\tclaims\'>";
                Pattern pattern = Pattern.compile(patt);
                Matcher match = pattern.matcher(html);
                if(match.find() && !c.getCountryName().equals("World")) {
                    String line = match.group(1);
                    line = line.replace(",", "");
                    Double i = Double.valueOf(Double.parseDouble(line));
                    output.put(i, c.getCountryName());
                }
            }
        }

        for(int var12 = 0; var12 < 10; ++var12) {
            System.out.println(output.pollLastEntry());
        }

    }

    public static void extraCreditOne(Map<String, List<Country>> temp) {
        TreeMap percapita = new TreeMap();
        Iterator x = temp.keySet().iterator();

        while(x.hasNext()) {
            String s = (String)x.next();
            Iterator var4 = ((List)temp.get(s)).iterator();

            while(var4.hasNext()) {
                Country c = (Country)var4.next();
                String country = c.getCountryName();
                String html = getHTML(c.getLinkName());
                String patt = "Population:.*([0-9,]{0,3},)?([0-9,]{0,3},)?[0-9,]{0,3}\t\\([A-Za-z0-9.\t\\.\\-\\s]+\\).*Nationality";
                Pattern pattern = Pattern.compile(patt, 32);
                Matcher match = pattern.matcher(html);
                String pattNew = "Electricity\t-\tconsumption:</a>.*class=category_data>([0-9A-Za-z\\.\\,\\-\\(\\)\\t\t\\s]+)</div>\t<div><span\tclass=\'.*Electricity\t-\texports\'>";
                Pattern patternNew = Pattern.compile(pattNew, 32);
                Matcher matchNew = patternNew.matcher(html);
                if(match.find() && matchNew.find()) {
                    String newLine = matchNew.group(1);
                    String patt2 = "[0-9,]{5,13}";
                    Pattern pattern2 = Pattern.compile(patt2, 32);
                    Matcher match2 = pattern2.matcher(match.group());
                    if(match2.find()) {
                        String pop = match2.group();
                        pop = pop.replace(",", "");
                        double realpop = Double.parseDouble(pop);
                        int num = newLine.indexOf("\t");
                        new Double(0.0D);
                        Double consumption;
                        if(newLine.substring(0, num).contains(",")) {
                            String st = newLine.substring(0, num);
                            st = st.replace(",", "");
                            consumption = Double.valueOf(Double.parseDouble(st));
                        } else {
                            consumption = Double.valueOf(Double.parseDouble(newLine.substring(0, num)));
                        }

                        if(newLine.contains("trillion")) {
                            consumption = Double.valueOf(consumption.doubleValue() / realpop * 1.0E9D);
                        } else if(newLine.contains("billion")) {
                            consumption = Double.valueOf(consumption.doubleValue() / realpop * 1000000.0D);
                        } else if(newLine.contains("million")) {
                            consumption = Double.valueOf(consumption.doubleValue() / realpop * 1000.0D);
                        } else {
                            consumption = Double.valueOf(consumption.doubleValue() / realpop * 1.0D);
                        }

                        percapita.put(consumption, country);
                    }
                }
            }
        }

        for(int var24 = 0; var24 < 5; ++var24) {
            System.out.println(percapita.pollLastEntry());
        }

    }

    public static void answerQuestions(int questionNum, Map<String, List<Country>> regionToCountry) {
        if(questionNum == 1) {
            System.out.println(questionOne("SouthAmerica", "earthquakes", regionToCountry));
        }

        if(questionNum == 2) {
            System.out.println(questionTwo("star", regionToCountry));
        }

        if(questionNum == 3) {
            System.out.println(questionThree("Europe", regionToCountry));
        }

        if(questionNum == 4) {
            System.out.println(questionFour("MiddleEast", regionToCountry));
            System.out.println("still finding other countries...");
            System.out.println(questionFour("SouthAsia", regionToCountry));
            System.out.println("still finding other countries...");
            System.out.println(questionFour("CentralAsia", regionToCountry));
            System.out.println("still finding other countries...");
            System.out.println(questionFour("East&SoutheastAsia", regionToCountry));
        }

        if(questionNum == 5) {
            questionFive(10);
        }

        if(questionNum == 6) {
            questionSix(regionToCountry, 80, 50);
        }

        if(questionNum == 7) {
            System.out.println(questionSeven(regionToCountry));
        }

        if(questionNum == 8) {
            questionEight(regionToCountry);
        }

        if(questionNum == 9) {
            System.out.println("Extra credit # Takes particularly longer to compute...");
            extraCreditOne(regionToCountry);
        }

    }

    public static void main(String[] args) {
        System.out.println("hello user, please wait for approx. 40 seconds as the program loads data...");
        String temp = "https://www.cia.gov/library/publications/the-world-factbook/index.html";
        Map regions = createCountries(getUrls(temp));
        Iterator done = regions.keySet().iterator();

        while(done.hasNext()) {
            String reader = (String)done.next();
            System.out.println(reader);
        }

        boolean done1 = false;
        Scanner reader1 = new Scanner(System.in);

        while(!done1) {
            System.out.println("Which question would you like to answer? (please enter an integer between 1 and 9 where 9 corresponds to Extra Credit #1");
            int n = reader1.nextInt();
            System.out.println("Getting info....can take up to 35 seconds");
            answerQuestions(n, regions);
            System.out.println("Are you done? Please enter yes or no");
            String s = reader1.next();
            if(s.equals("yes")) {
                done1 = true;
                reader1.close();
                System.out.println("Have a good day");
            }
        }

        System.out.println(getHTML("https://www.cia.gov/library/publications/the-world-factbook/geos/print_av.html"));
    }
}

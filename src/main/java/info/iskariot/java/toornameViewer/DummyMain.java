package info.iskariot.java.toornameViewer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DummyMain
{

    public static void main(String[] args) throws Exception
    {
        Document doc = Jsoup
                .connect(
                        "https://www.toornament.com/tournaments/2339085216478707712/stages/2424922775599816704/groups/2431454887988600834/?locale=en_GB"
                )
                .get();
        Elements rankingList = doc.select(".ranking");
        Elements entries = rankingList.select(".ranking-container");
        //System.out.println(entries.first().toString());
        HashMap<String, Team> teams = new HashMap<>();
        for (Element o : entries)
        {
            Team t = new Team();
            t.name = o.select(".name").first().text();
            t.rank = Integer.parseInt(o.select(".rank").first().text());
            t.points = Integer.parseInt(o.select(".points").first().text());
            teams.put(t.name, t);
        }
        ArrayList<Match> matches = new ArrayList<>();
        for (Element o : entries)
        {
            for (Element mm : o.child(1).child(0).children())
            {
                Match m = new Match();
                m.url = mm.select("a").first().attr("href");
                if (!matches.contains(m))
                {
                    m.points = new int[mm.select(".opponent").size()];
                    for (Element opp : mm.select(".opponent"))
                    {
                        Elements nameTag = opp.select(".name");
                        Team t = teams.get(nameTag.first().text());
                        m.participants.add(t);
                        if (nameTag.hasClass("win"))
                        {
                            m.winners.add(t);
                        }
                        if (nameTag.hasClass("loss"))
                        {
                            m.loosers.add(t);
                        }
                        if (opp.select(".result").size() > 0)
                        {
                            m.points[m.participants.indexOf(t)] = Integer.parseInt(opp.select(".result").first().text());
                        }
                    }
                    matches.add(m);
                }
            }
        }
        for (Match m : matches)
        {
            for (Team t : m.participants)
            {
                t.matches.add(m);
            }
            System.out.println(m.url);
            System.out.println("Participants: " + m.participants.toString());
            System.out.println("Points: " + Arrays.toString(m.points));
            System.out.println("Winners: " + m.winners.toString());
            System.out.println();
        }
        ArrayList<Team> tt = new ArrayList<>(teams.values());
        Collections.sort(tt, (a, b) -> Integer.valueOf(a.rank).compareTo(Integer.valueOf(b.rank)));
        String spaces = "                                        ";
        for (Team t : tt)
        {
            System.out.print(t.rank + "\t" + t.points + "\t" + t.name + spaces.substring(t.name.length()) + "\t");
            for (Match m : t.matches)
            {
                System.out.print(Arrays.toString(m.points));
            }
            System.out.println();
        }
    }
}

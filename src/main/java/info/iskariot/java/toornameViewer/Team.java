package info.iskariot.java.toornameViewer;

import java.util.ArrayList;
import java.util.List;

public class Team
{
    public List<Match> matches = new ArrayList<>();
    public String      name;
    public String      points;
    public String      rank;

    @Override
    public String toString()
    {
        return name;
    }
}

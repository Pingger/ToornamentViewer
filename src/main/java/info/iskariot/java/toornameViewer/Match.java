package info.iskariot.java.toornameViewer;

import java.util.ArrayList;
import java.util.List;

public class Match
{
    public List<Team> loosers      = new ArrayList();
    public List<Team> participants = new ArrayList();
    public int[]      points       = new int[0];
    public String     url;
    public List<Team> winners      = new ArrayList();

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        { return true; }
        if (obj == null)
        { return false; }
        if (getClass() != obj.getClass())
        { return false; }
        Match other = (Match) obj;
        if (url == null)
        {
            if (other.url != null)
            { return false; }
        }
        else if (!url.equals(other.url))
        { return false; }
        return true;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + (url == null ? 0 : url.hashCode());
        return result;
    }
}

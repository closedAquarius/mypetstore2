package csu.web.mypetstore.domain;

public class Journal
{
    private String description;
    private String date;
    private String color;

    public void setDescription(String description)
    {
        this.description = description;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public void setColor(String color)
    {
        this.color = color;
    }

    public String getDescription()
    {
        return description;
    }

    public String getDate()
    {
        return date;
    }

    public String getColor()
    {
        return color;
    }
}

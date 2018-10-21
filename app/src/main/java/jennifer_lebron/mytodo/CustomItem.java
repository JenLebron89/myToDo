package jennifer_lebron.mytodo;

//	simple	class	that	contains	a	name	and	a	date
//	imports
import java.text.SimpleDateFormat;
import java.util.Date;
//	class	definition
public class CustomItem	{
    //	constructor	for	the	class
    CustomItem(String	name,	long time)	{
        //	take a copy	of the	name and convert the time into a simple	date format string
        this.name =	name;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date	d =	new Date(time);
        date =	sdf.format(d);
    }
    //	getter	methods	for	both	fields
    public String	getName()	{	return name;	}
    public String	getDate()	{	return date;	}
    //	private	fields	of	the	class
    private String	name;
    private String	date;
}
package jennifer_lebron.mytodo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
public class AddItem extends Activity	{
    //	private	fields	of	the	class
    private String taskId;
    private TextView	tv;
    private Button	btn_add2;
    @Override
    protected void onCreate(Bundle	savedInstanceState)	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        //	get	the	intent	that	started	this	activity	and	extract	the	count	from
//	it
        Intent	intent =	getIntent();
        taskId =	intent.getStringExtra("id");
//	grab	the	textview from	the	layout	and	update	the	text	to	show	the	new
//	count	value
        tv =	(TextView)	findViewById(R.id.tv_add);
        tv.setText("You're in Add");
        //tv.setText("this	activity	has	been	started	" +	count +	"	times");
//	get	the	button	and	attach	a	listener	that	will	update	the	counter	and
//	will	dismiss	this	activity
        btn_add2 =	(Button)	findViewById(R.id.btn_add_to_list);

    }

    public void clickButtonBack(View v){
        // v.getId() and then if r.id whatever = whatever
        Intent	intent =	new Intent(AddItem.this,	MainActivity.class);
//        String id = (((TextView)findViewById(R.id.et_new_strings)).getText().toString());
//        //pass string to intent
//        intent.putExtra("id", id);
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu	menu)	{
//	Inflate	the	menu;	this	adds	items	to	the	action	bar	if	it	is
//	present.
        getMenuInflater().inflate(R.menu.main,	menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem	item)	{
//	Handle	action	bar	item	clicks	here.	The	action	bar	will
//	automatically	handle	clicks	on	the	Home/Up	button,	so	long
//	as	you	specify	a	parent	activity	in	AndroidManifest.xml.
        int id =	item.getItemId();
        if (id ==	R.id.action_settings)	{
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

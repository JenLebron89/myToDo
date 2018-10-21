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
public class EditActivity	extends Activity	{
    //	private	fields	of	the	class
    private String taskId;
    private TextView	tv;
    private Button	btn_accept;
    @Override
    protected void onCreate(Bundle	savedInstanceState)	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        //	get	the	intent	that	started	this	activity	and	extract	the	count	from
//	it
        Intent	intent =	getIntent();
        taskId =	intent.getStringExtra("id");
//	grab	the	textview from	the	layout	and	update	the	text	to	show	the	new
//	count	value
        tv =	(TextView)	findViewById(R.id.tv);
        tv.setText("The ID of the task clicked is: " + taskId);
        //tv.setText("this	activity	has	been	started	" +	count +	"	times");
//	get	the	button	and	attach	a	listener	that	will	update	the	counter	and
//	will	dismiss	this	activity
        btn_accept =	(Button)	findViewById(R.id.btn_accept);
        btn_accept.setOnClickListener(new OnClickListener()	{
            //	overridden	on	click	method	to	return	a	result	to	the	starter	of	this
//	activity
            public void onClick(View	v)	{
                Intent	result =	new Intent(Intent.ACTION_VIEW);
                //String id = (((TextView)findViewById(R.id.tv_display)).getText().toString());
                //pass string to intent
                //result.putExtra("id", id);
                //startActivity(result);
                //setResult(RESULT_OK,	result);
                finish();
            }
        });
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

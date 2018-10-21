package jennifer_lebron.mytodo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
public class AddItem extends Activity	{
    //	private	fields	of	the	class
    private String taskId;
    private TextView	tv;
    private Button	btn_add2;
    private EditText et_add;
    CustomItem addedTask;
    String testTask;
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
        //tv.setText("You're in Add");
        //tv.setText("this	activity	has	been	started	" +	count +	"	times");
        et_add = (EditText) findViewById(R.id.et_add);
        btn_add2 =	(Button)	findViewById(R.id.btn_add_to_list);

        //	add	in	a	listener	for	the	edit	text	to	create	new	items	in	our	list	view
        et_add.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView view, int actionid, KeyEvent event) {
                //	if	the	user	is	done	entering	in	a	new	string	then	add	it	to
                //	the	array	list.	this	then	notifies	the	adapter	that	the	data	has
                //	changed	and	that	the	list	view	needs	to	be	updated
                if (actionid == EditorInfo.IME_ACTION_DONE) {
                    addedTask = new CustomItem(et_add.getText().toString(),
                            System.currentTimeMillis());
                    testTask = et_add.getText().toString();
                    tv.setText(testTask);
                    //caa.notifyDataSetChanged();
                    //tv_display.setText(et_new_strings.getText() + " added to list.");
                    return true;
                }
                //	if	we	get	to	this	point	then	the	event	has	not	been	handled	thus
                //	return	false
                return false;
            }
        });



    }



    public void clickButtonBack(View v){
        // v.getId() and then if r.id whatever = whatever
        Intent	intent =	new Intent(AddItem.this,	MainActivity.class);
//        String id = (((TextView)findViewById(R.id.et_new_strings)).getText().toString());
//        //pass string to intent
//        intent.putExtra("id", id);
        intent.putExtra("addedTask", testTask);
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

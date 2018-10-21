package jennifer_lebron.mytodo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //	private	fields	of	the	class
    private TextView tv_display;
    private ListView lv_mainlist;
    private EditText et_new_strings;
    private ArrayList<CustomItem> al_items;
    private CustomArrayAdapter caa;
    //	get	the	button	from	the	layout	and	add	a	listener	to	it
    private Button btn_edit;
    private Button btn_add;
    private String taskAdded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //	pull	the	list	view	and	the	edit	text	from	the	xml
        tv_display = (TextView) findViewById(R.id.tv_display);
        lv_mainlist = (ListView) findViewById(R.id.lv_mainlist);
        //et_new_strings = (EditText) findViewById(R.id.et_new_strings);
        //	generate	an	array	list	with	some	simple	strings
        al_items = new ArrayList<CustomItem>();
        //	create	an	array	adapter	for	al_strings	and	set	it	on	the	listview
        caa = new CustomArrayAdapter(this, al_items);
        lv_mainlist.setAdapter(caa);

        btn_edit = (Button) findViewById(R.id.bt_edit);
        btn_add = (Button) findViewById(R.id.bt_add);


//        //	add	in	a	listener	for	the	edit	text	to	create	new	items	in	our	list	view
//        et_new_strings.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            public boolean onEditorAction(TextView view, int actionid, KeyEvent event) {
//                //	if	the	user	is	done	entering	in	a	new	string	then	add	it	to
//                //	the	array	list.	this	then	notifies	the	adapter	that	the	data	has
//                //	changed	and	that	the	list	view	needs	to	be	updated
//                if (actionid == EditorInfo.IME_ACTION_DONE) {
//                    al_items.add(new CustomItem(et_new_strings.getText().toString(),
//                            System.currentTimeMillis()));
//                    caa.notifyDataSetChanged();
//                    tv_display.setText(et_new_strings.getText() + " added to list.");
//                    return true;
//                }
//                //	if	we	get	to	this	point	then	the	event	has	not	been	handled	thus
//                //	return	false
//                return false;
//            }
//        });



        //	add	in	a	listener	that	listens	for	short	clicks	on	our	list	items
        lv_mainlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //	overridden	method	that	we	must	implement	to	get	access	to	short	clicks
            public void onItemClick(AdapterView<?> adapterview, View view, int pos, long id) {
                //	update	the	text	view	to	state	that	a	short	click	was	made	here
                tv_display.setText("item	" + pos + "	selected");
            }
        });
        //	add	in	a	listener	that	listens	for	long	clicks	on	our	list	items
        lv_mainlist.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            //	overridden	method	that	we	must	implement	to	get	access	to	long	clicks
            public boolean onItemLongClick(AdapterView<?> adapterview, View view, int pos, long id) {
                //	update	the	display	with	what	we	have	just	long	clicked
                tv_display.setText("item	" + pos + "	long	clicked");
                //	as	we	are	going	to	change	the	textview anyway	we	can	automatically
                //	return	true;
                return true;
            }
        });

//        btn_edit.setOnClickListener(new View.OnClickListener()	{
//            //	overridden	method	to	handle	a	button	click
//            public void onClick(View	v)	{
//                Intent	intent =	new Intent(MainActivity.this,	EditActivity.class);
//                startActivity(intent);
//            }
//        });
    }
    public void clickButton(View v){
        // v.getId() and then if r.id whatever = whatever
        Intent	intent =	new Intent(MainActivity.this,	EditActivity.class);
//        String id = (((TextView)findViewById(R.id.et_new_strings)).getText().toString());
//        //pass string to intent
//        intent.putExtra("id", id);
        startActivity(intent);
    }

    public void clickButtonAdd(View v){
        Intent	intent =	new Intent(MainActivity.this,	AddItem.class);
//        String id = (((TextView)findViewById(R.id.et_new_strings)).getText().toString());
//        //pass string to intent
//        intent.putExtra("id", id);
        startActivity(intent);

    }

    public void addToList(View v){
        taskAdded = "New task added successfully";
        al_items.add(new CustomItem("New Task",
                System.currentTimeMillis()));
        caa.notifyDataSetChanged();
        tv_display.setText(taskAdded);

    }
}

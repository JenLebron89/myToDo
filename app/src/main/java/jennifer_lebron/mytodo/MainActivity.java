package jennifer_lebron.mytodo;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
    //buttons
    private Button btn_edit;
    private Button btn_add;
    private String taskAdded;

    //	database fields
    private TestDBOpenHelper	tdb;
    private SQLiteDatabase sdb;


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

        Intent	intent =	getIntent();
        taskAdded =	intent.getStringExtra("addedTask");
        addToList();

        //	get	access	to	an	sqlite database
        tdb =	new TestDBOpenHelper(this,	"taskTable.db",	null,	1);
        sdb =	tdb.getWritableDatabase();

        //	add	in a few rows to our database
        ContentValues cv =	new ContentValues();

        cv.put("TASK_NAME",	"do assignments");
        cv.put("TASK_DATE",	"now");

        sdb.insert("taskTable",null, cv);

        cv.put("TASK_NAME",	"have baby");
        cv.put("TASK_DATE",	"soon");

        sdb.insert("taskTable",null, cv);

        cv.put("TASK_NAME",	"eat tacos");
        cv.put("TASK_DATE",	"right away");

        sdb.insert("taskTable",null, cv);
//        cv.put("FIRST_NAME",	"jane");
//        cv.put("LAST_NAME",	"doe");
//        sdb.insert("test",	null,	cv);
//        cv.put("FIRST_NAME",	"jim");
//        cv.put("LAST_NAME",	"doe");
//        sdb.insert("test",	null,	cv);

        // name of the table to query
        String	table_name =	"taskTable";
        // the	columns	that we	wish to	retrieve from the tables
        String[] columns = {"TASK_NAME", "TASK_DATE"};

        //	where	clause	of	the	query.	DO	NOT	WRITE	WHERE	IN	THIS
        String	where =	null;
        //	arguments	to	provide	to	the	where	clause
        String	where_args[]	=	null;
        //	group	by	clause	of	the	query.	DO	NOT	WRITE	GROUP	BY	IN	THIS
        String	group_by =	null;
        //	having	clause	of	the	query.	DO	NOT	WRITE	HAVING	IN	THIS
        String	having =	null;
        //	order	by	clause	of	the	query.	DO	NOT	WRITE	ORDER	BY	IN	THIS
        String	order_by =	null;
        //	run	the	query.	this	will	give	us	a	cursor	into	the	database
        //	that	will	enable	us	to	change	the	table	row	that	we	are	working	with
        Cursor c =	sdb.query(table_name,	columns,	where,	where_args,	group_by,
                having,	order_by);

        //method to query database and populate arraylist
        /*

        // loop through db

        c.moveToFirst(); //start at beginning of db
        for(int i =	0; i <	c.getCount(); i++) {
           String taskname1 =  c.getString(0); // get task name (pkey) and make it customItem (Task)
           al_items.add(new CustomItem(taskname1,
                System.currentTimeMillis()));
           //caa.notifyDataSetChanged();
            c.moveToNext();
        }


        */



        //	print	out	some	data	from	the	cursor	to	the	screen
        TextView	tv =	(TextView)	findViewById(R.id.tv);
        String	total_text =	"total number of rows: " + c.getCount()	+ "\n";
        c.moveToFirst();
        for(int i =	0; i <	c.getCount(); i++) {
            total_text += c.getString(0) + " " +
                    c.getString(1)	+ "\n";
            c.moveToNext();
        }
        tv.setText(total_text);


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

    public void addToList(){
        tv_display.setText("Task added.");
        al_items.add(new CustomItem(taskAdded,
                System.currentTimeMillis()));
        caa.notifyDataSetChanged();

    }

    //	overridden	method	that	will	clear	out	the	contents	of	the	database
    protected void onDestroy() {
        super.onDestroy();
//	run	a	query	that	will	delete	all	the	rows	in	our	database
        String table = "taskTable";
        String where = null;
        String where_args[] = null;
        sdb.delete(table, where, where_args);
    }
}

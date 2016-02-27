package android.com.autocompletetextview;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.MultiAutoCompleteTextView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.SearchView.OnQueryTextListener;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    AutoCompleteTextView text;
    MultiAutoCompleteTextView text1;
    Button btn_search,btn_submit;
    DatabaseHandler dbhandler;
    String[] languages={"Fever","Typhoid","Dengue","Malaria","Hiv aids","Gas","Headache","Cough","Cold","Body Pains"};
    EditText disease_id,disease_name,disease_drug,disease_symptom;
    LinearLayout DiseaseDescription;
    Cursor cursor;
    TextView tv_disease_id,tv_disease_name,tv_disease_drug,tv_disease_symptom;
    String diseaseId,diseaseName,diseaseDrug,diseaseSymptom;
    String Did,Dname,Ddrug,Dsymptom;
    String autoComplteText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        text=(AutoCompleteTextView)findViewById(R.id.autoCompleteTextView1);
        btn_search=(Button)findViewById(R.id.btn_search);
        tv_disease_id=(TextView)findViewById(R.id.tv_diseaseId);
        tv_disease_name=(TextView)findViewById(R.id.tv_diseaseName);
        tv_disease_drug=(TextView)findViewById(R.id.tv_diseaseDrug);
        tv_disease_symptom=(TextView)findViewById(R.id.tv_diseaseSymptoms);

        DiseaseDescription=(LinearLayout)findViewById(R.id.diseaseDescription);
        btn_submit=(Button)findViewById(R.id.btn_submit);

        dbhandler=new DatabaseHandler(this);

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,languages);

        text.setAdapter(adapter);
        text.setThreshold(1);
        text.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                autoComplteText=(String)parent.getItemAtPosition(position);
            }
        });

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                insert();


              /*  disease_name.setText(autoComplteText);
                disease_name.setTextColor(Color.BLACK);
                tv_disease_id.setText("Disease Id is:" + Did);
                disease_id.setVisibility(View.GONE);
                disease_id.setTextColor(Color.BLACK);
                tv_disease_name.setText("Disease Name is:" + Dname);
                disease_name.setVisibility(View.GONE);
                disease_name.setTextColor(Color.BLACK);
                tv_disease_drug.setText("Disease Drug is:" + Ddrug);
                disease_drug.setVisibility(View.GONE);
                disease_drug.setTextColor(Color.BLACK);
                tv_disease_symptom.setText("Disease Symptoms is:" + Dsymptom);
                disease_symptom.setVisibility(View.GONE);
                disease_symptom.setTextColor(Color.BLACK);*/


               // btn_submit.setVisibility(View.GONE);
            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });

    }

    public void insert()
    {

        /*diseaseName=disease_name.getText().toString();
        diseaseDrug=disease_drug.getText().toString();
        diseaseSymptom=disease_symptom.getText().toString();*/

        if(dbhandler.getDiseaseInfoCount()==0){
            dbhandler.addDiseaseInfo(new DiseaseInfo("1", "Fever", "fever drug", "fever Symptoms"));
            dbhandler.addDiseaseInfo(new DiseaseInfo("2", "Headache", "headache drug", "headache symptoms"));
            dbhandler.addDiseaseInfo(new DiseaseInfo("3", "Hiv aids", "hiv aids drug", "hiv aids symptoms"));
            dbhandler.addDiseaseInfo(new DiseaseInfo("4", "Cold", "cold drug", "cold symptoms"));
            dbhandler.addDiseaseInfo(new DiseaseInfo("5", "Cough", "cough drug", "cough symptoms"));


        }

        DiseaseDescription.setVisibility(View.VISIBLE);
        DiseaseInfo c=dbhandler.getDiseaseInfo(autoComplteText);

     /*   List<DiseaseInfo> contacts = dbhandler.getAllContacts();

        for (DiseaseInfo cn : contacts) {
            String log = "Id: "+cn.getId()+" ,Name: " + cn.getName() + " ,drug: " + cn.getDrug()+" ,symptoms"+cn.getSymptom();
            // Writing Contacts to log
            Log.d("Name: ", log);
        }
        // Toast.makeText(getApplicationContext(),contact.getId()+"\n"+contact.getName(),Toast.LENGTH_LONG).show();
*/


        if(c!=null) {

            tv_disease_id.setText("Disease Id is:  " + c.getId());
            tv_disease_name.setText("Disease Name is:" + c.getName());
            tv_disease_drug.setText("Disease Drug is:" + c.getDrug());
            tv_disease_symptom.setText("Disease Symptoms is:" + c.getSymptom());
            btn_submit.setVisibility(View.GONE);
        }else{
            Toast.makeText(getApplicationContext(),"Enter record is not available",Toast.LENGTH_LONG).show();
            DiseaseDescription.setVisibility(View.GONE);

        }

    }
}

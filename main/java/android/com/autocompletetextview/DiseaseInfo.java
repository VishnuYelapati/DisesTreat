package android.com.autocompletetextview;

/**
 * Created by srinu on 2/18/2016.
 */
public class DiseaseInfo {

    String name,drug,symptom,id;
    public DiseaseInfo()
    {

    }

    public DiseaseInfo(String name,String drug,String symptom)
    {
      this.name=name;
      this.drug=drug;
      this.symptom=symptom;
    }
    public DiseaseInfo(String id,String name,String drug,String symptom) {
        // TODO Auto-generated constructor stub
        this.id=id;
        this.name=name;
        this.drug=drug;
        this.symptom=symptom;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDrug() {
        return drug;
    }

    public void setDrug(String drug) {
        this.drug = drug;
    }

    public String getSymptom() {
        return drug;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }
}

import java.util.ArrayList;

public class ClusterEntry {
    private int age;
    private String str_age;
    private String education;
    private String maritalStatus;
    private String race;
    private int size;
    private ArrayList<Integer> ids;

    private ArrayList<Integer> sensitive_attributes;

    private int[] sids;

    ClusterEntry(Person p) {
        this.age = p.getAge();
        this.str_age = p.getStr_age();
        this.education = p.getEducation();
        this.maritalStatus = p.getMaritalStatus();
        this.race = p.getRace();
        this.size = 1;
        this.ids = new ArrayList();
        this.ids.add(p.getId());
        this.sids = new int[9];
        this.sids[p.getSid()]++;
        this.sensitive_attributes = new ArrayList();
        this.sensitive_attributes.add(p.getOccupationInt());
    }

    public void add(Person p) {
        ++this.size;
        this.ids.add(p.getId());
        sids[p.getSid()]++;
        if (!sensitive_attributes.contains(p.getOccupationInt())) sensitive_attributes.add(p.getOccupationInt());
//        sensitive_attributes[p.getOccupationInt()]++;
    }

    public int getAge() {
        return this.age;
    }

    public String getStr_age() {
        return this.str_age;
    }

    public String getEducation() {
        return this.education;
    }

    public String getMaritalStatus() {
        return this.maritalStatus;
    }

    public String getRace() {
        return this.race;
    }

    public int getSize() {
        return this.size;
    }

    public ArrayList<Integer> getIds() {
        return this.ids;
    }

    public int[] getSids() { return this.sids; }

    public ArrayList<Integer> getSensitive_attributes() {
        return sensitive_attributes;
    }

}


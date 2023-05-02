//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

public class Person {
    private int id;
    private int sid;
    private String str_age;
    private int age;
    private String workClass;
    private int fnlwgt;
    private String education;
    private int educationNum;
    private String maritalStatus;
    private String occupation;
    private String relationship;
    private String race;
    private String sex;
    private int capital_gain;
    private int capital_loss;
    private int hours_per_week;
    private String native_country;

    public Person(String var1, int var2) {
        String[] var3 = var1.split(",");
        if (var3.length != 15) {
            System.out.println("Error reading person");
        }

        this.id = var2;
        this.str_age = var3[0].trim();
        this.age = Integer.parseInt(var3[0].trim());
        this.workClass = var3[1].trim();
        this.fnlwgt = Integer.parseInt(var3[2].trim());
        this.education = var3[3].trim();
        this.educationNum = Integer.parseInt(var3[4].trim());
        this.maritalStatus = var3[5].trim();
        this.occupation = var3[6].trim();
        this.relationship = var3[7].trim();
        this.race = var3[8].trim();
        this.sex = var3[9].trim();
        this.capital_gain = Integer.parseInt(var3[10].trim());
        this.capital_loss = Integer.parseInt(var3[11].trim());
        this.hours_per_week = Integer.parseInt(var3[12].trim());
        this.native_country = var3[13].trim();
        this.sid = toSid(this.occupation);
    }

    // convert the sensitive attribute of occupation to an SID for alpha algorithm
    private int toSid(String occupation) {
        if (occupation.equals("Transport-moving") ||
                occupation.equals("Priv-house-serv") ||
                occupation.equals("Protective-serv") ||
                occupation.equals("Armed-Forces")) {
            return 0;
        } else if (occupation.equals("Tech-support") ||
                occupation.equals("Craft-repair") ||
                occupation.equals("Other-service")) {
            return 1;
        } else if (occupation.equals("Handlers-cleaners") ||
                occupation.equals("Machine-op-inspct")) {
            return 2;
        } else if (occupation.equals("Sales")) {
            return 3;
        } else if (occupation.equals("Exec-managerial")) {
            return 4;
        } else if (occupation.equals("Prof-specialty")) {
            return 5;
        } else if (occupation.equals("Adm-clerical")) {
            return 6;
        } else if (occupation.equals("Farming-fishing")) {
            return 7;
        } else return 8;
    }

    public boolean matches(ClusterEntry var1) {
        return this.str_age.equals(var1.getStr_age()) && this.education.equals(var1.getEducation()) && this.maritalStatus.equals(var1.getMaritalStatus()) && this.race.equals(var1.getRace());
    }

    public String toString() {
        return String.format("%s, %s, %s, %s, %d, %s, %s, %s, %s, %s, %s, %s, %s, %s", this.str_age, this.workClass, this.fnlwgt, this.education, this.educationNum, this.maritalStatus, this.occupation, this.relationship, this.race, this.sex, this.capital_gain, this.capital_loss, this.hours_per_week, this.native_country);
    }

    public int getSid() { return this.sid; }

    public int getId() {
        return this.id;
    }

    public String getStr_age() {
        return this.str_age;
    }

    public void setStr_age(String var1) {
        this.str_age = var1;
    }

    public int getAge() {
        return this.age;
    }

    public String getEducation() {
        return this.education;
    }

    public void setEducation(String var1) {
        this.education = var1;
    }

    public int getEducationNum() {
        return this.educationNum;
    }

    public void setEducationNum(int var1) {
        this.educationNum = var1;
    }

    public String getMaritalStatus() {
        return this.maritalStatus;
    }

    public void setMaritalStatus(String var1) {
        this.maritalStatus = var1;
    }

    public String getRace() {
        return this.race;
    }

    public void setRace(String var1) {
        this.race = var1;
    }

    public String getOccupation() {
        return this.occupation;
    }

    public int getOccupationInt() {
        if (this.occupation.equals("Tech-support")) return 0;
        else if (this.occupation.equals("Craft-repair")) return 1;
        else if (this.occupation.equals("Other-service")) return 2;
        else if (this.occupation.equals("Sales")) return 3;
        else if (this.occupation.equals("Exec-managerial")) return 4;
        else if (this.occupation.equals("Prof-specialty")) return 5;
        else if (this.occupation.equals("Handlers-cleaners")) return 6;
        else if (this.occupation.equals("Machine-op-inspct")) return 7;
        else if (this.occupation.equals("Adm-clerical")) return 8;
        else if (this.occupation.equals("Farming-fishing")) return 9;
        else if (this.occupation.equals("Transport-moving")) return 10;
        else if (this.occupation.equals("Priv-house-serv")) return 11;
        else if (this.occupation.equals("Protective-serv")) return 12;
        else if (this.occupation.equals("Armed-Forces")) return 13;
        else return 14;
    }
//
//    public void setId(int var1) {
//        this.id = var1;
//    }
//
//    public void setAge(int var1) {
//        this.age = var1;
//    }
//
//    public String getWorkClass() {
//        return this.workClass;
//    }
//
//    public void setWorkClass(String var1) {
//        this.workClass = var1;
//    }
//
//    public int getFnlwgt() {
//        return this.fnlwgt;
//    }
//
//    public void setFnlwgt(int var1) {
//        this.fnlwgt = var1;
//    }
//
//    public void setOccupation(String var1) {
//        this.occupation = var1;
//    }
//
//    public String getRelationship() {
//        return this.relationship;
//    }
//
//    public void setRelationship(String var1) {
//        this.relationship = var1;
//    }
//
//    public String getSex() {
//        return this.sex;
//    }
//
//    public void setSex(String var1) {
//        this.sex = var1;
//    }
//
//    public int getCapital_gain() {
//        return this.capital_gain;
//    }
//
//    public void setCapital_gain(int var1) {
//        this.capital_gain = var1;
//    }
//
//    public int getCapital_loss() {
//        return this.capital_loss;
//    }
//
//    public void setCapital_loss(int var1) {
//        this.capital_loss = var1;
//    }
//
//    public int getHours_per_week() {
//        return this.hours_per_week;
//    }
//
//    public void setHours_per_week(int var1) {
//        this.hours_per_week = var1;
//    }
//
//    public String getNative_country() {
//        return this.native_country;
//    }
//
//    public void setNative_country(String var1) {
//        this.native_country = var1;
//    }
}


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Main {
    public Main() {
    }

    private static int actualK;
    private static int actualL;
    private static double actualAlpha;

    public static ArrayList<Person> generalizeAge(ArrayList<Person> people, int degree) {
        for (Person p : people) {
            int age = p.getAge();
            if (degree == 0) {
                if (age >= 17 && age < 27) {
                    p.setStr_age("[17,27)");
                } else if (age >= 27 && age < 36) {
                    p.setStr_age("[27,36)");
                } else if (age >= 36 && age < 45) {
                    p.setStr_age("[36,45)");
                } else if (age >= 45 && age < 54) {
                    p.setStr_age("[45,54)");
                } else if (age >= 54 && age < 63) {
                    p.setStr_age("[54,63)");
                } else if (age >= 63 && age < 72) {
                    p.setStr_age("[63,72)");
                } else if (age >= 72 && age < 81) {
                    p.setStr_age("[72,81)");
                } else {
                    p.setStr_age("[81,90]");
                }
            } else if (degree == 1) {
                if (age >= 17 && age < 36) {
                    p.setStr_age("[17,36)");
                } else if (age >= 36 && age < 54) {
                    p.setStr_age("[36,54)");
                } else if (age >= 54 && age < 72) {
                    p.setStr_age("[54,72)");
                } else {
                    p.setStr_age("[72,99]");
                }
            } else if (degree == 2) {
                if (age >= 17 && age < 54) {
                    p.setStr_age("[17,54)");
                } else {
                    p.setStr_age("[54,90]");
                }
            } else {
                p.setStr_age("*");
            }
        }
        return people;
    }

    public static ArrayList<Person> generalizeEducation(ArrayList<Person> people, int degree) {
        for (Person p : people) {
                    int educationNum = p.getEducationNum();
                    if (degree == 0) {
                        if (educationNum >= 1 && educationNum < 3) {
                            p.setEducation("[Preschool,4th]");
                        } else if (educationNum >= 3 && educationNum < 5) {
                            p.setEducation("[5th,8th]");
                        } else if (educationNum >= 5 && educationNum < 7) {
                            p.setEducation("[9th,10th]");
                        } else if (educationNum >= 7 && educationNum < 9) {
                            p.setEducation("[11th,12th]");
                        } else if (educationNum >= 9 && educationNum < 11) {
                            p.setEducation("[HS-Grad,Some-college]");
                        } else if (educationNum >= 11 && educationNum < 13) {
                            p.setEducation("[Assoc-voc,Assoc-acdm]");
                        } else if (educationNum >= 13 && educationNum < 15) {
                            p.setEducation("[Bachelors,Masters]");
                        } else {
                            p.setEducation("[Prof-school,Doctorate]");
                        }
                    } else if (degree == 1) {
                        if (educationNum >= 1 && educationNum < 5) {
                            p.setEducation("[Preschool,8th]");
                        } else if (educationNum >= 5 && educationNum < 9) {
                            p.setEducation("[9th,12th]");
                        } else if (educationNum >= 9 && educationNum < 13) {
                            p.setEducation("[HS-Grad,Assoc-acdm]");
                        } else {
                            p.setEducation("[Bachelors,Doctorate]");
                        }
                    } else if (degree == 2) {
                        if (educationNum >= 1 && educationNum < 9) {
                            p.setEducation("[Preschool,12th]");
                        } else {
                            p.setEducation("[HS-Grad,Doctorate]");
                        }
                    } else {
                        p.setEducation("*");
                    }
                }
                return people;
            }

    public static ArrayList<Person> generalizeMaritalStatus(ArrayList<Person> people, int degree) {
        for (Person p : people) {
                    String maritalStatus = p.getMaritalStatus();
                    if (degree == 0) {
                        if (!maritalStatus.equals("Married-civ-spouse") && !maritalStatus.equals("Married-spouse-absent") && !maritalStatus.equals("Married-AF-spouse")) {
                            p.setMaritalStatus("Not Married");
                        } else {
                            p.setMaritalStatus("Married");
                        }
                    } else {
                        p.setMaritalStatus("*");
                    }
                }
                return people;
    }

    public static ArrayList<Person> generalizeRace(ArrayList<Person> people, int degree) {
        for (Person p : people) {
            p.setRace("*");
        }
        return people;
    }

    public static ArrayList<Person> anonymize(ArrayList<Person> people, int k, int l, double alpha, int suppressionBudget) {
        int ageGeneralization = 0;
        int educationGeneralization = 0;
        int maritalStatusGeneralization = 0;
        int raceGeneralization = 0;
        ArrayList<ClusterEntry> clusters = new ArrayList<>();

        while(ageGeneralization < 4 || educationGeneralization < 4 || maritalStatusGeneralization < 2 || raceGeneralization < 1) {
            clusters = new ArrayList<>();
            clusters.add(new ClusterEntry(people.get(0)));

            for(int i = 0; i < people.size(); ++i) {
                Person p = people.get(i);
                if (p.getId() != 0) {
                    boolean found = false;

                    for(int j = 0; j < clusters.size(); ++j) {
                        ClusterEntry c = clusters.get(j);
                        if (p.matches(c)) {
                            c.add(p);
                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        clusters.add(new ClusterEntry(p));
                    }
                }
            }

            if (checkK(people, k, suppressionBudget) + checkL(people, l, suppressionBudget) + checkA(people, alpha, suppressionBudget) <= suppressionBudget) {
                break;
            }

            ArrayList<String> uniqueAge = new ArrayList<>();
            ArrayList<String> uniqueEducation = new ArrayList<>();
            ArrayList<String> uniqueMaritalStatus = new ArrayList<>();
            ArrayList<String> uniqueRace = new ArrayList<>();

            for (Person p : people) {
                if (!uniqueAge.contains(p.getStr_age())) uniqueAge.add(p.getStr_age());
                if (!uniqueEducation.contains(p.getEducation())) uniqueEducation.add(p.getEducation());
                if (!uniqueMaritalStatus.contains(p.getMaritalStatus())) uniqueMaritalStatus.add(p.getMaritalStatus());
                if (!uniqueRace.contains(p.getRace())) uniqueRace.add(p.getRace());
            }

            int a = ageGeneralization == 4 ? 0 : uniqueAge.size();
            int b = educationGeneralization == 4 ? 0 : uniqueEducation.size();
            int c = maritalStatusGeneralization == 2 ? 0 : uniqueMaritalStatus.size();
            int d = raceGeneralization == 1 ? 0 : uniqueRace.size();
            int maxDistortion = Math.max(a, Math.max(b, Math.max(c, d)));
            if (maxDistortion == 0) return people;

            if (maxDistortion == uniqueAge.size() && ageGeneralization < 4) {
                people = generalizeAge(people, ageGeneralization);
                ++ageGeneralization;
            } else if (maxDistortion == uniqueEducation.size() && educationGeneralization < 4) {
                people = generalizeEducation(people, educationGeneralization);
                ++educationGeneralization;
            } else if (maxDistortion == uniqueMaritalStatus.size() && maritalStatusGeneralization < 2) {
                people = generalizeMaritalStatus(people, maritalStatusGeneralization);
                ++maritalStatusGeneralization;
            } else if (maxDistortion == uniqueRace.size() && raceGeneralization < 1) {
                people = generalizeRace(people, raceGeneralization);
                ++raceGeneralization;
            }
        }

        System.out.println("Generalization of Age: " + ageGeneralization + " out of 4");
        System.out.println("Generalization of Education: " + educationGeneralization + " out of 4");
        System.out.println("Generalization of Marital Status: " + maritalStatusGeneralization + " out of 2");
        System.out.println("Generalization of Race: " + raceGeneralization + " out of 1");
        System.out.println("Initial size of data set: " + people.size());

        ArrayList<Person> toRemove = new ArrayList<>();
        for (ClusterEntry ce : clusters) {
            if (ce.getSize() < k) {
                for (int i : ce.getIds()) {
                    toRemove.add(people.get(i));
                }
            }
            if (ce.getSensitive_attributes().size() < l) {
                for (int i : ce.getIds()) {
                    if (!toRemove.contains(people.get(i))) toRemove.add(people.get(i));
                }
            }

        }
        people.removeAll(toRemove);
        System.out.println("Resulting size of data set: " + people.size());
        return people;
    }

    /**
     * if total people in clusters which do not follow k anonymity is less than our suppression budget,then those people
     *  can just be suppressed to achieve desired k anonymity
     * @param people
     * @param k
     * @param suppressionBudget
     * @return
     */
    public static int checkK(ArrayList<Person> people, int k, int suppressionBudget) {
        ArrayList<ClusterEntry> clusters = new ArrayList<>();
        clusters.add(new ClusterEntry(people.get(0)));
        // separate people into clusters
        for (int i = 0; i < people.size(); i++) {
            Person p = people.get(i);
            if (p.getId() == 0) continue;
            boolean found = false;
            for (int j = 0; j < clusters.size(); j++) {
                ClusterEntry c = clusters.get(j);
                if (p.matches(c)) {
                    c.add(p);
                    found = true;
                    break;
                }
            }
            if (!found) clusters.add(new ClusterEntry(p));
        }
        int minK = clusters.get(0).getSize();
        // number of people who fall outside of a k-anonymized cluster
        // these people can be suppressed iff we have less than suppressionBudget
        int countBelow = 0;
        for (ClusterEntry ce : clusters) {
            if (ce.getSize() < minK) minK = ce.getSize();
            if (ce.getSize() < k) countBelow += ce.getSize();
        }
        actualK = minK;
        return countBelow;
//        return minK >= k || countBelow <= suppressionBudget;
    }

    public static int checkL(ArrayList<Person> people, int l, int suppressionBudget) {
        ArrayList<ClusterEntry> clusters = new ArrayList<>();
        clusters.add(new ClusterEntry(people.get(0)));
        // separate people into clusters
        for (int i = 0; i < people.size(); i++) {
            Person p = people.get(i);
            if (p.getId() == 0) continue;
            boolean found = false;
            for (int j = 0; j < clusters.size(); j++) {
                ClusterEntry c = clusters.get(j);
                if (p.matches(c)) {
                    c.add(p);
                    found = true;
                    break;
                }
            }
            if (!found) clusters.add(new ClusterEntry(p));
        }
        int minL = 9999999;
        int countBelow = 0;
        for (ClusterEntry ce : clusters) {
            if (ce.getSensitive_attributes().size() < minL) {
                minL = ce.getSensitive_attributes().size();
            }
            if (ce.getSensitive_attributes().size() < l) countBelow += ce.getSize();
        }
        actualL = minL;
        return countBelow;
    }

    public static int checkA(ArrayList<Person> people, double a, int suppressionBudget) {
        ArrayList<ClusterEntry> clusters = new ArrayList<>();
        clusters.add(new ClusterEntry(people.get(0)));
        // separate people into clusters
        for (int i = 0; i < people.size(); i++) {
            Person p = people.get(i);
            if (p.getId() == 0) continue;
            boolean found = false;
            for (int j = 0; j < clusters.size(); j++) {
                ClusterEntry c = clusters.get(j);
                if (p.matches(c)) {
                    c.add(p);
                    found = true;
                    break;
                }
            }
            if (!found) clusters.add(new ClusterEntry(p));
        }
        double maxA = -1;
        int count_in_violation = 0;
        for (ClusterEntry ce : clusters) {
            for (int i : ce.getSids()) {
                double curr_a = i / (double) ce.getSize();
                if (curr_a > maxA) maxA = curr_a;
                if (curr_a > a) count_in_violation += ce.getSize();
            }
        }
        actualAlpha = maxA;
        return count_in_violation;
    }

    public static void main(String[] var0) {
        int k = 5;
        int l = 8;
        double alpha = .4;
        ArrayList result = new ArrayList();

        // read data
        try {
            BufferedReader var2 = new BufferedReader(new FileReader("adult.data"));
            Throwable var3 = null;

            try {
                String var5;
                try {
                    for(int var4 = 0; (var5 = var2.readLine()) != null && !var5.equals(""); ++var4) {
                        Person var6 = new Person(var5, var4);
                        result.add(var6);
                    }
                } catch (Throwable var19) {
                    var3 = var19;
                    throw var19;
                }
            } finally {
                if (var2 != null) {
                    if (var3 != null) {
                        try {
                            var2.close();
                        } catch (Throwable var17) {
                            var3.addSuppressed(var17);
                        }
                    } else {
                        var2.close();
                    }
                }

            }
        } catch (Exception var21) {
            System.out.println("Error reading data");
        }

        // generalize data
        result = anonymize(result, k, l, alpha, 159);
        if (checkK(result, k, 0) > 0) {
            System.out.println("K ANONYMITY NOT SUFFICIENT");
        } else System.out.println("K = " + actualK);
        if (checkL(result, l, 0) > 0) {
            System.out.println("L DIVERSITY NOT SUFFICIENT");
        } else System.out.println("L = " + actualL);
        if (checkA(result, alpha, 0) > 0) {
            System.out.println("ALPHA NOT SUFFICIENT");
        } else System.out.println("Alpha = " + actualAlpha);

        // write data
        try {
            File var22 = new File("out.data");
            if (var22.createNewFile()) {
                System.out.println("File created: " + var22.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException var16) {
            System.out.println("Error creating file.");
            var16.printStackTrace();
        }

        try {
            FileWriter var23 = new FileWriter("out.data");
            Iterator var24 = result.iterator();

            while(var24.hasNext()) {
                Person var25 = (Person)var24.next();
                var23.write(var25.toString() + "\n");
            }

            var23.close();
            System.out.println("Wrote output data to out.data");
        } catch (IOException var18) {
            System.out.println("Error writing to file");
            var18.printStackTrace();
        }

    }
}


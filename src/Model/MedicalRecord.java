package Model;

/**
 * Created by Sajeewa on 5/15/2017.
 */
public class MedicalRecord {

    private String caseID;
    private String caseDate;
    private String patientID;
    private String doctorID;
    private String caseType;
    private String comments;

    public MedicalRecord(String caseID, String caseDate, String patientID, String doctorID, String caseType, String comments){
        this.setCaseID(caseID);
        this.setCaseDate(caseDate);
        this.setPatientID(patientID);
        this.setDoctorID(doctorID);
        this.setCaseType(caseType);
        this.setComments(comments);
    }


    public String getCaseID() {
        return caseID;
    }

    public void setCaseID(String caseID) {
        this.caseID = caseID;
    }

    public String getCaseDate() {
        return caseDate;
    }

    public void setCaseDate(String caseDate) {
        this.caseDate = caseDate;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        if (caseType.equals("V")){
            this.caseType = "General Vaccination";
        } else {
            this.caseType = "Sickness";
        }
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}

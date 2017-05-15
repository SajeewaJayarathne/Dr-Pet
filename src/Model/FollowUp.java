package Model;

/**
 * Created by Sajeewa on 5/15/2017.
 */
public class FollowUp {
    private String caseID;
    private String reason;
    private String followUpDate;
    private String description;
    private boolean isActive;

    public FollowUp(String caseID, String reason, String followUpDate, String description, boolean isActive){
        this.setCaseID(caseID);
        this.setReason(reason);
        this.setFollowUpDate(followUpDate);
        this.setDescription(description);
        this.setActive(isActive);
    }

    public String getCaseID() {
        return caseID;
    }

    public void setCaseID(String caseID) {
        this.caseID = caseID;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getFollowUpDate() {
        return followUpDate;
    }

    public void setFollowUpDate(String followUpDate) {
        this.followUpDate = followUpDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}

package smartsite;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Minh_Khang
 */
public class Visitor extends Person {

    private String visitPurpose;
    private String hostName;
    private int accessLevel;

    public Visitor() {
    }

    public Visitor(String id, String fullName, String code, String role, String status,
            String visitPurpose, String hostName) {
        super(id, fullName, code, role, status);
        this.visitPurpose = visitPurpose;
        this.hostName = hostName;
    }

    public Visitor(String id, String fullName, String code, String role, String status) {
        this(id, fullName, code, role, status, "#", "#");
    }

    public Visitor(String visitPurpose, String hostName) {
        this.visitPurpose = visitPurpose;
        this.hostName = hostName;
    }

    public String getVisitPurpose() {
        return visitPurpose;
    }

    public void setVisitPurpose(String visitPurpose) {
        this.visitPurpose = visitPurpose;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public int getAccessLevel() {
        return accessLevel;
    }

    public final void setAccessLevel(int accessLevel) {
        if (accessLevel < 0 || accessLevel > 2) {
            throw new IllegalArgumentException("Access level must be between 0 and 2!");
        }
        this.accessLevel = accessLevel;
    }

    @Override
    public String getRoleDescription() {
        return "Site Manager oversees site operations, manages zone access, and approves safety logs (Access Level: " + accessLevel + ").";
    }

    @Override
    public String toFileLine() {
        return super.toFileLine() + "|" + visitPurpose + "|" + hostName;
    }

    @Override
    public String toString() {
        return super.toString() + " - Purpose: " + visitPurpose + " - Host: " + hostName;
    }
}

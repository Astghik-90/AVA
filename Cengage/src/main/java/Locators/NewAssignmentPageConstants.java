package Locators;

public class NewAssignmentPageConstants {

    //Step1: Assignment information
    public static final String title = "[data-qa-id='create-edit-assignment_title_input']";
    public static final String continueCreateButton = "[type='submit']";

    //Step 2: Content selection
    public static final String addCourseButton = "[data-qa-id='button']";
    public static final String groupDDMenuItems = "[data-qa-id*='assignment_group-chooser_dropdown']";
    public static final String expandCourseUnitsButtons = "[data-qa-id*='dashboard_expand-assignment']";
    public static final String courseTitleInDDMenu = "[>p]";
    public static final String selectedCourses = "group-title"; //classname
    public static final String selectUnitToggles = "ios-checkbox"; //ID


    //Step 3: Student selection
    public static final String allStudentsCheckbox = "all-students"; //ID
    public static final String selectStudentCheckboxes = "[data-qa-id*='assignment_student']";

}

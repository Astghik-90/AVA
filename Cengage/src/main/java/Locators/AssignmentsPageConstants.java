package Locators;

public class AssignmentsPageConstants {
    //new assignment
    public static final String newAssignmentButton = "[data-qa-id ='course-assignments_new-assignment_link']";

    //toggle
    public static final String filterAll = "//div[@class='filter']/div/div[1][1]";
    public static final String filterActive = "//div[@class='filter']/div/div[2]";
    public static final String filterExpired = "//div[@class='filter']/div/div[3]";

    //course
    public static final String courseDDMenu = "dropdown-container"; //ID
    public static final String courseList = "[data-qa-id*='assignments_group-chooser_dropdown_']";

    //sorting
    public static final String assignmentName = "[for='name']";
    public static final String courses = "[for='groupsName']";
    public static final String endDate = "[for='dueDate']";
    public static final String manualGrading = "[for='unmarkedCount']";

    //assignment rows
    public static final String dataRowListPerPage = "[data-row='true']";

    //pagination
    public static final String availablePageList = "[aria-label*=Page]";
    public static final String perPageSelector = "[data-qa-id='per-page-selector']";
    public static final String perPageSelectorOptions = "[data-qa-id='per-page-selector_dropdown_item']";





}
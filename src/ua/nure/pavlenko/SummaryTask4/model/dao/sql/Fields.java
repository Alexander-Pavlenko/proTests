package ua.nure.pavlenko.SummaryTask4.model.dao.sql;

/**
 * Created by Alexander on 20.05.2017.
 */
public final class Fields {
    ///////////////////SUBJECT TABLE//////////////
    public static final String ENTITY_ID = "id";
    public static final String SUBJECT_NAME = "name";
    public static final String SUBJECT_ICON = "icon";
/////////////////////////////////////////////////////
    //////////////////////USER TABLE/////////////////
    public static final String USER_LOGIN = "login";
    public static final String USER_PASSWORD = "password";
    public static final String USER_FIRST_NAME = "first_name";
    public static final String USER_LAST_NAME = "last_name";
    public static final String USER_NUMBER = "number";
    public static final String USER_EMAIL = "e_mail";
    public static final String USER_PHOTO = "photo";
    public static final String USER_DATE_REGISTRATION = "date";
    public static final String USER_ROLE = "role";
    public static final String USER_IS_BLOCK = "isBlocked";
    public static final String USER_IS_ACTIVATED = "isActivated";
    //////////////////////////////////////////////////////////
    ///////////////////////Test Table////////////////////////
    public static final String TEST_NAME = "name";
    public static final String TEST_SUBJECT_ID = "subject_id";
    public static final String TEST_TEST_TIME = "test_time";
    public static final String TEST_DESCRIPTION = "description";
    public static final String TEST_TYPE = "type";
    public static final String TEST_ICON = "icon";
    public static final String IS_DELETE = "isDelete";

    ///////////////////////////////////////////////////////
    ///////////////////////Answer Table///////////////////
    public static final String QUESTION_ID = "question_id";
    public static final String ANSWER = "answer";
    public static final String TRUTHFUL = "truthful";

    //////////////////////////////////////////////////////
    ///////////////////////Test Table/////////////////////
    public static final String TEST_ID = "test_id";
    public static final String QUESTION = "question";
    public static final String QUESION_CODE = "code";
}

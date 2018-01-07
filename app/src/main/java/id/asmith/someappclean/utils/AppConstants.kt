package id.asmith.someappclean.utils


/**
 * Created by Agus Adhi Sumitro on 27/12/2017.
 * https://asmith.my.id
 * aasumitro@gmail.com
 */
object AppConstants {

    //Pattern
    val EMAIL_PATTERN = ("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
    val USERNAME_PATTERN = ("^[a-z0-9_-]{3,15}\$")
    val PHONE_PATTERN = ("^[+(00)][0-9]{6,14}$")

    //Prefs
    val USER_LOG_STATUS = "isUserStillLogin"

    //Local data db
    val DB_NAME = "QueueApp"
    val DB_VERSION = 1
    val DB_TABLE = "user"
    val KEY_ID = "id"
    val KEY_USER_ID = "uid"
    val KEY_USER_NAME ="name"
    val KEY_USER_EMAIL ="email"
    val KEY_USER_PHONE ="phone"
    val KEY_USER_TOKEN = "token"
    val KEY_CREATED = "created_at"
    val KEY_UPDATED ="updated_at"

    //Remote data url
    private val BASE_URL = "http://192.168.43.70/project/"
    private val NAME_AND_VERSION = "rest-project/api/v1/"
    val API_URL = BASE_URL + NAME_AND_VERSION



}
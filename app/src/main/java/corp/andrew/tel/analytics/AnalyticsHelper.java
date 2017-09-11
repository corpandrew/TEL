package corp.andrew.tel.analytics;

import com.flurry.android.FlurryAgent;

import java.util.Map;

/**
 * Helps with logging custom events and errors to Flurry
 */
public class AnalyticsHelper {

    //MainActivityData
    public static final String EVENT_LIST_ITEM = "list_item_selected";
    public static final String EVENT_IMAGE_LIST_ITEM = "list_item_image_selected";
    public static final String EVENT_FAVORITE_LIST_ITEM = "list_item_favorite";
    public static final String EVENT_SEARCH = "search_selected";
    public static final String EVENT_SYNC = "sync_selected";
    public static final String EVENT_NAV_BAR_ITEM = "nav_bar_item_selected";

    //SolutionActivityData
    public static final String EVENT_SOLUTION_IMAGE = "solution_image_selected";
    public static final String EVENT_SOLUTION_FAVORITE = "solution_favorite";
    public static final String EVENT_TEL_SITE = "solution_tel_site";
    public static final String EVENT_EMAIL = "solution_email";
    public static final String EVENT_PRODUCT_SITE = "solution_product_site";

    public static final String PARAM_LOCATION = "location_request_param";


    /**
     * Logs an event for analytics.
     *
     * @param eventName   name of the event
     * @param eventParams event parameters (can be null)
     * @param timed       <code>true</code> if the event should be timed, false otherwise
     */
    public static void logEvent(String eventName, Map<String, String> eventParams, boolean timed) {
        FlurryAgent.logEvent(eventName, eventParams, timed);
    }

    /**
     * Ends a timed event that was previously started.
     *
     * @param eventName   name of the event
     * @param eventParams event parameters (can be null)
     */
    public static void endTimedEvent(String eventName, Map<String, String> eventParams) {
        FlurryAgent.endTimedEvent(eventName, eventParams);
    }


    /**
     * Ends a timed event without event parameters.
     *
     * @param eventName name of the event
     */
    public static void endTimedEvent(String eventName) {
        FlurryAgent.endTimedEvent(eventName);
    }

    /**
     * Logs an error.
     *
     * @param errorId          error ID
     * @param errorDescription error description
     * @param throwable        a {@link Throwable} that describes the error
     */
    public static void logError(String errorId, String errorDescription, Throwable throwable) {
        FlurryAgent.onError(errorId, errorDescription, throwable);
    }

    /**
     * Logs location.
     *
     * @param latitude  latitude of location
     * @param longitude longitude of location
     */
    public static void logLocation(double latitude, double longitude) {
        FlurryAgent.setLocation((float) latitude, (float) longitude);
    }

    /**
     * Logs page view counts.
     */
    public static void logPageViews() {
        FlurryAgent.onPageView();
    }

}
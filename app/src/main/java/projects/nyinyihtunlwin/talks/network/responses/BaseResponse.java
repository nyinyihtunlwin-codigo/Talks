package projects.nyinyihtunlwin.talks.network.responses;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Dell on 1/25/2018.
 */

public abstract class BaseResponse {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("apiVersion")
    private String apiVersion;

    @SerializedName("page")
    private int page;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public int getPage() {
        return page;
    }
}

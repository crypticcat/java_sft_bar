package sft.bar.mantis.appmanager;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import sft.bar.mantis.model.Issue;

import java.io.IOException;

public class RestHelper {
    private ApplicationManager app;
    private RestHelper rest;

    public RestHelper(ApplicationManager app) {
        this.app = app;
    }

    public String getIssueState(int id) throws IOException {
        String json = getExecutor().execute(Request.Get(String.format(app.getProperty("rest.url") + "/issues/%s.json", id)))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement state = parsed.getAsJsonObject().get("state_name");
        return new Gson().fromJson(state, new TypeToken<String>() {}.getType());
    }

    private Executor getExecutor() {
        return Executor.newInstance().auth(app.getProperty("rest.key"), "");
    }
}

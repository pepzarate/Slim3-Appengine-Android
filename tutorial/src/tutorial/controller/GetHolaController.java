    package tutorial.controller;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.util.List;

import org.slim3.datastore.Datastore;

import com.google.gson.Gson;

import tutorial.meta.TweetMeta;
import tutorial.model.Tweet;

public class GetHolaController extends Controller {

    private static final String ENCODING = "UTF-8";
    private static final String CONTENT_TYPE_JSON =
        "application/json; charset=" + ENCODING;

    @Override
    public Navigation run() throws Exception {

        try {
            // validate

            // validateCommon();

            // Map<String,Object> map = execute();
            /*
             * String result = null; String ext = getExtension();
             * if(ext.equals(TYPE_XML)){
             * response.setContentType(CONTENT_TYPE_XML); result =
             * getXmlResult(0, map); }else if(ext.equals(TYPE_JSONP)){ String
             * callback = request.getParameter(PARAM_CALLBACK);
             * response.setContentType(CONTENT_TYPE_JSON); result =
             * getJsonpResult(0, map, callback); }else{
             * response.setContentType(CONTENT_TYPE_JSON); result =
             * getJsonResult(0, map); }
             */
            response.setContentType(CONTENT_TYPE_JSON);
            // result = getXmlResult(0, map);
            // Gson myGson = new Gson();
            TweetMeta t = new TweetMeta();

            List<Tweet> myList =
                Datastore.query(t).sort(t.createdDate.desc).asList();
            Gson gson = new Gson();

            String json = gson.toJson(myList);

            PrintWriter writer =
                new PrintWriter(new OutputStreamWriter(
                    response.getOutputStream(),
                    ENCODING));

            try {
                writer.print(json);
            } finally {
                writer.close();
            }

        } catch (Exception e) {
            //writer.print(123,e.getMessage());
        }

        return null;
    }
}



import org.apache.commons.codec.binary.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

import java.net.URL;
import java.util.Scanner;


public class Weather {

    public static String getWeather(String message, Model model) throws IOException {
        URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + message + "&units=metric&appid=4076e9d02f91410715480e738fc7e415");

        Scanner in = new Scanner((InputStream) url.getContent());
        String result = "";
        while (in.hasNext()) {
            result += in.nextLine();
        }
        JSONObject object = new JSONObject(result);
        model.getName();

        JSONObject main = object.getJSONObject("main");
        model.setTemp(main.getDouble("temp"));
        model.setHumidity(main.getDouble("humidity"));


        JSONArray getArray = object.getJSONArray("weather");
        for (int i = 0; i < getArray.length(); i++) {
            JSONObject obj = getArray.getJSONObject(i);
            model.setIcon((String) obj.get("icon"));
            model.setMain((String) obj.get("main"));

        }
        return  "Город: " + message.substring(0,1).toUpperCase() + message.substring(1) + "\n" +
                "Температура: " + model.getTemp() + "\n" +
                "Влажность: " + model.getHumidity() + "\n" +
                "Погода: " + model.getMain() + "\n" +
                "http://api.openweathermap.org/img/w/"+model.getIcon()+".png";
    }
}
//<div id="openweathermap-widget-12"></div>
//<script>window.myWidgetParam ? window.myWidgetParam : window.myWidgetParam = [];
// window.myWidgetParam.push({id: 12,cityid: '625144',appid: 'e0dec6cf6550cf1070cc177ef79a4109',units: 'metric',containerid: 'openweathermap-widget-12',  });
// (function() {var script = document.createElement('script');script.async = true;script.charset = "utf-8";script.src = "//openweathermap.org/themes/openweathermap/assets/vendor/owm/js/weather-widget-generator.js";
// var s = document.getElementsByTagName('script')[0];s.parentNode.insertBefore(script, s);  })();</script>
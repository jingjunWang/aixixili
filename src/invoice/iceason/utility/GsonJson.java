package invoice.iceason.utility;

import com.google.gson.Gson;

public class GsonJson {
	private static Gson gson = new Gson();
	public static Gson getGson(){
		return gson;
	}
}

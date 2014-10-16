import javax.json.*;
import java.net.*;
import java.io.*;

public class android
{
	public static void main(String[] args) throws IOException, ProtocolException
	{
		HttpURLConnection request;
		try
		{
			request = (HttpURLConnection)(new URL("http://api.bandsintown.com/artists/slayer/events.json?api_version=2.0&app_id=YOUR_APP_ID")).openConnection();
		}
		catch(IOException io)
		{
			System.out.println("Failed to open connection");
			return;
		}
		try
		{
			request.setRequestMethod("GET");
		}
		catch(ProtocolException pe)
		{
			System.out.println("Protocol Exception");
			return;
		}
		try
		{
			request.connect();
		}
		catch(IOException io)
		{
			System.out.println("Failed to connect");
			return;
		}

		JsonReader reader = Json.createReader(new InputStreamReader(request.getInputStream()));
		JsonArray array = reader.readArray();

		JsonWriter writer = Json.createWriter(new FileOutputStream("../Slayer.txt"));
		writer.writeArray(array);
		writer.close();

		System.out.println("Slayer");
		System.out.println("\n");
		for(JsonValue val : array)
		{
			JsonObject obj = (JsonObject)val;
			String datetime[] = obj.get("datetime").toString().split("T");
			System.out.println("Date: " + datetime[0].replaceAll("\"",""));
			System.out.println("Time: " + datetime[1].replaceAll("\"",""));
			System.out.println("URL: " + obj.get("facebook_rsvp_url").toString().replaceAll("\"",""));
			JsonObject venue = (JsonObject)obj.get("venue");
			System.out.println("Name: " + venue.get("name").toString().replaceAll("\"",""));
			System.out.println("City: " + venue.get("city").toString().replaceAll("\"",""));
			System.out.println("Country: " + venue.get("country").toString().replaceAll("\"",""));
			System.out.println();
		}
	}
}
package no.hvl.dat100ptc.oppgave3;

import static java.lang.Math.*;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSUtils {

	public static double findMax(double[] da) {

		double max;

		max = da[0];

		for (double d : da) {
			if (d > max) {
				max = d;
			}
		}

		return max;
	}

	public static double findMin(double[] da) {

		double min;

		// TODO - START

		min = da[0];

		for (double d : da) {
			if (d < min) {
				min = d;
			}
		}

		return min;

		// TODO - SLUT

	}

	public static double[] getLatitudes(GPSPoint[] gpspoints) {

		// TODO - START

		double[] latitudeTab = new double[gpspoints.length];

		for (int i = 0; i < latitudeTab.length; i++) {
			latitudeTab[i] = gpspoints[i].getLatitude();
		}

		return latitudeTab;

		// TODO - SLUTT
	}

	public static double[] getLongitudes(GPSPoint[] gpspoints) {

		// TODO - START

		double[] longitudeTab = new double[gpspoints.length];

		for (int i = 0; i < longitudeTab.length; i++) {
			longitudeTab[i] = gpspoints[i].getLongitude();
		}

		return longitudeTab;

		// TODO - SLUTT

	}

	private static int R = 6371000; // jordens radius

	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		double d;
		double latitude1, longitude1, latitude2, longitude2;

		// TODO - START

		latitude1 = toRadians(gpspoint1.getLatitude());
		longitude1 = toRadians(gpspoint1.getLongitude());
		latitude2 = toRadians(gpspoint2.getLatitude());
		longitude2 = toRadians(gpspoint2.getLongitude());
		
		double deltaLati = (latitude2) - (latitude1);
		double deltaLong = (longitude2) - (longitude1);
		double a = pow(sin(deltaLati / 2), 2) + cos(latitude1) * cos(latitude2) * pow(sin(deltaLong / 2), 2);
		double c = 2 * atan2(sqrt(a), sqrt(1 - a));
		d = R * c;
		return d;
			
		// TODO - SLUTT

	}

	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		int secs;
		double speed;

		// TODO - START

		secs = gpspoint2.getTime() - gpspoint1.getTime();
		double distance = distance(gpspoint1, gpspoint2);
		//speed = (distance / 1000) / (secs / 3600);
		speed = (distance / secs) * 3.6;

		return speed;
		
		// TODO - SLUTT

	}

	public static String formatTime(int secs) {

		String timestr;
		//String TIMESEP = ":";

		// TODO - START

		int h = secs / 3600;
		int hRest = secs % 3600;
		int m = hRest / 60;
		int mRest = hRest % 60;
		int s = mRest;
		String hh = "" + h;
		String mm = "" + m;
		String ss = "" + s;
		if (h < 10) hh = "0" + h;
		if (m < 10) mm = "0" + m;
		if (s < 10) ss = "0" + s;
		timestr = hh + ":" + mm + ":" + ss;
		
		String b = "";
		for (int i = 0; i <= 10 - timestr.length() - 1; i++) {
			b += " ";
		}
		return b + timestr;
		// TODO - SLUTT

	}

	private static int TEXTWIDTH = 10;

	public static String formatDouble(double d) {

		//String str;

		// TODO - START

		//String a = String.format("%.2f", d);
		double desimalRound = round(d * 100.0) / 100.0;
		String a = "" + desimalRound;
		String b = "";
		for (int i = 0; i <= 10 - a.length() - 1; i++) {
			b += " ";
		}
		return b + a;

		// TODO - SLUTT

	}
}

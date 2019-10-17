package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

public class ShowRoute extends EasyGraphics {

	private static int MARGIN = 50;
	private static int MAPXSIZE = 700;
	private static int MAPYSIZE = 700;

	private GPSPoint[] gpspoints;
	private GPSComputer gpscomputer;
	
	public ShowRoute() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		makeWindow("Route", MAPXSIZE + 2 * MARGIN, MAPYSIZE + 2 * MARGIN);

		showRouteMap(MARGIN + MAPYSIZE);

//		playRoute(MARGIN + MAPYSIZE);
		
		showStatistics();
	}

	// antall x-pixels per lengdegrad
	public double xstep() {

		double maxlon = GPSUtils.findMax(GPSUtils.getLongitudes(gpspoints));
		double minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));

		double xstep = MAPXSIZE / (Math.abs(maxlon - minlon)); 

		return xstep;
	}

	// antall y-pixels per breddegrad
	public double ystep() {
	
		// TODO - START
		
		double maxlat = GPSUtils.findMax(GPSUtils.getLatitudes(gpspoints));
		double minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));
		
		double ystep = MAPYSIZE / (Math.abs(maxlat - minlat));
		
		return ystep;
		
		// TODO - SLUTT
		
	}

	public void showRouteMap(int ybase) {

		// TODO - START
		
		double minlong = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));
		double minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));
		
		setColor(0, 255, 0);
		int prevx = 0;
		int prevy = 0;
		
		for (int i = 0; i < gpspoints.length; i++) {
			int x = MARGIN + (int) ((gpspoints[i].getLongitude() - minlong) * xstep());
			int y = ybase - (int) ((gpspoints[i].getLatitude() - minlat) * ystep());
			fillCircle(x, y, 3);
			
			if (i > 0) {
				drawLine(prevx, prevy, x, y);
			}
			
			prevx = x;
			prevy = y;
			
		}
		
		// TODO - SLUTT
	}

	public void showStatistics() {

		int TEXTDISTANCE = 20;

		setColor(0,0,0);
		setFont("Courier",12);
		
		// TODO - START
		
		drawString("Total time     :" + GPSUtils.formatTime(gpscomputer.totalTime()), MARGIN, 10);
		drawString("Total distance :" + GPSUtils.formatDouble(gpscomputer.totalDistance() / 1000) + " km", MARGIN, 10 + TEXTDISTANCE);
		drawString("Total elevation:" + GPSUtils.formatDouble(gpscomputer.totalElevation()) + " m", MARGIN, 10 + 2 * TEXTDISTANCE);
		drawString("Max speed      :" + GPSUtils.formatDouble(gpscomputer.maxSpeed()) + " km/t", MARGIN, 10 + 3 * TEXTDISTANCE);
		drawString("Average speed  :" + GPSUtils.formatDouble(gpscomputer.averageSpeed()) + " km/t", MARGIN, 10 + 4 * TEXTDISTANCE);
		drawString("Energy         :" + GPSUtils.formatDouble(gpscomputer.totalKcal(80)) + " kcal", MARGIN, 10 + 5 * TEXTDISTANCE);
		
		// TODO - SLUTT;
	}

	public void playRoute(int ybase) {

		// TODO - START
		
		throw new UnsupportedOperationException(TODO.method());
		
		// TODO - SLUTT
	}

}

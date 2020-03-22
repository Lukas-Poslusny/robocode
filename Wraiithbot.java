import robocode.AdvancedRobot;
import robocode.ScannedRobotEvent;

import java.awt.*;

public class Wraiithbot extends AdvancedRobot {

    public void run() {
        // sets up robots basics
        configureTank();

        // spin infinitely
        turnRadarRight(360);
        // scan for any other robots

        while (true) {
            move();
            scan();
            shoot();
        }
    }

    public void move () {
        setAhead(100);
        setTurnRight(90);
        setAhead(100);
        setTurnRight(90);
        setAhead(100);
        setTurnRight(90);
        setAhead(100);
        setTurnRight(90);
    }

    public void shoot () {
        if (getEnergy() < 15) {
            fire(3);
        } else {
            fire(1);
        }
    }

    public void onScannedRobot(ScannedRobotEvent e) {
        double radarTurn =
                // Lock on target
                getHeadingRadians() + e.getBearingRadians()
                        // Subtract current radar heading to get turn required
                        - getRadarHeadingRadians();

        // move radar
        setTurnRadarRightRadians(radarTurn);

        // move gun
        double gunSpin = getGunHeadingRadians() - getRadarHeadingRadians();
        setTurnGunLeftRadians(gunSpin);

    }

    private void configureTank() {
        // colors
        setBodyColor(Color.BLACK);
        setGunColor(Color.BLACK);
        setScanColor(Color.BLUE);
        setRadarColor(Color.BLACK);

        // independent Gun and Robot Turning
        setAdjustGunForRobotTurn(true);
        // independent Radar and gun Turning
        setAdjustRadarForGunTurn(true);
    }
}
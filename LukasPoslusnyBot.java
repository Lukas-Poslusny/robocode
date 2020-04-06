import robocode.*;

import java.awt.*;

public class LukasPoslusnyBot extends AdvancedRobot {
    int missCounter = 0;
    boolean locked = false;

    public void run() {
        // sets up robots basics
        configureTank();
        turnRadarRight(360);

        while (true) {
            move(1);
            scan();
            shoot();
        }
    }

    public void onRobotDeath(RobotDeathEvent event) {
        turnRadarRight(360);
        scan();
    }

    public void onBulletMissed(BulletMissedEvent event) {
        missCounter += 1;
        if (missCounter == 3) {
            scan();
            missCounter = 0;
        }
    }

    public void shoot () {
        if(getEnergy() < 30) {
            fire(1);
        }
        else if (getEnergy() > 30) {
            fire(3);
        }
    }

    public void move (int times) {
        for (int i = 0; i < times; i++) {
            setAhead(100);
            setTurnRight(90);
            setAhead(100);
            setTurnRight(90);
            setAhead(100);
            setTurnRight(90);
            setAhead(100);
            setTurnRight(90);
        }
    }

    public void onScannedRobot (ScannedRobotEvent e) {
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

    public void onHitWall(HitWallEvent e){
        double bearing = e.getBearing();
        turnRight(-bearing);
        ahead(100);

    }

    public void onHitRobot(HitRobotEvent e) {
        ahead(-50);
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
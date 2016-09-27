package com.qualcomm.ftcrobotcontroller.opmodes;

import android.os.SystemClock;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by kadoo on 11/25/2015.
 */
public class MoveTest extends OpMode {

    DcMotor DLeft;
    DcMotor DRight;
    Servo Flapper;
    double flapperPower;
    int count = 0;
    int count2 = 0;

    public MoveTest() {}

    @Override
    public void init() {
        DLeft = hardwareMap.dcMotor.get("motor_L");
        DRight = hardwareMap.dcMotor.get("motor_R");
        Flapper = hardwareMap.servo.get("flapper");
        DLeft.setDirection(DcMotor.Direction.REVERSE);
        flapperPower = 0.55;
    }
    @Override
    public void loop() {
        flapperPower = 0.2;
        if(count <1) {
            Flapper.setPosition(flapperPower);
            DLeft.setPower(1);
            DRight.setPower(1);
            SystemClock.sleep(500);
            count ++;
        }
        else {
            DRight.setPower(0);
            DLeft.setPower(0);
            Flapper.setPosition(0.55);
        }
        }
    @Override
    public void stop() {
        DLeft.setPower(0);
        DRight.setPower(0);
        Flapper.setPosition(0.55);
    }
}

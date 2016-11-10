package org.firstinspires.ftc.teamcode;

/**
 * Created by Kaden Eyre on 10/22/2016.
 * Made for main configuration of new robot.
 */
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="TestMode", group="Teleop")

public class testMode extends OpMode{
    public DcMotor motor2;
    public DcMotor motor1;
    public Servo Servo1;

    public final static double servoMin = 0.1;
    public final static double servoMax = 0.9;

    //HardwareMap hwMap;

    public testMode() {}

    @Override
    public void init() {

        motor2 = hardwareMap.dcMotor.get("motor2");
        motor1 = hardwareMap.dcMotor.get("motor1");

        motor2.setDirection(DcMotor.Direction.FORWARD);
        motor1.setDirection(DcMotor.Direction.REVERSE);
        motor2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        Servo1 = hardwareMap.servo.get("servo1");

        Servo1.setPosition(0.5);
    }
    @Override
    public void loop() {
        float M2 = gamepad1.left_stick_y;
        float M1 = gamepad2.left_stick_y;
        double S1 = 0.5;

        M2 = Range.clip(M2, -1, 1);
        M1 = Range.clip(M1, -1, 1);

        if (gamepad1.a) {
            S1 = S1 + 0.05;
        }
        if (gamepad1.b) {
            S1 = S1 - 0.05;
        }

        motor2.setPower(M2);
        motor1.setPower(M1);

        Servo1.setPosition(S1);

        telemetry.addData("leftDrive", "Motor 2 Drive Power: " + String.format("%.2f", M2));
        telemetry.addData("rightDrive", "Motor 1 Drive Power: " + String.format("%.2f", M1));
    }
    @Override
    public void stop(){}
}

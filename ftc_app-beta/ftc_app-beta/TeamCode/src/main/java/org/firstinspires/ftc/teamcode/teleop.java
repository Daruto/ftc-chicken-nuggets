package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by kadoo on 11/25/2015.
 */

@TeleOp(name="TeleOp1", group="Teleop")

public class teleop extends OpMode{
    DcMotor DRight;
    DcMotor DLeft;
    DcMotor Conveyor;
    DcMotor bucket;
    Servo cBucket;
    Servo Extender;

    final static double servoMax = 0.8;
    final static double servoMin = 0.2;
    int cBucketOut = 0;
    double cBucketPos;
    double extenderPos = 0.9;
    double servoChange = 0.1;

    public teleop () {}

    @Override
    public void init() {
        DRight = hardwareMap.dcMotor.get("motor_R");
        DLeft = hardwareMap.dcMotor.get("motor_L");
        DLeft.setDirection(DcMotor.Direction.REVERSE);
        cBucket = hardwareMap.servo.get("climberBucket");
        Extender = hardwareMap.servo.get("extenderator");
        bucket = hardwareMap.dcMotor.get("bucket");
        Conveyor = hardwareMap.dcMotor.get("conveyor");

        DRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        DLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
    @Override
    public void loop() {
        float throttle = -gamepad1.left_stick_y;
        float direction = gamepad1.left_stick_x;
        float right = throttle - direction;
        float left = throttle + direction;
        float bucketPower = (gamepad1.right_trigger - gamepad1.left_trigger) * cBucketOut;
        float conveyorPower = gamepad1.right_stick_y;
        right = Range.clip(right, -1, 1);
        left = Range.clip(left, -1, 1);
        bucketPower = Range.clip(bucketPower, -1, 1);
        conveyorPower = Range.clip(conveyorPower, -1, 1);

        DRight.setPower(right);
        DLeft.setPower(left);
        bucket.setPower(bucketPower);
        Conveyor.setPower(conveyorPower);

        if(gamepad1.dpad_up) {
            cBucketPos = 0.9;
            cBucketOut = 1;
        }
        if(gamepad1.dpad_down) {
            cBucketPos = 0.1;
            cBucketOut = 0;
        }
        if(gamepad1.right_bumper) {
            extenderPos = 0.1;
        }else {
            extenderPos = 0.9;
        }

        cBucket.setPosition(cBucketPos);
        Extender.setPosition(extenderPos);
    }
    @Override
    public void stop() {}
}

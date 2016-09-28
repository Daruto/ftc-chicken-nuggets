package org.firstinspires.ftc.teamcode;

/**
 * Created by Kaden Eyre on 8/25/2016.
 * Made for Test configuration of new robot.
 */
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="TestTeleop", group="Teleop")

public class ProgrammingBot extends OpMode{
    public DcMotor leftDrive;
    public DcMotor rightDrive;
    public Servo arm;
    public Servo wrist;

    public final static double armMin = 0.2;
    public final static double armMax = 0.8;
    public final static double wristMin = 0.2;
    public final static double wristMax = 0.8;

    public ProgrammingBot() {}

    @Override
    public void init() {

        leftDrive = hardwareMap.dcMotor.get("leftDrive");
        rightDrive = hardwareMap.dcMotor.get("rightDrive");
        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);

        arm = hardwareMap.servo.get("arm");
        wrist = hardwareMap.servo.get("wrist");

        leftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        leftDrive.setPower(0);
        rightDrive.setPower(0);
        arm.setPosition(armMin);
        wrist.setPosition(wristMin);

    }
    @Override
    public void loop() {
        float throttle = gamepad1.left_stick_y;
        float direction = gamepad1.left_stick_x;
        float right = throttle + direction;
        float left = throttle - direction;

        left = Range.clip(left, -1, 1);
        right = Range.clip(right,-1, 1);

        leftDrive.setPower(left);
        rightDrive.setPower(right);

        telemetry.addData("leftDrive", "Left Drive Power: " + String.format("%.2f", left));
        telemetry.addData("rightDrive", "Right Drive Power: " + String.format("%.2f", right));
    }
    @Override
    public void stop(){}
}

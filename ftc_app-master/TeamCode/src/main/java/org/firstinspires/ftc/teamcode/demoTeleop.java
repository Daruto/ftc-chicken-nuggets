package org.firstinspires.ftc.teamcode;

/**
 * Created by Kaden Eyre on 8/21/2016.
 * Made for main configuration of new robot.
 */
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="demoTeleop", group="Teleop")

public class demoTeleop extends OpMode{
    public DcMotor leftDrive;
    public DcMotor rightDrive;
    public DcMotor arm;

    //HardwareMap hwMap;

    public demoTeleop() {}

    @Override
    public void init() {

        leftDrive = hardwareMap.dcMotor.get("leftDrive");
        rightDrive = hardwareMap.dcMotor.get("rightDrive");
        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);

        arm = hardwareMap.dcMotor.get("Arm");

        leftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }
    @Override
    public void loop() {
        float throttle = gamepad1.left_stick_y;
        float direction = gamepad1.left_stick_x;
        float right = throttle + direction;
        float left = throttle - direction;

        float armPos = gamepad1.right_stick_y;

        left = Range.clip(left, -1, 1);
        right = Range.clip(right, -1, 1);
        armPos = Range.clip(armPos, -1, 1);

        leftDrive.setPower(left);
        rightDrive.setPower(right);
        arm.setPower(armPos);

        telemetry.addData("leftDrive", "Left Drive Power: " + String.format("%.2f", left));
        telemetry.addData("rightDrive", "Right Drive Power: " + String.format("%.2f", right));
    }
    @Override
    public void stop(){}
}

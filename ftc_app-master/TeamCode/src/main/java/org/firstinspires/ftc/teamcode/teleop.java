package org.firstinspires.ftc.teamcode;

/**
 * Created by Kaden Eyre on 8/25/2016.
 * Made for main configuration of new robot.
 */
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Teleop1", group="Teleop")

public class teleop extends OpMode{
    public DcMotor leftDrive;
    public DcMotor rightDrive;
    public DcMotor Arm;

    public Servo claw;

    public teleop() {}

    @Override
    public void init() {

        leftDrive = hardwareMap.dcMotor.get("leftDrive");
        rightDrive = hardwareMap.dcMotor.get("rightDrive");
        Arm = hardwareMap.dcMotor.get("arm");
        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);
        Arm.setDirection(DcMotor.Direction.FORWARD);

        leftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        claw = hardwareMap.servo.get("clawServo");

    }
    @Override
    public void loop() {
        float throttle = gamepad1.left_stick_y;
        float direction = gamepad1.left_stick_x;
        float right = throttle + direction;
        float left = throttle - direction;

        float armMove = gamepad1.right_stick_y;

        left = Range.clip(left, -1, 1);
        right = Range.clip(right, -1, 1);
        armMove = Range.clip(armMove, -1, 1);

        leftDrive.setPower(left);
        rightDrive.setPower(right);
        Arm.setPower(armMove);

        double clawPos = 0.5;
        double clawDelta = 0.5;
        int triggered = 1;

        if (gamepad1.a) {
            if (triggered == 1) {
                triggered = 0;
                clawPos = clawPos + clawDelta;
            }
        }else {
        if (gamepad1.b) {
            if (triggered == 1) {
                triggered = 0;
                clawPos = clawPos - clawDelta;
            }
        }
        else {triggered = 1;}}

        claw.setPosition(clawPos);

        telemetry.addData("leftDrive", "Left Drive Power: " + String.format("%.2f", left));
        telemetry.addData("rightDrive", "Right Drive Power: " + String.format("%.2f", right));
        telemetry.addData("claw", "Claw Position: " + String.format("%.2f", clawPos));
    }
    @Override
    public void stop(){}
}
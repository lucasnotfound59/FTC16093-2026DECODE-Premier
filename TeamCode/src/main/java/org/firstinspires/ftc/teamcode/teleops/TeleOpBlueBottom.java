package org.firstinspires.ftc.teamcode.teleops;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;

@TeleOp(name = "TeleOp Blue Bottom", group = "TeleOp")
public class TeleOpBlueBottom extends TeleOpBase {
    public TeleOpBlueBottom() {
        // target = Blue goal (10, 133) inch，PP 场地坐标系
        super(10, 133,
                new Pose2D(DistanceUnit.INCH, 39, 31, AngleUnit.DEGREES, 90),
                new Pose2D(DistanceUnit.INCH, -27.56, -90.55, AngleUnit.DEGREES, -90),
                0);
    }
}
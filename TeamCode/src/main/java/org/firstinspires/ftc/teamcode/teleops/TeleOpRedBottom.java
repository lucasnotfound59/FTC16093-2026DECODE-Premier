package org.firstinspires.ftc.teamcode.teleops;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;

@TeleOp(name = "TeleOp Red Bottom", group = "TeleOp")
public class TeleOpRedBottom extends TeleOpBase {
    public TeleOpRedBottom() {
        // target = Red goal (130, 134) inch，PP 场地坐标系
        super(130, 134,
                new Pose2D(DistanceUnit.INCH, 102, 32, AngleUnit.DEGREES, 90),
                new Pose2D(DistanceUnit.INCH, -22.44, 90.55, AngleUnit.DEGREES, 90),
                0);
    }
}

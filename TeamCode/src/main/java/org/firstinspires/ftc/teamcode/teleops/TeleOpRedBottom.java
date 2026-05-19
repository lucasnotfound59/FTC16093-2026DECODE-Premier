package org.firstinspires.ftc.teamcode.teleops;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;

@TeleOp(name = "TeleOp Red Bottom", group = "TeleOp")
public class TeleOpRedBottom extends TeleOpBase {
    public TeleOpRedBottom() {
        super(99.06, 287.02,
                new Pose2D(DistanceUnit.INCH, 105, 31, AngleUnit.DEGREES, 0),
                new Pose2D(DistanceUnit.CM, -57, 230, AngleUnit.DEGREES, 90),
                0);
    }
}

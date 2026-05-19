package org.firstinspires.ftc.teamcode.teleops;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;

@TeleOp(name = "TeleOp Blue Bottom", group = "TeleOp")
public class TeleOpBlueBottom extends TeleOpBase {
    public TeleOpBlueBottom() {
        super(-99.06, 287.02,
                new Pose2D(DistanceUnit.INCH, 39, 31, AngleUnit.DEGREES, 180),
                new Pose2D(DistanceUnit.CM, -70, -230, AngleUnit.DEGREES, -90),
                180);
    }
}
package org.firstinspires.ftc.teamcode.teleops;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;

@TeleOp(name = "TeleOp Test", group = "TeleOp")
public class TeleOpTest extends TeleOpBaseTest {
    public TeleOpTest() {
        super(266.98, 97.83, 90, new Pose2D(DistanceUnit.CM, 0, 0, AngleUnit.DEGREES, 0), 90);
    }
}

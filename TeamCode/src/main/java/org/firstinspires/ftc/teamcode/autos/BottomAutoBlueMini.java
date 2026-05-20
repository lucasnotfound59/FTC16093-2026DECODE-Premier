package org.firstinspires.ftc.teamcode.autos;

import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Bottom Auto Blue Mini", group = "Auto")
public class BottomAutoBlueMini extends BottomAutoBaseMini {
    public BottomAutoBlueMini() {
        super(
                // autoPanTarget 改 inch（原 310, 120 cm = 122.05, 47.24 inch）
                122.05, 47.24, 90,
                new Pose(59, 10, Math.toRadians(180)),
                new Pose(15, 10, Math.toRadians(180)),
                new Pose(15, 20, Math.toRadians(180)),
                new Pose(40.4, 31.4, Math.toRadians(180))
        );
    }
}

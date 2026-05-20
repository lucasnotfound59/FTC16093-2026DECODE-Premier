package org.firstinspires.ftc.teamcode.autos;

import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Bottom Auto Red Mini", group = "Auto")
public class BottomAutoRedMini extends BottomAutoBaseMini{
    public BottomAutoRedMini() {
        super(
                // autoPanTarget 改 inch（原 310, -120 cm = 122.05, -47.24 inch）
                122.05, -47.24, -90,
                new Pose(85, 10, 0),
                new Pose(130, 10, 0),
                new Pose(130, 20, 0),
                new Pose(102.6, 32, 0)
        );
    }
}

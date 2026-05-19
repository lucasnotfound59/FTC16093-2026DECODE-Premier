package org.firstinspires.ftc.teamcode.subsystems;

import androidx.annotation.NonNull;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.robotcore.external.navigation.UnnormalizedAngleUnit;

import lombok.Getter;
import lombok.Setter;

/**
 * 数据容器：从外置里程计 / 定位计算机获取的机器人运动状态。
 * 所有量以机器人自身坐标系定义：x 向左为正，y 向前为正。
 */
public class PinpointDriverData {
    
    // ==========================================
    // 成员变量
    // ==========================================
    private final GoBildaPinpointDriver pinpointDriver;

    // 当前航向（弧度）
    @Getter
    private double headingRadians;

    // 当前航向（度）
    @Getter
    private double headingDegrees;

    // 机器人X坐标
    @Getter
    private double robotX;

    // 机器人Y坐标
    @Getter
    private double robotY;

    // 机器人X方向速度
    @Getter
    private double robotVx;

    // 机器人Y方向速度
    @Getter
    private double robotVy;

    // 航向角速度
    @Getter
    private double yawRate;

    // 机器人位置
    @Getter @Setter
    private Pose2D robotPosition;

    /**
     * 从GoBilda Pinpoint里程计构造里程计数据实例
     * @param pinpointDriver GoBilda Pinpoint里程计驱动
     */
    public PinpointDriverData(@NonNull GoBildaPinpointDriver pinpointDriver) {
        this.pinpointDriver = pinpointDriver;
        this.headingRadians = pinpointDriver.getHeading(AngleUnit.RADIANS);
        this.headingDegrees = pinpointDriver.getHeading(AngleUnit.DEGREES);
        this.robotX = pinpointDriver.getPosX(DistanceUnit.CM);
        this.robotY = pinpointDriver.getPosY(DistanceUnit.CM);
        this.robotVx = pinpointDriver.getVelX(DistanceUnit.CM);
        this.robotVy = pinpointDriver.getVelY(DistanceUnit.CM);
        this.yawRate = pinpointDriver.getHeadingVelocity(UnnormalizedAngleUnit.DEGREES);
        this.robotPosition = pinpointDriver.getPosition();
    }

    public void update() {
        if (pinpointDriver != null) {
            pinpointDriver.update();
            this.headingRadians = pinpointDriver.getHeading(AngleUnit.RADIANS);
            this.headingDegrees = pinpointDriver.getHeading(AngleUnit.DEGREES);
            this.robotX = pinpointDriver.getPosX(DistanceUnit.CM);
            this.robotY = pinpointDriver.getPosY(DistanceUnit.CM);
            this.robotVx = pinpointDriver.getVelX(DistanceUnit.CM);
            this.robotVy = pinpointDriver.getVelY(DistanceUnit.CM);
            this.yawRate = pinpointDriver.getHeadingVelocity(UnnormalizedAngleUnit.DEGREES);
            this.robotPosition = pinpointDriver.getPosition();
        }
    }
}

package org.firstinspires.ftc.teamcode.subsystems;

import androidx.annotation.NonNull;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.robotcore.external.navigation.UnnormalizedAngleUnit;

import lombok.Getter;

/**
 * 数据容器：从外置里程计 / 定位计算机获取的机器人运动状态。
 *
 * <h3>单位约定（全工程统一 inch）</h3>
 * <ul>
 *   <li>位置 / 速度 getter（{@link #getRobotX()} 等）一律返回 <b>inch</b>。</li>
 *   <li>下游消费者：{@link AutoPan}、{@link org.firstinspires.ftc.teamcode.teleops.ShooterTuner ShooterTuner}
 *       —— 都以 inch 作为 target 单位，需要保持一致。</li>
 *   <li>PP follower 跟本类各自调用 Pinpoint 设备读位置，互不干扰。
 *       两边都跑 inch 现在统一了。</li>
 *   <li>{@link #setRobotPosition(Pose2D)} 内部显式提取 inch 数值再写硬件，
 *       绕开 Pinpoint SDK 对 Pose2D 自带 DistanceUnit 的潜在 bug。</li>
 * </ul>
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
    @Getter
    private Pose2D robotPosition;

    /**
     * 从GoBilda Pinpoint里程计构造里程计数据实例
     * @param pinpointDriver GoBilda Pinpoint里程计驱动
     */
    public PinpointDriverData(@NonNull GoBildaPinpointDriver pinpointDriver) {
        this.pinpointDriver = pinpointDriver;
        this.headingRadians = pinpointDriver.getHeading(AngleUnit.RADIANS);
        this.headingDegrees = pinpointDriver.getHeading(AngleUnit.DEGREES);
        this.robotX = pinpointDriver.getPosX(DistanceUnit.INCH);
        this.robotY = pinpointDriver.getPosY(DistanceUnit.INCH);
        this.robotVx = pinpointDriver.getVelX(DistanceUnit.INCH);
        this.robotVy = pinpointDriver.getVelY(DistanceUnit.INCH);
        this.yawRate = pinpointDriver.getHeadingVelocity(UnnormalizedAngleUnit.DEGREES);
        this.robotPosition = pinpointDriver.getPosition();
    }

    public void update() {
        if (pinpointDriver != null) {
            pinpointDriver.update();
            this.headingRadians = pinpointDriver.getHeading(AngleUnit.RADIANS);
            this.headingDegrees = pinpointDriver.getHeading(AngleUnit.DEGREES);
            this.robotX = pinpointDriver.getPosX(DistanceUnit.INCH);
            this.robotY = pinpointDriver.getPosY(DistanceUnit.INCH);
            this.robotVx = pinpointDriver.getVelX(DistanceUnit.INCH);
            this.robotVy = pinpointDriver.getVelY(DistanceUnit.INCH);
            this.yawRate = pinpointDriver.getHeadingVelocity(UnnormalizedAngleUnit.DEGREES);
            this.robotPosition = pinpointDriver.getPosition();
        }
    }

    /**
     * 设置机器人初始位置：直接写入 Pinpoint 硬件，并刷新所有缓存字段。
     *
     * 不能用 Lombok @Setter，那只会改本地字段，下次 update() 立刻被硬件值覆盖；
     * 也不能只更新 robotPosition，因为消费者（Drive / AutoPan）读的是
     * headingRadians / robotX / robotY 等独立缓存字段。
     */
    public void setRobotPosition(Pose2D pose) {
        this.robotPosition = pose;
        if (pinpointDriver != null) {
            // 显式提取 INCH/RAD 值后构造统一单位的 Pose2D，避免依赖 Pinpoint SDK
            // 对 Pose2D 内自带 DistanceUnit / AngleUnit 的处理（如果 SDK 忽略单位标记
            // 把数值当 mm 用，位置会被设错 25 倍 / 64 倍——加这层显式转换是兜底）。
            Pose2D normalizedPose = new Pose2D(
                    DistanceUnit.INCH,
                    pose.getX(DistanceUnit.INCH),
                    pose.getY(DistanceUnit.INCH),
                    AngleUnit.RADIANS,
                    pose.getHeading(AngleUnit.RADIANS)
            );
            pinpointDriver.setPosition(normalizedPose);
            // 立即从硬件回读，保证下一帧 update() 前的读取也是新值
            this.headingRadians = pinpointDriver.getHeading(AngleUnit.RADIANS);
            this.headingDegrees = pinpointDriver.getHeading(AngleUnit.DEGREES);
            this.robotX = pinpointDriver.getPosX(DistanceUnit.INCH);
            this.robotY = pinpointDriver.getPosY(DistanceUnit.INCH);
        }
    }
}

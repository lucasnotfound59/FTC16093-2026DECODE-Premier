package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;

import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.VoltageSensor;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.jetbrains.annotations.Contract;

public class Hardwares {
    public Sensors sensors;
    public Motors motors;
    public Servos servos;

    public static <T> T getHardware(@NonNull HardwareMap hardwareMap, String name, Class<T> clazz){
        return hardwareMap.get(clazz, name);
    }

    @NonNull
    @Contract("_, _, _, _ -> new")
    public static ServoEx getHardware(@NonNull HardwareMap hardwareMap, String name, double minAngleDegree, double maxAngleDegree){
        return new SimpleServo(hardwareMap, name, minAngleDegree, maxAngleDegree);
    }

    public static class Sensors{
        public GoBildaPinpointDriver odo;
        public VoltageSensor voltageSensor;
        /** Limelight 3A 视觉传感器；硬件配置中缺失时为 null（不阻塞启动）。 */
        public Limelight3A ll;

        public Sensors(@NonNull HardwareMap hardwareMap){
            odo = getHardware(hardwareMap, "pinpoint", GoBildaPinpointDriver.class);
            // 按官方 sample 推荐顺序配置：offsets → resolution → directions → resetPosAndIMU。
            //
            // Pod 安装偏置：9.6 cm = 3.78 inch（X 前向 pod 横向偏置），6.8 cm = 2.68 inch（Y 横向 pod 前向偏置）。
            // 跟 PP 的 Constants.localizerConstants 数值一致，单位换算后等价（9.6 cm = 3.78 inch）。
            odo.setOffsets(3.78, 2.68, DistanceUnit.INCH);
            // 编码器解析度必须显式指定！不调用的话 Pinpoint 用默认 pod 型号算位置，
            // 跟实际 4-bar pod 不匹配会导致位置读数严重错误（甚至"X/Y 几乎不动"）。
            odo.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
            odo.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.FORWARD, GoBildaPinpointDriver.EncoderDirection.FORWARD);
            // 一步把位置和 IMU 都重置——比 recalibrateIMU + setPosition(0,0,0) 更原子，
            // 避免异步校准跟 setPosition 时序竞争（之前怀疑这导致 startPose 设了又被清）。
            odo.resetPosAndIMU();

            voltageSensor = hardwareMap.voltageSensor.iterator().next();

            // LL 找不到（机器人配置里没有"limelight"，或硬件没装）时静默降级为 null。
            // VisionBearingTracker 看到 ll==null 永远返回 isFresh=false，
            // AutoPan 自动退到纯 odo 跟踪——保持没 LL 的车也能跑这套代码。
            try {
                ll = hardwareMap.get(Limelight3A.class, "limelight");
            } catch (Exception e) {
                ll = null;
            }
        }
    }

    public static class Motors{
        public DcMotorEx mLeftFront, mRightFront, mLeftRear, mRightRear, shooterLeft, shooterRight, intake, pan;
        public Motors(@NonNull HardwareMap hardwareMap){
            mLeftFront = getHardware(hardwareMap, "leftFront", DcMotorEx.class);
            mRightFront = getHardware(hardwareMap, "rightFront", DcMotorEx.class);
            mLeftRear = getHardware(hardwareMap, "leftRear", DcMotorEx.class);
            mRightRear = getHardware(hardwareMap, "rightRear", DcMotorEx.class);

            intake = getHardware(hardwareMap, "intake", DcMotorEx.class);

            shooterLeft = getHardware(hardwareMap, "shooterLeft", DcMotorEx.class);
            shooterRight = getHardware(hardwareMap, "shooterRight", DcMotorEx.class);

            pan = getHardware(hardwareMap, "pan", DcMotorEx.class);
        }
    }

    public static class Servos{
        public ServoEx gate, pitch;
        public Servos(@NonNull HardwareMap hardwareMap){
            gate = getHardware(hardwareMap, "gate", 0, 300);
            pitch = getHardware(hardwareMap, "pitch", 0, 300);
        }
    }

    public Hardwares(HardwareMap hardwareMap){
        sensors = new Sensors(hardwareMap);
        motors = new Motors(hardwareMap);
        servos = new Servos(hardwareMap);
    }
}

package org.firstinspires.ftc.teamcode.utils;

/**
 * 场地常量：AprilTag ID、Goal 坐标查找等。
 *
 * <h3>坐标系约定（与 AutoPan / PinpointDriverData 一致）</h3>
 * PedroPathing 场地系：原点在场地一角，X / Y 全正数，单位 <b>inch</b>。
 * 全工程统一 inch，AutoPan / PP follower / ShooterTuner 同一单位互通。
 *
 * <h3>⚠️ tagIdForGoalY 已失效</h3>
 * 新坐标系下蓝/红 goal Y 同号（都为正），Y 符号已经不能区分 alliance。
 * 不要再用 {@link #tagIdForGoalY(double)} —— 应该由 OpMode 直接传 TAG_ID_BLUE_GOAL
 * 或 TAG_ID_RED_GOAL 给 AutoPan。该方法保留只为兼容旧调用点，新代码勿用。
 *
 * <h3>Tag ID 占位</h3>
 * 实际 ID 待 Decode 2026 官方手册确认；占位值仅为编译通过用，
 * 上场前必须更新。
 */
public final class FieldConstants {
    private FieldConstants() {}

    public static final int TAG_ID_BLUE_GOAL = 20;
    public static final int TAG_ID_RED_GOAL  = 24;

    /** 无视觉目标的哨兵值，传给 AutoPan 表示禁用视觉闭环。 */
    public static final int TAG_ID_NONE = -1;

    /**
     * 根据 goal 的场地 Y 坐标推断要瞄准的 AprilTag ID。
     * Y > 0 → 蓝方 goal，Y < 0 → 红方 goal。
     */
    public static int tagIdForGoalY(double goalY) {
        if (goalY > 0) return TAG_ID_BLUE_GOAL;
        if (goalY < 0) return TAG_ID_RED_GOAL;
        return TAG_ID_NONE;
    }
}

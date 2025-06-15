package info;

public class SystemInfo {
    /**
    *管理员界面 JPanel
     * √系统菜单JMenu
     * √退出登录JMenuItem 修改密码JMenuItem JInternalFrame
     * √住户信息JMenu JInternalFrame JTable
     *  序号 用户名 密码 住址 车牌
     * √管理员信息JMenu JInternalFrame JTable
     *  序号 用户名 密码 性别 年龄 电话
     * 物业费用JMenu JInternalFrame
     * √ 缴费项目JMenuItem JTable
     *      序号 项目 收费标准 截止日期
     * √ 缴费状态JMenuItem JTable
     *      序号 用户名 地址 项目1       项目2...
     *                          已/未缴费
     * 社区公告 JMenu JTable
     *  序号 标题 内容 发布时间
     * √报修管理 JMenu JTable
     *  序号 项目 保修人 住址 故障 预计花费 实际花费 维修情况
     *                                          已/等待处理
     */
    /*
    用户界面JPanel
    √系统菜单
        退出登录 修改密码
    个人信息(resident里获得信息)
        序号 用户名 密码 住址 车牌
    物业费用
        √缴费项目(共用界面)
        ×缴费状态
            序号 项目 缴费情况
    社区公告(共用界面)
    √报修管理(arepair里获得信息)
        序号 项目 故障 预计花费 实际花费 维修情况
                                    已/等待处理

     */
}

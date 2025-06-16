package command;

import DAO.ARepairDAO;

public class AddRepairCommand implements RepairCommand {
    private final Object[] repairData;
    private final boolean isAdmin;

    public AddRepairCommand(Object[] repairData, boolean isAdmin) {
        this.repairData = repairData;
        this.isAdmin = isAdmin;
    }

    @Override
    public void execute() {
        if (isAdmin) {
            ARepairDAO.add(repairData);
        } else {
            ARepairDAO.addUR(repairData);
        }
    }

    @Override
    public void undo() {
        // 由于添加操作没有直接的撤销方法，这里需要实现一个删除操作
        // 注意：这里假设repairData[0]是ID，如果不是，需要先查询获取ID
        if (repairData[0] != null) {
            ARepairDAO.remove((Integer) repairData[0]);
        }
    }
} 
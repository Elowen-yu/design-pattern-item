package command;

import DAO.ARepairDAO;

public class DeleteRepairCommand implements RepairCommand {
    private final int repairId;
    private final Object[] repairData;

    public DeleteRepairCommand(int repairId, Object[] repairData) {
        this.repairId = repairId;
        this.repairData = repairData;
    }

    @Override
    public void execute() {
        ARepairDAO.remove(repairId);
    }

    @Override
    public void undo() {
        // 恢复被删除的报修记录
        ARepairDAO.add(repairData);
    }
} 
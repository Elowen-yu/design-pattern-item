package command;

import DAO.ARepairDAO;

public class UpdateRepairCommand implements RepairCommand {
    private final int repairId;
    private final Object[] newData;
    private final Object[] oldData;
    private final boolean isAdmin;

    public UpdateRepairCommand(int repairId, Object[] newData, Object[] oldData, boolean isAdmin) {
        this.repairId = repairId;
        this.newData = newData;
        this.oldData = oldData;
        this.isAdmin = isAdmin;
    }

    @Override
    public void execute() {
        if (isAdmin) {
            ARepairDAO.update(repairId, newData);
        } else {
            ARepairDAO.updateUR(repairId, newData);
        }
    }

    @Override
    public void undo() {
        if (isAdmin) {
            ARepairDAO.update(repairId, oldData);
        } else {
            ARepairDAO.updateUR(repairId, oldData);
        }
    }
} 
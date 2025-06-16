package command;

import java.util.Stack;

public class CommandHistory {
    private final Stack<RepairCommand> undoStack = new Stack<>();
    private final Stack<RepairCommand> redoStack = new Stack<>();

    public void executeCommand(RepairCommand command) {
        command.execute();
        undoStack.push(command);
        redoStack.clear(); // 清空重做栈，因为新的命令会改变历史
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            RepairCommand command = undoStack.pop();
            command.undo();
            redoStack.push(command);
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            RepairCommand command = redoStack.pop();
            command.execute();
            undoStack.push(command);
        }
    }

    public boolean canUndo() {
        return !undoStack.isEmpty();
    }

    public boolean canRedo() {
        return !redoStack.isEmpty();
    }
} 
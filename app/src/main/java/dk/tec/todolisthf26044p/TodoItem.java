package dk.tec.todolisthf26044p;

import java.io.Serializable;
import java.time.LocalDateTime;

public class TodoItem implements Serializable {

    private String title;
    private boolean isFinished;
    private LocalDateTime create;
    private LocalDateTime deadline;
    private boolean repeatable;



    public TodoItem(String title, LocalDateTime deadline, boolean repeatable) {
        this.title = title;
        this.deadline = deadline;
        this.repeatable = repeatable;
        setCreate(LocalDateTime.now());
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public LocalDateTime getCreate() {
        return create;
    }

    public void setCreate(LocalDateTime create) {
        this.create = create;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public boolean isRepeatable() {
        return repeatable;
    }

    public void setRepeatable(boolean repeatable) {
        this.repeatable = repeatable;
    }
}

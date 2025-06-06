package org.fastcampus.post.domain.common;

import java.time.LocalDateTime;

public class DateTimeInfo {

    private boolean isEdited;
    private LocalDateTime dateTime;

    public DateTimeInfo() {
        this.isEdited = false;
        this.dateTime = LocalDateTime.now();
    }

    public void updateEditDatetime(){
        this.isEdited = true;
        this.dateTime = LocalDateTime.now();

    }
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public boolean isEdited() {
        return isEdited;
    }
}

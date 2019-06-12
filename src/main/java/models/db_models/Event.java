package models.db_models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;

import static utils.ValidationUtils.length;
import static utils.ValidationUtils.name;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Event
{
    private Long id;
    private String name;
    private String comment;

    private DateTime startDate;
    private DateTime endDate;

    private Long label_id;
    private Long calendar_id;

    public Event(){}

    public Event(Long id, String name, String comment, DateTime startDate, DateTime endDate, Long label_id, Long calendar_id)
    {
        this.id = id;
        this.name = name;
        this.comment = comment;
        this.startDate = startDate;
        this.endDate = endDate;
        this.label_id = label_id;
        this.calendar_id = calendar_id;
    }

    @JsonProperty
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @JsonProperty
    public DateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(DateTime startDate) {
        this.startDate = startDate;
    }

    @JsonProperty
    public DateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(DateTime endDate) {
        this.endDate = endDate;
    }

    @JsonProperty
    public Long getLabel_id() {
        return label_id;
    }

    public void setLabel_id(Long label_id) {
        this.label_id = label_id;
    }

    @JsonProperty
    public Long getCalendar_id() {
        return calendar_id;
    }

    public void setCalendar_id(Long calendar_id) {
        this.calendar_id = calendar_id;
    }

    public boolean isNameValid() {
        return (name != null && name(name) && length(name, 1, 50));
    }

    public boolean isCommentValid() {
        return  (comment != null && length(comment, 1, 255));
    }

    public boolean isStartDateValid() {
        return startDate != null && (startDate.isBefore(endDate) || startDate.isEqual(endDate));
    }

    public boolean isEndDateValid() {
        return endDate != null && (endDate.isAfter(startDate) || endDate.isEqual(startDate));
    }

    public boolean isLabelIdValid() {
        return (label_id != null && label_id > 0);
    }

    public boolean isCalendarIdValid() {
        return (calendar_id != null && calendar_id > 0 );
    }

    public boolean isValid() {
        return(isNameValid() &&
                isCommentValid() &&
                isStartDateValid() &&
                isEndDateValid() &&
                isLabelIdValid() &&
                isCalendarIdValid());
    }
}

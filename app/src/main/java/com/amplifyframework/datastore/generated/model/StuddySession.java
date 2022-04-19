package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.temporal.Temporal;
import com.amplifyframework.core.model.annotations.HasMany;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.AuthStrategy;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.ModelOperation;
import com.amplifyframework.core.model.annotations.AuthRule;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the StuddySession type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "StuddySessions", authRules = {
  @AuthRule(allow = AuthStrategy.PRIVATE, operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
})
public final class StuddySession implements Model {
  public static final QueryField ID = field("StuddySession", "id");
  public static final QueryField TOPIC = field("StuddySession", "topic");
  public static final QueryField DATE = field("StuddySession", "date");
  public static final QueryField TIME = field("StuddySession", "time");
  public static final QueryField LOCATION = field("StuddySession", "location");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String") String topic;
  private final @ModelField(targetType="AWSDate") Temporal.Date date;
  private final @ModelField(targetType="AWSTime") Temporal.Time time;
  private final @ModelField(targetType="String") String location;
  private final @ModelField(targetType="StuddySessionUser") @HasMany(associatedWith = "studdySession", type = StuddySessionUser.class) List<StuddySessionUser> Participants = null;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String getId() {
      return id;
  }
  
  public String getTopic() {
      return topic;
  }
  
  public Temporal.Date getDate() {
      return date;
  }
  
  public Temporal.Time getTime() {
      return time;
  }
  
  public String getLocation() {
      return location;
  }
  
  public List<StuddySessionUser> getParticipants() {
      return Participants;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private StuddySession(String id, String topic, Temporal.Date date, Temporal.Time time, String location) {
    this.id = id;
    this.topic = topic;
    this.date = date;
    this.time = time;
    this.location = location;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      StuddySession studdySession = (StuddySession) obj;
      return ObjectsCompat.equals(getId(), studdySession.getId()) &&
              ObjectsCompat.equals(getTopic(), studdySession.getTopic()) &&
              ObjectsCompat.equals(getDate(), studdySession.getDate()) &&
              ObjectsCompat.equals(getTime(), studdySession.getTime()) &&
              ObjectsCompat.equals(getLocation(), studdySession.getLocation()) &&
              ObjectsCompat.equals(getCreatedAt(), studdySession.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), studdySession.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getTopic())
      .append(getDate())
      .append(getTime())
      .append(getLocation())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("StuddySession {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("topic=" + String.valueOf(getTopic()) + ", ")
      .append("date=" + String.valueOf(getDate()) + ", ")
      .append("time=" + String.valueOf(getTime()) + ", ")
      .append("location=" + String.valueOf(getLocation()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static BuildStep builder() {
      return new Builder();
  }
  
  /** 
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   */
  public static StuddySession justId(String id) {
    return new StuddySession(
      id,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      topic,
      date,
      time,
      location);
  }
  public interface BuildStep {
    StuddySession build();
    BuildStep id(String id);
    BuildStep topic(String topic);
    BuildStep date(Temporal.Date date);
    BuildStep time(Temporal.Time time);
    BuildStep location(String location);
  }
  

  public static class Builder implements BuildStep {
    private String id;
    private String topic;
    private Temporal.Date date;
    private Temporal.Time time;
    private String location;
    @Override
     public StuddySession build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new StuddySession(
          id,
          topic,
          date,
          time,
          location);
    }
    
    @Override
     public BuildStep topic(String topic) {
        this.topic = topic;
        return this;
    }
    
    @Override
     public BuildStep date(Temporal.Date date) {
        this.date = date;
        return this;
    }
    
    @Override
     public BuildStep time(Temporal.Time time) {
        this.time = time;
        return this;
    }
    
    @Override
     public BuildStep location(String location) {
        this.location = location;
        return this;
    }
    
    /** 
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     */
    public BuildStep id(String id) {
        this.id = id;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String topic, Temporal.Date date, Temporal.Time time, String location) {
      super.id(id);
      super.topic(topic)
        .date(date)
        .time(time)
        .location(location);
    }
    
    @Override
     public CopyOfBuilder topic(String topic) {
      return (CopyOfBuilder) super.topic(topic);
    }
    
    @Override
     public CopyOfBuilder date(Temporal.Date date) {
      return (CopyOfBuilder) super.date(date);
    }
    
    @Override
     public CopyOfBuilder time(Temporal.Time time) {
      return (CopyOfBuilder) super.time(time);
    }
    
    @Override
     public CopyOfBuilder location(String location) {
      return (CopyOfBuilder) super.location(location);
    }
  }
  
}

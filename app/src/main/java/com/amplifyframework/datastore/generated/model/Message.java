package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.temporal.Temporal;

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

/** This is an auto generated class representing the Message type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Messages", authRules = {
  @AuthRule(allow = AuthStrategy.PRIVATE, operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
})
@Index(name = "byUser", fields = {"userID"})
public final class Message implements Model {
  public static final QueryField ID = field("Message", "id");
  public static final QueryField CONTENT = field("Message", "content");
  public static final QueryField USER_ID = field("Message", "userID");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String") String content;
  private final @ModelField(targetType="ID", isRequired = true) String userID;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String getId() {
      return id;
  }
  
  public String getContent() {
      return content;
  }
  
  public String getUserId() {
      return userID;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private Message(String id, String content, String userID) {
    this.id = id;
    this.content = content;
    this.userID = userID;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Message message = (Message) obj;
      return ObjectsCompat.equals(getId(), message.getId()) &&
              ObjectsCompat.equals(getContent(), message.getContent()) &&
              ObjectsCompat.equals(getUserId(), message.getUserId()) &&
              ObjectsCompat.equals(getCreatedAt(), message.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), message.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getContent())
      .append(getUserId())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Message {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("content=" + String.valueOf(getContent()) + ", ")
      .append("userID=" + String.valueOf(getUserId()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static UserIdStep builder() {
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
  public static Message justId(String id) {
    return new Message(
      id,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      content,
      userID);
  }
  public interface UserIdStep {
    BuildStep userId(String userId);
  }
  

  public interface BuildStep {
    Message build();
    BuildStep id(String id);
    BuildStep content(String content);
  }
  

  public static class Builder implements UserIdStep, BuildStep {
    private String id;
    private String userID;
    private String content;
    @Override
     public Message build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Message(
          id,
          content,
          userID);
    }
    
    @Override
     public BuildStep userId(String userId) {
        Objects.requireNonNull(userId);
        this.userID = userId;
        return this;
    }
    
    @Override
     public BuildStep content(String content) {
        this.content = content;
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
    private CopyOfBuilder(String id, String content, String userId) {
      super.id(id);
      super.userId(userId)
        .content(content);
    }
    
    @Override
     public CopyOfBuilder userId(String userId) {
      return (CopyOfBuilder) super.userId(userId);
    }
    
    @Override
     public CopyOfBuilder content(String content) {
      return (CopyOfBuilder) super.content(content);
    }
  }
  
}

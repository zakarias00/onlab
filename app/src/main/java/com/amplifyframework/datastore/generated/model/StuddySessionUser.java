package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.annotations.BelongsTo;
import com.amplifyframework.core.model.temporal.Temporal;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the StuddySessionUser type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "StuddySessionUsers")
public final class StuddySessionUser implements Model {
  public static final QueryField ID = field("StuddySessionUser", "id");
  public static final QueryField STUDDY_SESSION = field("StuddySessionUser", "studdySessionID");
  public static final QueryField USER = field("StuddySessionUser", "userID");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="StuddySession", isRequired = true) @BelongsTo(targetName = "studdySessionID", type = StuddySession.class) StuddySession studdySession;
  private final @ModelField(targetType="User", isRequired = true) @BelongsTo(targetName = "userID", type = User.class) User user;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String getId() {
      return id;
  }
  
  public StuddySession getStuddySession() {
      return studdySession;
  }
  
  public User getUser() {
      return user;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private StuddySessionUser(String id, StuddySession studdySession, User user) {
    this.id = id;
    this.studdySession = studdySession;
    this.user = user;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      StuddySessionUser studdySessionUser = (StuddySessionUser) obj;
      return ObjectsCompat.equals(getId(), studdySessionUser.getId()) &&
              ObjectsCompat.equals(getStuddySession(), studdySessionUser.getStuddySession()) &&
              ObjectsCompat.equals(getUser(), studdySessionUser.getUser()) &&
              ObjectsCompat.equals(getCreatedAt(), studdySessionUser.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), studdySessionUser.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getStuddySession())
      .append(getUser())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("StuddySessionUser {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("studdySession=" + String.valueOf(getStuddySession()) + ", ")
      .append("user=" + String.valueOf(getUser()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static StuddySessionStep builder() {
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
  public static StuddySessionUser justId(String id) {
    return new StuddySessionUser(
      id,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      studdySession,
      user);
  }
  public interface StuddySessionStep {
    UserStep studdySession(StuddySession studdySession);
  }
  

  public interface UserStep {
    BuildStep user(User user);
  }
  

  public interface BuildStep {
    StuddySessionUser build();
    BuildStep id(String id);
  }
  

  public static class Builder implements StuddySessionStep, UserStep, BuildStep {
    private String id;
    private StuddySession studdySession;
    private User user;
    @Override
     public StuddySessionUser build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new StuddySessionUser(
          id,
          studdySession,
          user);
    }
    
    @Override
     public UserStep studdySession(StuddySession studdySession) {
        Objects.requireNonNull(studdySession);
        this.studdySession = studdySession;
        return this;
    }
    
    @Override
     public BuildStep user(User user) {
        Objects.requireNonNull(user);
        this.user = user;
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
    private CopyOfBuilder(String id, StuddySession studdySession, User user) {
      super.id(id);
      super.studdySession(studdySession)
        .user(user);
    }
    
    @Override
     public CopyOfBuilder studdySession(StuddySession studdySession) {
      return (CopyOfBuilder) super.studdySession(studdySession);
    }
    
    @Override
     public CopyOfBuilder user(User user) {
      return (CopyOfBuilder) super.user(user);
    }
  }
  
}

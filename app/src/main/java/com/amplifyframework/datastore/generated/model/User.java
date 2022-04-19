package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.annotations.HasMany;
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

/** This is an auto generated class representing the User type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Users", authRules = {
  @AuthRule(allow = AuthStrategy.OWNER, ownerField = "owner", identityClaim = "cognito:username", provider = "userPools", operations = { ModelOperation.CREATE, ModelOperation.DELETE, ModelOperation.UPDATE })
})
public final class User implements Model {
  public static final QueryField ID = field("User", "id");
  public static final QueryField NAME = field("User", "name");
  public static final QueryField FIRST_NAME = field("User", "first_name");
  public static final QueryField SECOND_NAME = field("User", "second_name");
  public static final QueryField BIRTHDAY = field("User", "birthday");
  public static final QueryField SCHOOL = field("User", "school");
  public static final QueryField EMAIL = field("User", "email");
  public static final QueryField PASSWORD = field("User", "password");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String") String name;
  private final @ModelField(targetType="String") String first_name;
  private final @ModelField(targetType="String") String second_name;
  private final @ModelField(targetType="String") String birthday;
  private final @ModelField(targetType="String") String school;
  private final @ModelField(targetType="AWSEmail") String email;
  private final @ModelField(targetType="Message") @HasMany(associatedWith = "userID", type = Message.class) List<Message> Messages = null;
  private final @ModelField(targetType="String") String password;
  private final @ModelField(targetType="StuddySessionUser") @HasMany(associatedWith = "user", type = StuddySessionUser.class) List<StuddySessionUser> studdysessions = null;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String getId() {
      return id;
  }
  
  public String getName() {
      return name;
  }
  
  public String getFirstName() {
      return first_name;
  }
  
  public String getSecondName() {
      return second_name;
  }
  
  public String getBirthday() {
      return birthday;
  }
  
  public String getSchool() {
      return school;
  }
  
  public String getEmail() {
      return email;
  }
  
  public List<Message> getMessages() {
      return Messages;
  }
  
  public String getPassword() {
      return password;
  }
  
  public List<StuddySessionUser> getStuddysessions() {
      return studdysessions;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private User(String id, String name, String first_name, String second_name, String birthday, String school, String email, String password) {
    this.id = id;
    this.name = name;
    this.first_name = first_name;
    this.second_name = second_name;
    this.birthday = birthday;
    this.school = school;
    this.email = email;
    this.password = password;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      User user = (User) obj;
      return ObjectsCompat.equals(getId(), user.getId()) &&
              ObjectsCompat.equals(getName(), user.getName()) &&
              ObjectsCompat.equals(getFirstName(), user.getFirstName()) &&
              ObjectsCompat.equals(getSecondName(), user.getSecondName()) &&
              ObjectsCompat.equals(getBirthday(), user.getBirthday()) &&
              ObjectsCompat.equals(getSchool(), user.getSchool()) &&
              ObjectsCompat.equals(getEmail(), user.getEmail()) &&
              ObjectsCompat.equals(getPassword(), user.getPassword()) &&
              ObjectsCompat.equals(getCreatedAt(), user.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), user.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getName())
      .append(getFirstName())
      .append(getSecondName())
      .append(getBirthday())
      .append(getSchool())
      .append(getEmail())
      .append(getPassword())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("User {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("name=" + String.valueOf(getName()) + ", ")
      .append("first_name=" + String.valueOf(getFirstName()) + ", ")
      .append("second_name=" + String.valueOf(getSecondName()) + ", ")
      .append("birthday=" + String.valueOf(getBirthday()) + ", ")
      .append("school=" + String.valueOf(getSchool()) + ", ")
      .append("email=" + String.valueOf(getEmail()) + ", ")
      .append("password=" + String.valueOf(getPassword()) + ", ")
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
  public static User justId(String id) {
    return new User(
      id,
      null,
      null,
      null,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      name,
      first_name,
      second_name,
      birthday,
      school,
      email,
      password);
  }
  public interface BuildStep {
    User build();
    BuildStep id(String id);
    BuildStep name(String name);
    BuildStep firstName(String firstName);
    BuildStep secondName(String secondName);
    BuildStep birthday(String birthday);
    BuildStep school(String school);
    BuildStep email(String email);
    BuildStep password(String password);
  }
  

  public static class Builder implements BuildStep {
    private String id;
    private String name;
    private String first_name;
    private String second_name;
    private String birthday;
    private String school;
    private String email;
    private String password;
    @Override
     public User build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new User(
          id,
          name,
          first_name,
          second_name,
          birthday,
          school,
          email,
          password);
    }
    
    @Override
     public BuildStep name(String name) {
        this.name = name;
        return this;
    }
    
    @Override
     public BuildStep firstName(String firstName) {
        this.first_name = firstName;
        return this;
    }
    
    @Override
     public BuildStep secondName(String secondName) {
        this.second_name = secondName;
        return this;
    }
    
    @Override
     public BuildStep birthday(String birthday) {
        this.birthday = birthday;
        return this;
    }
    
    @Override
     public BuildStep school(String school) {
        this.school = school;
        return this;
    }
    
    @Override
     public BuildStep email(String email) {
        this.email = email;
        return this;
    }
    
    @Override
     public BuildStep password(String password) {
        this.password = password;
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
    private CopyOfBuilder(String id, String name, String firstName, String secondName, String birthday, String school, String email, String password) {
      super.id(id);
      super.name(name)
        .firstName(firstName)
        .secondName(secondName)
        .birthday(birthday)
        .school(school)
        .email(email)
        .password(password);
    }
    
    @Override
     public CopyOfBuilder name(String name) {
      return (CopyOfBuilder) super.name(name);
    }
    
    @Override
     public CopyOfBuilder firstName(String firstName) {
      return (CopyOfBuilder) super.firstName(firstName);
    }
    
    @Override
     public CopyOfBuilder secondName(String secondName) {
      return (CopyOfBuilder) super.secondName(secondName);
    }
    
    @Override
     public CopyOfBuilder birthday(String birthday) {
      return (CopyOfBuilder) super.birthday(birthday);
    }
    
    @Override
     public CopyOfBuilder school(String school) {
      return (CopyOfBuilder) super.school(school);
    }
    
    @Override
     public CopyOfBuilder email(String email) {
      return (CopyOfBuilder) super.email(email);
    }
    
    @Override
     public CopyOfBuilder password(String password) {
      return (CopyOfBuilder) super.password(password);
    }
  }
  
}

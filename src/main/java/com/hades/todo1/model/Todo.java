package com.hades.todo1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="todo")

@AllArgsConstructor
@NoArgsConstructor
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="id")
    private Long id;

    @Column (name="title")
    @NotBlank(message= "Le titre est obligatoire")
    private String title;

    @Column (name="description")
    private String description;

    @Column (name="complete")
    private boolean complete;

    @Column (name="date_Created")
    private LocalDateTime dateCreated;

    public LocalDateTime getDateDefinitive() {
        return dateDefinitive;
    }

    public void setDateDefinitive(LocalDateTime dateDefinitive) {
        this.dateDefinitive = dateDefinitive;
    }

    @Column (name="last_Uptade_date")
    private LocalDateTime lastUptated;
    @Column (name="date_deinitive")
    private LocalDateTime dateDefinitive;


    @Column(name="Priority")
    @Enumerated(EnumType.STRING)
    private Priority priority;


    @Column(name = "Recurente")
    private boolean reccurent;


    @OneToMany(mappedBy = "parentTask", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SubTask> subTasks = new ArrayList<>();

    public boolean isReccurent() {
        return reccurent;
    }

    public void setReccurent(boolean reccurent) {
        this.reccurent = reccurent;
    }

    public RecurentType getRecurentType() {
        return recurentType;
    }

    public void setRecurentType(RecurentType recurentType) {
        this.recurentType = recurentType;
    }

    @Column(name="type Recurent")
    @Enumerated(EnumType.STRING)
    private RecurentType recurentType;

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDateTime getLastUptated() {
        return lastUptated;
    }

    public void setLastUptated(LocalDateTime lastUptated) {
        this.lastUptated = lastUptated;
    }
}

package pl.edu.wszib.school.website.model;

import javax.persistence.*;
import java.util.SortedSet;

@Entity(name="Posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Page page;

    @ManyToOne(fetch = FetchType.EAGER)
    private User author;

    private String date; //data utworzenia
    private String title;

    @Column()
    @Lob
    private String content; //zawartość strony

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="post_id", referencedColumnName = "id")
    @OrderBy("id ASC")
    private SortedSet<Comment> comments;

    public Post() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public SortedSet<Comment> getComments() {
        return comments;
    }

    public void setComments(SortedSet<Comment> comments) {
        this.comments = comments;
    }
}

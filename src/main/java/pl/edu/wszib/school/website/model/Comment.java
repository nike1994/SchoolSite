package pl.edu.wszib.school.website.model;

import javax.persistence.*;

@Entity(name="Comments")
public class Comment implements Comparable<Comment>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="post_id", referencedColumnName = "id")
    private Post post;

    @ManyToOne(fetch = FetchType.EAGER)
    private User author;

    private String date; //data utworzenia
    private String content;

    public Comment() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    @Override
    public int compareTo(Comment o) {
        return  ((Integer)this.id).compareTo(o.id);
    }
}

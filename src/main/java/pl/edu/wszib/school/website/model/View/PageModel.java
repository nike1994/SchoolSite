package pl.edu.wszib.school.website.model.View;

public class PageModel {
    private int page_id;
    private int parent_id;
    private String title;
    private boolean hasChild;

    public PageModel(int id_page, int id_parent, String title) {
        this.page_id = id_page;
        this.parent_id = id_parent;
        this.title = title;
    }

    public PageModel() {
    }

    public int getPage_id() {
        return page_id;
    }

    public void setPage_id(int page_id) {
        this.page_id = page_id;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isHasChild() {
        return hasChild;
    }

    public void setHasChild(boolean hasChild) {
        this.hasChild = hasChild;
    }
}

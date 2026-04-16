public class Amenity {

    private int id;
    private String name;
    private String description;
    public boolean equals(Object o){
        if(o==null || !(o instanceof Amenity)) return false;
        Amenity amenity = (Amenity) o;
        return (this.id== amenity.id);
    }


     public Amenity(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
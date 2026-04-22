public class Amenity {

    private static int nextId = 1;
    private int id;
    private String name;
    private String description;
    public boolean equals(Object o){
        if(o==null || !(o instanceof Amenity)) return false;
        Amenity amenity = (Amenity) o;
        return (this.id== amenity.id);
    }


     public Amenity( String name, String description) {
        this.id = nextId++;
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